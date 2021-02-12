package com.example.composeclub

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.composeclub.ui.theme.pill
import com.example.composeclub.ui.theme.plpBasic
import java.util.*

data class PricingModel(
    val priceYouPay: String,
    val wouldHavePaid: String? = null,
    val savings: String? = null,
)

class PricingModelProvider : PreviewParameterProvider<PricingModel> {
    override val values: Sequence<PricingModel>
        get() = sequenceOf(
            PricingModel(
                priceYouPay = "$4.99/month",
            ),
            PricingModel(
                priceYouPay = "$4.99/month",
                savings = "Save 16%"
            ),
            PricingModel(
                priceYouPay = "$4.99/month",
                wouldHavePaid = "$16.99"
            ),
            PricingModel(
                priceYouPay = "$4.99/month",
                wouldHavePaid = "$16.99",
                savings = "Save 16%"
            ),
        )
}

@Preview
@Composable
fun PlpButton(
    @PreviewParameter(PricingModelProvider::class) pricingModel: PricingModel
) {
    Box() {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = plpBasic
            ),
            modifier = Modifier
                .preferredWidth(width = 350.dp)
                .padding(top = 10.dp),
            onClick = { }
        ) {
            Text(text = buildAnnotatedString {
                append(
                    pricingModel.wouldHavePaid,
                    TextStyle(
                        color = Color.LightGray,
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.Bold,
                    )
                )
                append(
                    pricingModel.priceYouPay,
                    TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                )
            })
        }
        pricingModel.savings?.let {
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
            .preferredHeight(24.dp)
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
