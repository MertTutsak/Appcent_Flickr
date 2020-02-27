package com.appcent.flickr.di.module

import com.appcent.flickr.app.initializers.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @IntoSet
    @Binds
    abstract fun provideLogger(bind: LoggerInitializer): AppInitializer

}