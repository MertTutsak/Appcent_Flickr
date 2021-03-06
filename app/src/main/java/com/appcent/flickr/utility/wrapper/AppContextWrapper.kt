package com.appcent.flickr.utility.wrapper

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import com.appcent.flickr.utility.provider.AppLanguageProvider
import java.util.*

class AppContextWrapper {

    companion object {
        fun wrap(
            contxt: Context,
            appLang: AppLanguageProvider.LanguageType
        ): ContextWrapper {
            val locale: Locale = when (appLang) {
                AppLanguageProvider.LanguageType.TURKISH -> Locale(
                    AppLanguageProvider.LanguageType.TURKISH.code()
                )
                AppLanguageProvider.LanguageType.ENGLISH -> Locale(
                    AppLanguageProvider.LanguageType.ENGLISH.code()
                )
                else -> Locale(AppLanguageProvider.LanguageType.TURKISH.code())
            }
            Locale.setDefault(locale)

            var context = contxt
            val configuration = context.resources.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale)

                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)

                configuration.setLayoutDirection(locale);

                context = context.createConfigurationContext(configuration)
            } else {
                configuration.locale = locale

                configuration.setLayoutDirection(locale);

                context.resources.updateConfiguration(
                    configuration, context.resources.displayMetrics
                )
            }

            return ContextWrapper(context)
        }
    }

}