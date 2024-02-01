package se.kruskakli.nso.data.packages

import retrofit2.http.GET

interface PackagesApi {
    @GET("tailf-ncs%3Apackages")
    suspend fun getPackages(): NsoPackages
}