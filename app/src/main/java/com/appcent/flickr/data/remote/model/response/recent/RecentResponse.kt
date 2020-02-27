package com.appcent.flickr.data.remote.model.response.recent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.appcent.flickr.data.remote.model.base.BaseResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
class RecentResponse(
    @SerializedName("photos") var photos: Photos? = null
) : Parcelable, BaseResponse()