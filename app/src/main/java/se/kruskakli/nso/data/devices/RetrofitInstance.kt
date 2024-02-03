package se.kruskakli.nso.data.devices

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

// Example: 
//
// val api = RetrofitInstance.getApi("http://10.147.40.166:8080/restconf/data/",
//                                   "admin",
//                                   "admin")
// val packages = api.getNsoDevices()
//
object RetrofitInstance {
    private var api: DevicesApi? = null

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client : OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor)
    }.build()

    private val moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()

    fun getApi(baseUrl: String, user: String, password: String): DevicesApi {
        if (api == null) {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val originalRequest = chain.request()

                    val builder = originalRequest.newBuilder()
                        .header("Authorization", Credentials.basic(user, password))
                        .header("Accept", "application/yang-data+json")

                    val newRequest = builder.build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(interceptor)
                .build()

            api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
                .create(DevicesApi::class.java)
        }

        // In Kotlin, !! is the non-null assertion operator. It converts any
        // value to a non-null type and throws an exception if the value is null.
        return api!!
    }
}

