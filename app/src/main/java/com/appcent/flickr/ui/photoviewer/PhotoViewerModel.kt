package com.appcent.flickr.ui.photoviewer

import androidx.lifecycle.MutableLiveData
import com.appcent.flickr.data.manager.DataManagerImp
import com.appcent.flickr.ui.common.base.BaseViewModel
import javax.inject.Inject

class PhotoViewerModel @Inject constructor(var dataManagerImp: DataManagerImp) : BaseViewModel() {

    var stateLiveData = MutableLiveData<PhotoViewerViewState>()

    init {
        stateLiveData = MutableLiveData(PhotoViewerViewState())
    }


    fun setAlpha(alpha: Int) {
        stateLiveData.postValue(stateLiveData.value?.also { it.alpha = alpha }
            ?: PhotoViewerViewState())
    }

}