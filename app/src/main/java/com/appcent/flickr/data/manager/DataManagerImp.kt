package com.appcent.flickr.data.manager

import com.appcent.flickr.data.local.preferences.PreferenceHelper
import com.appcent.flickr.data.remote.service.api.ApiHelperImp
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DataManagerImp @Inject internal constructor(
    val compositeDisposable: CompositeDisposable,
    val apiHelperImp: ApiHelperImp,
    val preferenceHelper: PreferenceHelper
) : DataManager