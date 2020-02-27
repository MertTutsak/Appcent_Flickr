package com.appcent.flickr.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcent.flickr.R
import com.appcent.flickr.data.remote.model.response.recent.Photo
import com.appcent.flickr.ui.common.components.paginglayout.PagingLayout
import com.appcent.flickr.utility.Constants
import com.appcent.flickr.utility.extension.isNull
import com.appcent.flickr.utility.extension.loadImage
import com.appcent.flickr.utility.extension.notNull
import com.appcent.flickr.utility.extension.setOnDebouncedClickListener
import java.util.ArrayList

class RecyclerViewPhotosAdapter(
    val photos: ArrayList<Photo>,
    private val listener: PhotoClickListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        @SuppressLint("WrongConstant")
        @JvmStatic
        @BindingAdapter(
            "bind:photosNestedScrollView",
            "bind:photos",
            "bind:photoClick",
            "bind:pagingListener"
        )
        fun bind(
            pl: PagingLayout?,
            ns: NestedScrollView?,
            photos: ArrayList<Photo>?,
            clickListener: PhotoClickListener?,
            pagingListener: PagingLayout.OnLoadingInterface?
        ) {

            pl.notNull { pagingLayout: PagingLayout ->
                if (pagingLayout.rvPaging.adapter.isNull()) {
                    ns.notNull { pagingLayout.setNestedScrollview(it) }
                    pagingLayout.rvPaging.setHasFixedSize(true)
                    pagingLayout.onLoadingInterface = pagingListener
                    pagingLayout.rvPaging.adapter =
                        RecyclerViewPhotosAdapter(photos ?: ArrayList(), clickListener)
                    pagingLayout.rvPaging.layoutManager =
                        LinearLayoutManager(pagingLayout.context, LinearLayout.VERTICAL, false)
                    pagingLayout.rvPaging.setRecycledViewPool(RecyclerView.RecycledViewPool())
                } else {
                    (pagingLayout.rvPaging.adapter as RecyclerViewPhotosAdapter).let {
                        it.addList(photos)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return FlickrPhotoHolder(
            LayoutInflater.from(parent.context)!!.inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return this.photos.size
    }

    fun addList(photos: ArrayList<Photo>?) {
        photos?.forEach {
            this.photos.add(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun clear() {
        this.photos.clear()
        notifyDataSetChanged()
    }

    fun setList(photos: ArrayList<Photo>?) {
        clear()
        addList(photos)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as FlickrPhotoHolder).let {
            it.bindData(
                this.photos[position],
                position,
                listener
            )
        }
    }

    interface PhotoClickListener {
        fun onClick(photo: Photo, imgView: ImageView)
    }

    class FlickrPhotoHolder(
        val containerView: View
    ) : RecyclerView.ViewHolder(containerView) {
        var imgPhoto: ImageView = containerView.findViewById(R.id.imgPhoto)
        fun bindData(model: Photo, index: Int, listener: PhotoClickListener?) {
            imgPhoto.loadImage(model.getPhotoURL(Constants.PHOTO.ORIGINAL_SIZE), null, null)
            imgPhoto.setOnDebouncedClickListener {
                listener?.onClick(model, imgPhoto)
            }
            ViewCompat.setTransitionName(
                imgPhoto,
                Constants.SHARE_ELEMENT.SHARE_IMAGE_TRANSITION_NAME + index
            )
        }

    }
}