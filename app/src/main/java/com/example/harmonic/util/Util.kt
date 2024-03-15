package com.example.harmonic.util

import java.time.Duration

fun Duration.toDisplayString(): String {
    val s = this.seconds
    return String.format("%02d:%02d:%02d", s / 3600, (s % 3600) / 60, s % 60)
}