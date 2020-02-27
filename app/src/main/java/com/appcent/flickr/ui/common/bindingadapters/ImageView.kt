package com.appcent.flickr.ui.common.bindingadapters

import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.appcent.flickr.utility.extension.isNull
import com.appcent.flickr.utility.extension.loadImage
import com.appcent.flickr.utility.extension.notNull
import com.appcent.flickr.utility.provider.PicassoProvider
import com.squareup.picasso.Callback

object ImageView {

    @JvmStatic
    @BindingAdapter("bind:shareName")
    fun shareName(view: ImageView?, shareName: String?) {
        view.notNull {
            ViewCompat.setTransitionName(
                it,
                shareName ?: ""
            )
        }
    }

    @JvmStatic
    @BindingAdapter("bind:fetchImage")
    fun fetchImage(view: ImageView?, fetchImage: String?) {
        view?.loadImage(fetchImage, null, null)
    }

    @JvmStatic
    @BindingAdapter("bind:transitionFetchImage", "bind:transitionCallback")
    fun transitionFetchImage(
        view: ImageView?,
        transitionFetchImage: String?,
        transitionFetchCallback: Callback?
    ) {
        view.notNull { imageView ->
            transitionFetchCallback.notNull {
                PicassoProvider.with(imageView.context)
                    .load(transitionFetchImage ?: "")
                    .noFade().resize(1920, 1080).centerCrop()
                    .into(imageView, transitionFetchCallback)
            }.isNull {
                PicassoProvider.with(imageView.context)
                    .load(transitionFetchImage ?: "")
                    .noFade().resize(1920, 1080).centerCrop()
                    .into(imageView)
            }
        }
    }

}