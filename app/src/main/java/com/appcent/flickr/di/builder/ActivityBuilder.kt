package com.appcent.flickr.di.builder

import com.appcent.flickr.ui.main.MainActivityModule
import com.appcent.flickr.ui.main.view.MainActivity
import com.appcent.flickr.ui.photoviewer.PhotoViewerActivity
import com.appcent.flickr.ui.photoviewer.PhotoViewerActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(PhotoViewerActivityModule::class)])
    abstract fun bindPhotoViewerActivity(): PhotoViewerActivity

}