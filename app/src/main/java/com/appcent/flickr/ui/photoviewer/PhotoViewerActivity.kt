package com.appcent.flickr.ui.photoviewer

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.appcent.flickr.R
import com.appcent.flickr.databinding.ActivityPhotoviewerBinding
import com.appcent.flickr.ui.common.base.view.BaseActivity
import com.appcent.flickr.utility.Constants
import com.appcent.flickr.utility.extension.getStatusBarHeight
import com.appcent.flickr.utility.extension.observeNonNull
import com.appcent.flickr.utility.extension.setNavController
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.activity_photoviewer.*
import kotlin.math.hypot
import java.lang.Exception
import javax.inject.Inject

class PhotoViewerActivity : BaseActivity() {

    private lateinit var photoViewerActivityDataBinding: ActivityPhotoviewerBinding

    @Inject
    lateinit var photoViewerModel: PhotoViewerModel

    var xCoOrdinate: Float = 0f
    var yCoOrdinate: Float = 0f
    var screenCenterX: Double = 0.0
    var screenCenterY: Double = 0.0

    override fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup {
        photoViewerActivityDataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_photoviewer)
        return rlContainer as ViewGroup
    }

    override fun bindView() {
        photoViewerModel.navigator = this
        supportPostponeEnterTransition()

        initObservables()
        initContainer()
        initImgDraggable()
    }

    private fun initObservables() {
        photoViewerModel.stateLiveData.observeNonNull(this) {
            photoViewerActivityDataBinding.viewState = it
        }
    }

    private fun initContainer() {
        this.setNavController(
            android.R.color.transparent,
            true
        )
    }

    private fun initImgDraggable() {
        /**
         * Calculate max hypo value and center of screen.
         */
        val display: DisplayMetrics = resources.displayMetrics
        screenCenterX = (display.widthPixels.toDouble() / 2)
        screenCenterY =
            (display.heightPixels.toDouble() - this.applicationContext.getStatusBarHeight()) / 2
        val maxHypo: Double = hypot(screenCenterX, screenCenterY)

        photoViewerActivityDataBinding.touchListener = object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                /**
                 * Calculate hypo value of current imageview position according to center
                 */
                val centerYPos: Double = imgDraggable.y.toDouble() + (imgDraggable.height / 2)
                val centerXPos: Double = imgDraggable.x.toDouble() + (imgDraggable.width / 2)
                val a: Double = screenCenterX - centerXPos
                val b: Double = screenCenterY - centerYPos
                val hypo: Double = hypot(a, b)

                with((hypo * 255).toInt() / maxHypo.toInt()) {
                    if (this < 255
                    ) {
                        photoViewerModel.setAlpha(255 - this)
                    }
                }

                when (event?.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        xCoOrdinate = imgDraggable.x - event.rawX
                        yCoOrdinate = imgDraggable.y - event.rawY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        imgDraggable.animate().x(event.rawX + xCoOrdinate)
                            .y(event.rawY + yCoOrdinate).setDuration(0).start()
                    }
                    MotionEvent.ACTION_UP -> {
                        if (photoViewerModel.stateLiveData.value!!.alpha > 70) {
                            supportFinishAfterTransition()
                            return false
                        } else {
                            imgDraggable.animate().x(0f)
                                .y(screenCenterY.toFloat() - imgDraggable.height / 2)
                                .setDuration(100).start()
                            photoViewerModel.setAlpha(255)
                            photoViewerActivityDataBinding.executePendingBindings()
                        }
                    }
                    else -> return false

                }
                return true
            }
        }

        fetchImage()
    }

    fun fetchImage() {
        photoViewerActivityDataBinding.transitionCallback = object : Callback {
            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition()
            }

            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }
        }
        photoViewerModel.stateLiveData.value?.url =
            intent.extras?.getString(Constants.SHARE_ELEMENT.SHARE_IMAGE) ?: ""
    }
}
