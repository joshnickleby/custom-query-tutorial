package com.astontech.dataaccess.common.extensions

import java.time.LocalTime

fun LocalTime.fromString(timeString: String): LocalTime {
  val splitTime = timeString.split(".")[0].split(":")

  return LocalTime.of(splitTime[0].toInt(), splitTime[1].toInt(), splitTime[2].toInt())
}