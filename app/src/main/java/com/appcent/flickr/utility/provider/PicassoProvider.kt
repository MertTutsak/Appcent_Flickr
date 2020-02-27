package com.appcent.flickr.utility.provider

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

object PicassoProvider {
    @Volatile
    private var singleton: Picasso? = null


    fun with(context: Context): Picasso {
        if (singleton == null) {
            val client = OkHttpClient.Builder().build()
            singleton = Picasso.Builder(context).downloader(OkHttp3Downloader(client)).build()
        }
        return singleton!!
    }
}
