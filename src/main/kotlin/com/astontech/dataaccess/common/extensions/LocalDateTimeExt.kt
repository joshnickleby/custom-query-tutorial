package com.astontech.dataaccess.common.extensions

import java.sql.Timestamp
import java.time.*

fun LocalDateTime.fromString(dateTimeString: String): LocalDateTime {
  val splitDateTime = dateTimeString.split(" ")

  val date = LocalDate.now().fromString(splitDateTime[0])
  val time = LocalTime.now().fromString(splitDateTime[1])

  return LocalDateTime.of(date, time)
}

fun LocalDateTime.toZonedDateTime(): ZonedDateTime = ZonedDateTime.of(this, ZoneId.of("America/Chicago"))