package com.paf.unsplashsample.di

import com.paf.unsplashsample.api.ApiClient
import com.paf.unsplashsample.repo.MainRepo
import com.paf.unsplashsample.ui.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val networkModule = module {
    single {
        provideHttpClient(get())
    }

    single {
        provideRetrofit(get())
    }

    single {
        provideApiClient(get())
    }

    single {
        provideLoggingInterceptor()
    }
}

private val repositoryModule = module {
    single { MainRepo(get()) }
}

private val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
   return Retrofit.Builder()
       .addConverterFactory(GsonConverterFactory.create())
       .baseUrl("https://api.unsplash.com")
       .client(okHttpClient)
       .build()
}

private fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(loggingInterceptor)
        .build()
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return loggingInterceptor
}

private fun provideApiClient(retrofit: Retrofit): ApiClient {
    return retrofit.create(ApiClient::class.java)
}

val listOfModules = listOf(networkModule, repositoryModule, viewModelModule)

