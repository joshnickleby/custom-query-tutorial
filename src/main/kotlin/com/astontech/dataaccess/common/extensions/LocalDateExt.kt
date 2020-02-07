package com.astontech.dataaccess.common.extensions

import java.lang.IndexOutOfBoundsException
import java.time.LocalDate


fun LocalDate.fromString(dateString: String): LocalDate {
  return try {
    val splitDate = dateString.split("-")
    val dayTimeSplit = splitDate[2].split("T")

    LocalDate.of(splitDate[0].toInt(), splitDate[1].toInt(), dayTimeSplit[0].toInt())
  } catch (ex: IndexOutOfBoundsException) {
    println("Index out of bounds for: $dateString")
    return LocalDate.now()
  }
}