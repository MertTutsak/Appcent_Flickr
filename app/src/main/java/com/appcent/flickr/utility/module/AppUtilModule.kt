package com.appcent.flickr.utility.module

import com.appcent.flickr.utility.provider.AppLanguageProvider
import com.appcent.flickr.utility.provider.BusProvider
import dagger.Module
import dagger.Provides

@Module
class AppUtilModule {

    @Provides
    internal fun provideAppLanguageProvider(): AppLanguageProvider = AppLanguageProvider.instance

    @Provides
    internal fun provideBusProvider(): BusProvider = BusProvider()

}