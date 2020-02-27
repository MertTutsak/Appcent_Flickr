package com.appcent.flickr.data.remote.model.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseResponse(
    @SerializedName("stat") val stat: String? = null,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("message") val message: String? = null
) : Parcelable {
    fun isSuccess() = stat.equals("ok")


}