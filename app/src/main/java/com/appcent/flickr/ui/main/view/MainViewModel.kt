package com.appcent.flickr.ui.main.view

import androidx.lifecycle.MutableLiveData
import com.appcent.flickr.data.manager.DataManagerImp
import com.appcent.flickr.data.remote.model.request.recent.RecentRequest
import com.appcent.flickr.data.remote.model.response.recent.RecentResponse
import com.appcent.flickr.data.remote.service.resource.Resource
import com.appcent.flickr.utility.extension.plusAssign
import com.appcent.flickr.ui.common.base.BaseViewModel
import com.appcent.flickr.utility.extension.notNull
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(var dataManagerImp: DataManagerImp) : BaseViewModel() {

    var photosResponseLiveData = MutableLiveData<Resource<RecentResponse>>()

    fun getRecent(pageIndex: Int) {

        if(pageIndex == 0){
            navigator.showLoading()
        }

        dataManagerImp.apiHelperImp.getRecent(RecentRequest(page = pageIndex))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                photosResponseLiveData.value = it
                navigator.hideLoading()
                it.error.notNull { it -> navigator.showErrorDialog(it) }
            }.also {
                disposable += it
            }
    }

}