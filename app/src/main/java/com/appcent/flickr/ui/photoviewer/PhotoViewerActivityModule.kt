package com.appcent.flickr.ui.photoviewer

import com.appcent.flickr.di.ViewModelKey
import dagger.Module

@Module
abstract class PhotoViewerActivityModule {

    @ViewModelKey(PhotoViewerModel::class)
    internal abstract fun providePhotoViewerModel(viewModel: PhotoViewerModel): PhotoViewerModel

}


