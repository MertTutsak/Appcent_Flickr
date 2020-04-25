package com.appcent.flickr.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import com.appcent.flickr.R
import com.appcent.flickr.data.remote.model.response.recent.Photo
import com.appcent.flickr.data.remote.service.resource.Status
import com.appcent.flickr.utility.extension.observeNonNull
import com.appcent.flickr.databinding.ActivityMainBinding
import com.appcent.flickr.ui.common.base.view.BaseBottomUpActivity
import com.appcent.flickr.ui.common.components.paginglayout.PagingLayout
import com.appcent.flickr.ui.main.adapter.RecyclerViewPhotosAdapter
import com.appcent.flickr.ui.photoviewer.PhotoViewerActivity
import com.appcent.flickr.utility.Constants
import com.appcent.flickr.utility.extension.isNotNull
import com.appcent.flickr.utility.extension.setNavController
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseBottomUpActivity(), PagingLayout.OnLoadingInterface {

    private lateinit var mainActivityDataBinding: ActivityMainBinding

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup {
        mainActivityDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        return rootMainActivity as ViewGroup
    }

    override fun bindView() {
        mainViewModel.navigator = this
        mainActivityDataBinding.lifecycleOwner = this
        this.setNavController(
            android.R.color.transparent,
            true
        )

        initPagingLayout()
        initObservables()

        mainViewModel.getRecent(mainActivityDataBinding.rootMainActivity.cleanPageIndex().also {
            mainActivityDataBinding.rootMainActivity.getAdapter<RecyclerViewPhotosAdapter>()?.clear()
        })
    }

    private fun initObservables() {
        mainViewModel.photosResponseLiveData.observeNonNull(this) {
            when (it.status) {
                Status.ERROR -> {
                    mainActivityDataBinding.rootMainActivity.stopLoading()
                }
                Status.SUCCESS -> {
                    mainActivityDataBinding.rootMainActivity.stopLoading()
                    mainActivityDataBinding.viewModel = mainViewModel
                    if (it.data.isNotNull() && it.data?.photos.isNotNull()) {
                        if (it.data?.photos?.isLastPage() == true) {
                            mainActivityDataBinding.rootMainActivity.isLastPage = true
                        }
                    }
                }
            }
        }
    }

    private fun initPagingLayout() {
        mainActivityDataBinding.pagingListener = MaximoMainFragment@ this
        mainActivityDataBinding.photoClick = object :
            RecyclerViewPhotosAdapter.PhotoClickListener {
            override fun onClick(photo: Photo, imgView: ImageView) {
                imgView.openPhoto(photo)
            }
        }
    }

    private fun ImageView.openPhoto(photo: Photo) {
        val intent = Intent(this.context, PhotoViewerActivity::class.java)

        intent.putExtra(
            Constants.SHARE_ELEMENT.SHARE_IMAGE,
            photo.getPhotoURL(Constants.PHOTO.ORIGINAL_SIZE)
        )

        intent.putExtra(
            Constants.SHARE_ELEMENT.SHARE_IMAGE_TRANSITION_NAME,
            ViewCompat.getTransitionName(this) ?: ""
        )

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@MainActivity,
            this,
            ViewCompat.getTransitionName(this) ?: ""
        )
        startActivity(intent, options.toBundle())
    }

    override fun onStartLoad() {
        mainViewModel.getRecent(mainActivityDataBinding.rootMainActivity.pageIndexIncrease())
    }

    override fun onStopLoad() {
    }

}
