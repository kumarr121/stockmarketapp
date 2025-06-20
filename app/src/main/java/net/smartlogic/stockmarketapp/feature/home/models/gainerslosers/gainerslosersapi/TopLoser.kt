package net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersapi


import com.google.gson.annotations.SerializedName

data class TopLoser(
    @SerializedName("change_amount")
    val changeAmount: String? = null,
    @SerializedName("change_percentage")
    val changePercentage: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("ticker")
    val ticker: String? = null,
    @SerializedName("volume")
    val volume: String? = null
)