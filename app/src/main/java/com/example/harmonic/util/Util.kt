package com.example.harmonic.util

import android.text.format.DateUtils
import java.time.Duration

fun Duration.toDisplayString(): String {
    return DateUtils.formatElapsedTime(this.seconds)
}