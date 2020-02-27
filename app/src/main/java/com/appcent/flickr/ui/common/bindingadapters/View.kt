package com.appcent.flickr.ui.common.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.appcent.flickr.utility.extension.notNull

object View {

    @JvmStatic
    @BindingAdapter("bind:backgroundAlpha")
    fun backgroundAlpha(view: View?, backgroundAlpha: Int?) {
        backgroundAlpha.notNull { view?.background?.alpha = it }
    }

    @JvmStatic
    @BindingAdapter("bind:setTouchListener")
    fun setTouchListener(view: View?, listener: View.OnTouchListener?) {
        listener.notNull { view?.setOnTouchListener(it) }
    }

}