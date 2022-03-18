package com.testing.application.util

import java.text.DecimalFormat

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun Int?.yearString(): String {
    return this?.let {
        "$this"
    } ?: ""
}

fun Double?.ratingString(): String {
    return this?.let {
        val numberFormat = DecimalFormat("#,##0.00")
        numberFormat.format(this)
    } ?: "-"
}