package com.Cassio.android.softkeyboard

import android.util.TypedValue
import android.view.View

fun View.dpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resources.displayMetrics
    )
}

object Converter {
    fun dpToPx(view: View, dp: Float): Float = view.dpToPx(dp)
}