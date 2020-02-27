package com.appcent.flickr.data.remote.service.api

import android.content.Context
import com.appcent.flickr.BuildConfig
import com.appcent.flickr.data.remote.model.response.recent.RecentResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiHelper {

    companion object {

        fun createRetrofit(context: Context): ApiHelper {

            val builder = OkHttpClient.Builder()
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor(LoggingInterceptor())
            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiHelper::class.java)

        }
    }

    @GET("/services/rest/?")
    fun getRecent(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("format") format: String,
        @Query("api_key") api_key: String,
        @Query("nojsoncallback") nojsoncallback: String,
        @Query("method") method: String
    ): Observable<RecentResponse>


}