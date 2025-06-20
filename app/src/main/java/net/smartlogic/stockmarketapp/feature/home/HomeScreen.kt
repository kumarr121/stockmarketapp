package net.smartlogic.stockmarketapp.feature.home

import net.smartlogic.stockmarketapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.smartlogic.stockmarketapp.feature.home.models.gainerslosers.gainerslosersui.TopGainersLosersUIData

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel<HomeViewModel>()

    val screenState = homeViewModel.screenState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // my symbols text and arrow icon
                Row(
                    modifier = Modifier
                        .clickable {
                            homeViewModel.toggleMySymbolsPopUpVisibility()
                        }
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "My Symbols",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(
                        painter = painterResource(R.drawable.icon_code),
                        modifier = Modifier
                            .size(size = 16.dp)
                            .rotate(degrees = 90f),
                        contentDescription = "my symbols",
                        tint = Color.DarkGray
                    )
                }

                // my symbols pop up
                DropdownMenu(
                    modifier = Modifier,
                    expanded = screenState.showMySymbolsPopUp,
                    offset = DpOffset(x = 1.dp, y = 1.dp),
                    onDismissRequest = {
                       homeViewModel.toggleMySymbolsPopUpVisibility()
                    }
                ) {
                    screenState.symbolsList.forEach { symbolData ->
                       Row {
//                          if (symbolData.isSelected) {
//                              Icon(
//                                  painter = painterResource(R.drawable.icon_done),
//                                  contentDescription = "",
//                                  tint = Color.DarkGray
//                              )
//                          }
                           DropdownMenuItem(
                               text = {
                                   Text(symbolData.symbolName)
                               },
                               onClick = {
                                   homeViewModel.mySymbolsClick(symbolData.symbolId)
                               }
                           )
                       }
                    }

                    HorizontalDivider()

                    DropdownMenuItem(
                        text = {
                            Text("Manage Watchlist")
                        },
                        onClick = {
                            homeViewModel.mySymbolManageWatchListClick()
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text("New Watchlist")
                        },
                        onClick = {
                            homeViewModel.mySymbolNewWatchListClick()
                        }
                    )
                }
            }
        }

        items(items = screenState.topLosersGainers) { item ->
            ListItemUI(topGainersLosersUIItem = item)
        }
    }
}

@Composable
fun ListItemUI(topGainersLosersUIItem: TopGainersLosersUIData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            // company code and description
            Column(
                modifier = Modifier.weight(weight = 1f) // it occupies the maximum available space
            ) {
                Text(
                    text = topGainersLosersUIItem.companyCode,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = topGainersLosersUIItem.companyDescription,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }

            //chart
            Box {

            }

            // values
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = topGainersLosersUIItem.price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier
                        .background(
                            color = topGainersLosersUIItem.color,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    text = topGainersLosersUIItem.gainOrLoseValue,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HomeScreen()
}