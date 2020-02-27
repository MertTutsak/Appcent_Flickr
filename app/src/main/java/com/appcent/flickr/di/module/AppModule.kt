package com.appcent.flickr.di.module

import android.content.Context
import com.appcent.flickr.app.App
import com.appcent.flickr.BuildConfig
import com.appcent.flickr.data.local.preferences.AppPreferenceHelper
import com.appcent.flickr.data.local.preferences.PreferenceHelper
import com.appcent.flickr.di.ApiKeyInfo
import com.appcent.flickr.di.PreferenceInfo
import com.appcent.flickr.data.remote.service.api.ApiHelper
import com.appcent.flickr.data.remote.service.api.ApiHelperImp
import com.appcent.flickr.data.manager.DataManagerImp
import com.appcent.flickr.utility.Constants
import com.appcent.flickr.utility.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.X_APP_KEY

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = Constants.App.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper =
        appPreferenceHelper

    @Provides
    @Singleton
    fun providesRemoteService(context: Context): ApiHelper {
        return ApiHelper.createRetrofit(context)
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun providesFetchUseCase(context: Context): DataManagerImp {
        return DataManagerImp(
            provideCompositeDisposable(),
            ApiHelperImp(providesRemoteService(context)),
            providePrefHelper(
                AppPreferenceHelper(context, provideprefFileName())
            )
        )
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


}