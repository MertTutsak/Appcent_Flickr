package com.appcent.flickr.data.remote.model.base

import android.os.Parcelable
import com.appcent.flickr.BuildConfig
import com.appcent.flickr.utility.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
open class BaseRequest(
    val format: String = Constants.API.RESPONSE_FORMAT,
    val api_key: String = BuildConfig.FLICKR_API_KEY,
    val nojsoncallback: Int = Constants.API.NO_JSON_CALLBACK
) : Parcelable