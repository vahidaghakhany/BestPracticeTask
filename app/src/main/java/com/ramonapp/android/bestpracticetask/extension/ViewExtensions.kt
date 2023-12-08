package com.ramonapp.android.bestpracticetask.extension

import android.view.View

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.hide() {
    this?.visibility = View.INVISIBLE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}