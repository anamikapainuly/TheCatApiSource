package com.anupras.apl.thecatapisource.hilt


import com.anupras.apl.thecatapisource.BuildConfig
import com.anupras.apl.thecatapisource.api.CatImageApi
import com.anupras.apl.thecatapisource.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Anamika Painuly on 19/09/21.
 */

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    // Define the interceptor, add authentication headers
    private val interceptor = Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder().addHeader("x-api-key: ", BuildConfig.API_KEY).build()
        chain.proceed(newRequest)
    }
    private val builder = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    fun provideRetrofit(): CatImageApi {

        return Retrofit.Builder()
            .client(builder)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CatImageApi::class.java)
    }
}