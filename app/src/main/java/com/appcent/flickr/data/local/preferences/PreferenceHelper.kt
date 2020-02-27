package com.appcent.flickr.data.local.preferences

interface PreferenceHelper {
    fun setAppLanguage(language: String?)

    fun getAppLanguage(): String?
}