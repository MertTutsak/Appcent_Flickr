package com.appcent.flickr.data.remote.service.api

import com.appcent.flickr.data.remote.service.error.ApiError
import com.appcent.flickr.data.remote.model.request.recent.RecentRequest
import com.appcent.flickr.data.remote.model.response.recent.RecentResponse
import com.appcent.flickr.utility.extension.applyLoading
import com.appcent.flickr.data.remote.service.error.ErrorStatus
import com.appcent.flickr.data.remote.service.resource.Resource
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiHelperImp @Inject constructor(
    private val apiHelper: ApiHelper
) {

    fun getRecent(request: RecentRequest): Observable<Resource<RecentResponse>> {
        return apiHelper.getRecent(
            request.per_page,
            request.page,
            request.format,
            request.api_key,
            request.nojsoncallback.toString(),
            request.method
        ).map {
            if (it.isSuccess()) {
                Resource.success(it)
            } else {
                Resource.apiError(
                    ApiError.handleException(
                        it.code ?: ErrorStatus.UNKNOWN_ERROR.code(), it.message
                    )
                )
            }
        }.onErrorReturn {
            Resource.error(it)
        }.subscribeOn(Schedulers.io()).compose(applyLoading())
    }

}