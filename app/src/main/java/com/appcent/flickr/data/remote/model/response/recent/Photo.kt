package com.appcent.flickr.data.remote.model.response.recent

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id") val photoId: String? = null,
    @SerializedName("owner") val ownerId: String? = null,
    @SerializedName("secret") val secretId: String? = null,
    @SerializedName("server") val serverId: String? = null,
    @SerializedName("farm") val farmId: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("ispublic") val ispublic: Int? = null,
    @SerializedName("isfriend") val isfriend: Int? = null,
    @SerializedName("isfamily") val isfamily: Int? = null
) : Parcelable {

    fun getPhotoURL(photoSize: String):String = "https://farm${farmId}.staticflickr.com/${serverId}/${photoId}_${secretId}${photoSize}.jpg"

}