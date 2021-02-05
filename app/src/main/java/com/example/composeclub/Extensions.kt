package com.example.composeclub

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle


// Example usage:
//
//              text = buildAnnotatedString {
//                    append(caption, Typography.Caption_Small)
//                    append(credits, Typography.Credit)
//                }
fun AnnotatedString.Builder.append(text: String?, style: TextStyle, separator: String = " ") {
    if (!text.isNullOrEmpty()) {
        pushStyle(style.toSpanStyle())
        if (length > 0) {
            append(separator)
        }
        append(text)
        pop()
    }
}