package com.appcent.flickr.ui.common.base

import androidx.lifecycle.ViewModel
import com.appcent.flickr.data.remote.model.base.BaseResponse
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    private var mNavigator: WeakReference<BaseNavigator>? = null

    var navigator: BaseNavigator
        get() = mNavigator!!.get()!!
        set(navigator) {
            this.mNavigator = WeakReference(navigator)
        }

    @Inject
    lateinit var disposable: CompositeDisposable

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.clear()
        }
        super.onCleared()
    }

    fun checkResponse(baseResponse: BaseResponse): Boolean {
        return true
    }
}