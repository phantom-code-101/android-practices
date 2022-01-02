package com.demo.mysortingcomposewithhilt.ui.currency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.mysortingcomposewithhilt.R
import com.demo.mysortingcomposewithhilt.database.CurrencyInfo

@Composable
fun CurrencyListScreen(currencyList: List<CurrencyInfo>?) {

    val currencyListViewModel: CurrencyListViewModel = viewModel()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        currencyList?.also { items ->
            items(items = items, itemContent = { currencyInfo ->
                CurrencyListRow(currencyInfo = currencyInfo)
            })
        }
    }
}

@Composable
fun CurrencyListRow(currencyInfo: CurrencyInfo) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {

            }
    ) {

        val (indexRefs, titleRefs, symbolRefs, icArrowRefs) = createRefs()

        val name = currencyInfo.name ?: ""
        val symbol = currencyInfo.symbol ?: ""
        Text(text = name.substring(0, 1), Modifier.background(color = Color.LightGray, shape = CircleShape))
        Text(text = name)
        Text(text = symbol)
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_black_24dp),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun CurrencyListRowPreview() {
    val currencyInfo = CurrencyInfo("BTC", "Bitcoin", "BTC")
    CurrencyListRow(currencyInfo = currencyInfo)
}