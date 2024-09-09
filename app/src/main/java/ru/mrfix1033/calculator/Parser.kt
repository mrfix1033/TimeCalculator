package ru.mrfix1033.calculator

import kotlin.IllegalArgumentException

fun parse(_time: String) : Int {
    val time = _time.lowercase()
    var seconds = 0
    var tempValue = 0
    var lastCharIsUnit = true  // true во избежание s1d2h
    for (c in time) {
        if (c.isDigit()) {
            tempValue = tempValue * 10 + c.digitToInt()
            lastCharIsUnit = false
        }
        else {
            if (lastCharIsUnit) throw IllegalArgumentException("Некорректный синтаксис")  // 1sm
            seconds += tempValue * when (c) {
                's' -> 1
                'm' -> 60
                'h' -> 3600
                'd' -> 86400
                else -> throw IllegalArgumentException("Некорректная единица измерения \'$c\'")
            }
            lastCharIsUnit = true
            tempValue = 0
        }
    }
    if (!lastCharIsUnit)  // 1m0
        throw IllegalArgumentException("Некорректный синтаксис")
    return seconds
}

fun encode(_seconds: Int): String {
    val isNegative = _seconds < 0
    var seconds = if (isNegative) -_seconds else _seconds
    val days = seconds / 86400
    val hours = seconds % 86400 / 3600
    val minutes = seconds % 3600 / 60
    seconds %= 60
    return buildString {
        if (isNegative) append('-')
        if (days != 0) append("${days}d")
        if (hours != 0) append("${hours}h")
        if (minutes != 0) append("${minutes}m")
        if (seconds != 0) append("${seconds}s")
    }
}