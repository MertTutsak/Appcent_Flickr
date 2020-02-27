package com.appcent.flickr.ui.common.base.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.Toast
import com.appcent.flickr.data.remote.service.error.ApiError
import com.appcent.flickr.ui.common.components.progressdialog.LottieProgressDialog
import com.appcent.flickr.utility.extension.showToast
import com.appcent.flickr.ui.common.base.BaseNavigator
import com.appcent.flickr.utility.extension.*
import com.appcent.flickr.utility.provider.AppLanguageProvider
import com.appcent.flickr.utility.wrapper.AppContextWrapper
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseNavigator {

    private var progressDialog: LottieProgressDialog? = null
    open var rootView: ViewGroup? = null

    abstract fun onCreateActivity(savedInstanceState: Bundle?): ViewGroup

    abstract fun bindView()

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)

        setToolbar()

        rootView = onCreateActivity(savedInstanceState)
        bindView()
    }

    private fun setToolbar() {
        this.initStatusBar()
    }

    override fun hideLoading() {
        Handler().postDelayed({
            progressDialog.notNull { it.cancel() }
        }, 100)
    }

    override fun showLoading() {
        hideLoading()
        progressDialog.isNull {
            progressDialog = LottieProgressDialog(this)
        }
        progressDialog?.show()
    }

    override fun showErrorDialog(error: ApiError) {
        progressDialog.notNull { it.cancel() }
        Handler().postDelayed({
            this.applicationContext.showToast("${error.message} ${error.status.code()}", Toast.LENGTH_LONG)
        }, 100)
    }

    private fun performDI() = AndroidInjection.inject(this)

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            AppContextWrapper.wrap(
                newBase,
                AppLanguageProvider.instance.getAppLanguage(newBase)
            )
        )
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(
            AppContextWrapper.wrap(
                applicationContext,
                AppLanguageProvider.instance.getAppLanguage(applicationContext)
            ).resources.configuration
        )
    }
}