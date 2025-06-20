package net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersui

import androidx.compose.ui.graphics.Color

data class TopGainersLosersUIData(
    val companyCode: String = "AAPL",
    val companyDescription: String = "Apple inc.",
    val price: String = "123.33",
    val gainOrLoseValue: String = "4.99",
    val isGain: Boolean = true,
    val color: Color = Color.Green
)
