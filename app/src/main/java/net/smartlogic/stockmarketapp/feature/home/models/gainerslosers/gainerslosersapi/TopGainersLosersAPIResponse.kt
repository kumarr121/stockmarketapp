package net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersapi


import com.google.gson.annotations.SerializedName

data class TopGainersLosersAPIResponse(
    @SerializedName("last_updated")
    val lastUpdated: String? = null,
    @SerializedName("metadata")
    val metadata: String? = null,
    @SerializedName("most_actively_traded")
    val mostActivelyTraded: List<MostActivelyTraded?>? = null,
    @SerializedName("top_gainers")
    val topGainers: List<TopGainer?>? = null,
    @SerializedName("top_losers")
    val topLosers: List<TopLoser?>? = null
)