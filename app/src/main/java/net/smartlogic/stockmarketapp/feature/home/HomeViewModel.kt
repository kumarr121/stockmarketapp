package net.smartlogic.stockmarketapp.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.smartlogic.stockmarketapp.api.ApiInterface
import net.smartlogic.stockmarketapp.api.ApiService
import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.SymbolsData
import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersui.TopGainersLosersUIData
import net.smartlogic.stockmarketapp.util.Constants

class HomeViewModel: ViewModel() {
    private val TAG = javaClass.simpleName as String

    private val _screenState = mutableStateOf(HomeScreenState())

    val screenState: State<HomeScreenState> = _screenState

   init {
       viewModelScope.launch(Dispatchers.Default) {
           prepareSymbolsList()
           prepareTopGainersLosers()
       }
   }

    fun prepareSymbolsList() {
            val list = mutableListOf<SymbolsData>()

            list.add(SymbolsData(
                symbolName = "My Symbols",
                isSelected = true
            ))

            list.add(SymbolsData(
                symbolName = "AEROSPACE",
                isSelected = false
            ))

            _screenState.value = _screenState.value.copy(
                symbolsList = list
            )
    }

    suspend fun prepareTopGainersLosers() {
            val retrofitService =
                ApiService().getRetrofitClient()?.create(ApiInterface::class.java)

            val response = retrofitService?.getTopGainersLosers()

            if (response?.isSuccessful == true) {
                val uiList = mutableListOf<TopGainersLosersUIData>()

                val topGainers = response.body()?.topGainers
                val topLosers = response.body()?.topLosers

                topGainers?.forEach { topGainer ->
                    uiList.add(
                        TopGainersLosersUIData(
                            companyCode = topGainer?.ticker ?: Constants.nullPlaceHolder,
                            companyDescription = "description here",
                            price = "+" + (topGainer?.price ?: Constants.nullPlaceHolder),
                            gainOrLoseValue = topGainer?.changeAmount ?: Constants.nullPlaceHolder,
                            isGain = true,
                            color = Color.Companion.Green
                        )
                    )
                }

                topLosers?.forEach { topLoser ->
                    uiList.add(
                        TopGainersLosersUIData(
                            companyCode = topLoser?.ticker ?: Constants.nullPlaceHolder,
                            companyDescription = "description here",
                            price = topLoser?.price ?: Constants.nullPlaceHolder,
                            gainOrLoseValue = topLoser?.changeAmount ?: Constants.nullPlaceHolder,
                            isGain = false,
                            color = Color.Companion.Red
                        )
                    )
                }

                _screenState.value = _screenState.value.copy(
                    topLosersGainers = uiList
                )
            } else {
                // todo handle the error here
            }
    }

    fun toggleMySymbolsPopUpVisibility() {
        viewModelScope.launch {
            _screenState.value = _screenState.value.copy(
                showMySymbolsPopUp = !_screenState.value.showMySymbolsPopUp
            )
        }
    }

    fun mySymbolsClick(symbolId: Int) {

        toggleMySymbolsPopUpVisibility()
    }

    fun mySymbolAeroSpaceClick() {
        toggleMySymbolsPopUpVisibility()

    }

    fun mySymbolManageWatchListClick() {
        toggleMySymbolsPopUpVisibility()

    }

    fun mySymbolNewWatchListClick() {
        toggleMySymbolsPopUpVisibility()

    }

    override fun onCleared() {
        super.onCleared()
    }

}