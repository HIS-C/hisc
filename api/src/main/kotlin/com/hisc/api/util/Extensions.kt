package com.hisc.api.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.util.TimeZone

val FORMAT_YMD: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun Long.toLocalDateTime(): LocalDateTime? {
    return if (this == 0L) null
    else Instant.ofEpochMilli(this).atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime()
}

fun Temporal.toYmd(): String {
    return FORMAT_YMD.format(this)
}

fun Temporal.between(strYmd: String, endYmd: String): Boolean {
    return this.toYmd() in strYmd..endYmd
}
