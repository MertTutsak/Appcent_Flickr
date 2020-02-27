package com.appcent.flickr.data.remote.model.response.recent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("pages") val totalPages: Int? = null,
    @SerializedName("perpage") val perpage: Int? = null,
    @SerializedName("total") val total: Int? = null,
    @SerializedName("photo") val photo: ArrayList<Photo>? = null
) : Parcelable {
    fun isLastPage(): Boolean = totalPages ?: 0 <= page ?: 0
    fun isFirstPage(): Boolean = 0 == page
}