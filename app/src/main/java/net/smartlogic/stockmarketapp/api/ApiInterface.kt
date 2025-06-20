package net.smartlogic.stockmarketapp.api

import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersapi.TopGainersLosersAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("query")
    suspend fun getTopGainersLosers(
        @Query("function") function: String = "TOP_GAINERS_LOSERS",
        @Query("apikey") apiKey: String = "abc", //todo encrypt this using Android Keystore
    ): Response<TopGainersLosersAPIResponse?>?
}