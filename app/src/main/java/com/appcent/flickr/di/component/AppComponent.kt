package com.appcent.flickr.di.component

import com.appcent.flickr.app.App
import com.appcent.flickr.di.builder.ActivityBuilder
import com.appcent.flickr.di.builder.FragmentBuilder
import com.appcent.flickr.di.module.AppModule
import com.appcent.flickr.utility.module.AppUtilModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        AppUtilModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}