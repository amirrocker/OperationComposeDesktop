package de.amirrocker.operationcomposedesktop.coroutine

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import de.amirrocker.operationcomposedesktop.domain.Unit
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface UnitService {

    @GET("/randomTrainingSession")
    suspend fun getRandomUnit():Response<List<Unit>>

}

@OptIn(ExperimentalSerializationApi::class)
fun createUnitService():UnitService {
    val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().build()
            chain.proceed(request)
        }
        .build()

    val contentType = "application/json".toMediaType()
    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080")
        .addConverterFactory(Json { ignoreUnknownKeys=true }.asConverterFactory(contentType))
//        .addCallAdapterFactory(Rx) // missing the RxJava libraries
        .client(httpClient)
        .build()
    return retrofit.create(UnitService::class.java)
}