package com.example.composeclub

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeclub.ui.theme.pill
import com.example.composeclub.ui.theme.plpBasic
import java.util.*

sealed class PricingModel
data class ButtonModel(
    val priceYouPay: String,
    val wouldHavePaid: String? = null,
    val savings: String? = null,
) : PricingModel()

object Offline : PricingModel()

class PricingModelProvider : PreviewParameterProvider<ButtonModel> {
    override val values: Sequence<ButtonModel>
        get() = sequenceOf(
            ButtonModel(
                priceYouPay = "$4.99/month",
            ),
            ButtonModel(
                priceYouPay = "$4.99/month",
                savings = "Save 16%"
            ),
            ButtonModel(
                priceYouPay = "$4.99/month",
                wouldHavePaid = "$16.99"
            ),
            ButtonModel(
                priceYouPay = "$4.99/month",
                wouldHavePaid = "$16.99",
                savings = "Save 16%"
            ),
        )
}

@Composable
fun PlpScreen() {
    val viewModel = viewModel(modelClass = MainViewModel::class.java)
    val pricingModel = viewModel.queryPricingModel.collectAsState()
    Crossfade(targetState = pricingModel.value) {
        when (val value = pricingModel.value) {
            Offline -> {
                Text(text = "You are offline ya scrub")
            }
            is ButtonModel -> {
                PlpButton(buttonModel = value)
            }
        }
    }
}

@Preview
@Composable
fun PlpButton(
    @PreviewParameter(PricingModelProvider::class) buttonModel: ButtonModel
) {
    Box() {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = plpBasic
            ),
            modifier = Modifier
                .width(width = 350.dp)
                .padding(top = 10.dp),
            onClick = { }
        ) {
            Text(text = buildAnnotatedString {
                append(
                    buttonModel.wouldHavePaid,
                    TextStyle(
                        color = Color.LightGray,
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.Bold,
                    )
                )
                append(
                    buttonModel.priceYouPay,
                    TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                )
            })
        }
        buttonModel.savings?.let {
            SavingsPill(
                it,
                Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun SavingsPill(
    savings: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = savings.toUpperCase(Locale.getDefault()),
        fontWeight = FontWeight.Bold,
        color = plpBasic,
        modifier = modifier
            .height(24.dp)
            .border(
                width = 2.dp,
                color = plpBasic,
                shape = pill
            )
            .clip(shape = pill)
            .background(color = Color.White)
            .padding(horizontal = 8.dp, vertical = 2.dp)
    )
}

@Preview
@Composable
private fun PreviewSavingsPill() {
    SavingsPill(savings = "Save 16%")
}
