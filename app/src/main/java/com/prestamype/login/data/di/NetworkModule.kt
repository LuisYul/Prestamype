package com.prestamype.login.data.di


import com.prestamype.login.data.network.end_points.EndPointsImpl
import com.prestamype.login.data.network.end_points.EndPoints
import com.prestamype.login.data.network.end_points.EndPointsService
import com.prestamype.login.data.network.end_points.SupportInterceptor
import com.prestamype.login.data.network.utils.ConnectionUtils
import com.prestamype.login.data.network.utils.ConnectionUtilsImpl
import com.prestamype.login.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<ConnectionUtils> {
        ConnectionUtilsImpl(
            androidContext()
        )
    }
    factory { SupportInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideApi(get()) }
    single { provideRetrofit(get()) }
    single<EndPoints> { EndPointsImpl(get(), get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(authInterceptor: SupportInterceptor): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
    return builder.build()
}

fun provideApi(retrofit: Retrofit): EndPointsService = retrofit.create(EndPointsService::class.java)