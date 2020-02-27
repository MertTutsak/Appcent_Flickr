package com.appcent.flickr.data.remote.model.request.recent

import android.os.Parcelable
import com.appcent.flickr.data.remote.model.base.BaseRequest
import com.appcent.flickr.utility.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecentRequest(
    val per_page: Int = 20,
    val page: Int,
    val method: String = Constants.API.GET_RECENT_METHOD
) : Parcelable, BaseRequest() {
}

