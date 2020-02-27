package com.appcent.flickr.ui.common.base

import com.appcent.flickr.data.remote.service.error.ApiError

interface BaseNavigator {
    fun showLoading()
    fun hideLoading()
    fun showErrorDialog(error: ApiError)
}