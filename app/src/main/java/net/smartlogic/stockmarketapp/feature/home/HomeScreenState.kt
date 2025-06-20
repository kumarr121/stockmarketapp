package net.smartlogic.stockmarketapp.feature.home

import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.SymbolsData
import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersui.TopGainersLosersUIData

data class HomeScreenState(
    val topLosersGainers: List<TopGainersLosersUIData> = emptyList(),
    val showMySymbolsPopUp: Boolean = false,
    val symbolsList: List<SymbolsData> = emptyList()
)
