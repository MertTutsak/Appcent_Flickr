package com.appcent.flickr.app.initializers

import android.app.Application

interface AppInitializer {
    fun init(application: Application)
}
