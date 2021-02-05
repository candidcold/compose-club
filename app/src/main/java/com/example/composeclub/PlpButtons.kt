package com.example.composeclub

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeclub.ui.theme.ComposeClubTheme
import com.example.composeclub.ui.theme.pill
import com.example.composeclub.ui.theme.plpBasic
import java.util.*

@Composable
fun PlpButton(
    priceYouPay: String,
    wouldHavePaid: String? = null
) {
    Column {
        SavingsPill()
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = plpBasic
            ),
            modifier = Modifier.preferredWidth(width = 350.dp),
            onClick = { }
        ) {
            Text(text = buildAnnotatedString {
                append(
                    wouldHavePaid,
                    TextStyle(
                        color = Color.LightGray,
                        textDecoration = TextDecoration.LineThrough,
                        fontWeight = FontWeight.Bold,
                    )
                )
                append(
                    priceYouPay,
                    TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                )
            })
        }
    }
}

@Preview
@Composable
fun SavingsPill() {
    Text(
        text = "Save 16%".toUpperCase(Locale.getDefault()),
        fontWeight = FontWeight.Bold,
        color = plpBasic,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = plpBasic,
                shape = pill
            )
            .clip(shape = pill)
            .background(color = Color.White)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    )
}

@Preview
@Composable
private fun PreviewButton() {
    ComposeClubTheme {
        PlpButton(priceYouPay = "$4.99/month")
    }
}

@Preview
@Composable
private fun PreviewButton2() {
    ComposeClubTheme {
        PlpButton(
            priceYouPay = "$4.99/month",
            wouldHavePaid = "$16.99"
        )
    }
}