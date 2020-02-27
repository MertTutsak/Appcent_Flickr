package com.appcent.flickr.ui.main

import com.appcent.flickr.di.ViewModelKey
import com.appcent.flickr.ui.main.view.MainViewModel
import dagger.Module

@Module
abstract class MainActivityModule {

    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: MainViewModel): MainViewModel

}


