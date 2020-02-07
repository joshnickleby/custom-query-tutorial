package com.astontech.dataaccess.config

import com.astontech.dataaccess.dataAccess.DataAccessor
import com.astontech.dataaccess.domain.Why
import com.astontech.dataaccess.domain.WhyQuery
import com.astontech.dataaccess.repos.WhyRepo
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

@Component
class DbInitializer(private val WhyRepo: WhyRepo, private val dataAccessor: DataAccessor) : ApplicationListener<ContextRefreshedEvent> {

  override fun onApplicationEvent(p0: ContextRefreshedEvent) {
    val listOfWhys = listOf(
        Why(name = "a", testnum = 0, longnum = 0L, bigDecimal = BigDecimal.ZERO, checkFlag = true, localDate = LocalDate.now(), zoneDate = ZonedDateTime.now()),
        Why(name = "b", testnum = 1, longnum = 1L, bigDecimal = BigDecimal(1), checkFlag = false, localDate = LocalDate.now().plusDays(1), zoneDate = ZonedDateTime.now().minusDays(1)),
        Why(name = "c", testnum = 2, longnum = 2L, bigDecimal = BigDecimal(2), checkFlag = false, localDate = LocalDate.now().plusDays(2), zoneDate = ZonedDateTime.now().minusDays(2)),
        Why(name = "d", testnum = 3, longnum = 3L, bigDecimal = BigDecimal(3), checkFlag = false, localDate = LocalDate.now().plusDays(3), zoneDate = ZonedDateTime.now().minusDays(3)),
        Why(name = "e", testnum = 4, longnum = 4L, bigDecimal = BigDecimal(4), checkFlag = false, localDate = LocalDate.now().plusDays(4), zoneDate = ZonedDateTime.now().minusDays(4)),
        Why(name = "f", testnum = 5, longnum = 5L, bigDecimal = BigDecimal(5), checkFlag = false, localDate = LocalDate.now().plusDays(5), zoneDate = ZonedDateTime.now().minusDays(5)),
        Why(name = "g", testnum = 6, longnum = 6L, bigDecimal = BigDecimal(6), checkFlag = false, localDate = LocalDate.now().plusDays(6), zoneDate = ZonedDateTime.now().minusDays(6)),
        Why(name = "h", testnum = 7, longnum = 7L, bigDecimal = BigDecimal(7), checkFlag = false, localDate = LocalDate.now().plusDays(7), zoneDate = ZonedDateTime.now().minusDays(7)),
        Why(name = "i", testnum = 8, longnum = 8L, bigDecimal = BigDecimal(8), checkFlag = false, localDate = LocalDate.now().plusDays(8), zoneDate = ZonedDateTime.now().minusDays(8)),
        Why(name = "j", testnum = 9, longnum = 9L, bigDecimal = BigDecimal(9), checkFlag = false, localDate = LocalDate.now().plusDays(9), zoneDate = ZonedDateTime.now().minusDays(9)),
        Why(name = "k", testnum = 10, longnum = 10L, bigDecimal = BigDecimal(10), checkFlag = false, localDate = LocalDate.now().plusDays(10), zoneDate = ZonedDateTime.now().minusDays(10))
    )

    WhyRepo.saveAll(listOfWhys)

    WhyRepo.findAll().forEach {
//      println(it)
    }

    val query = Why.query()
        .name
        .testnum.bigDecimal.checkFlag.id.longnum.localDate.zoneDate

    val whys = this.dataAccessor.get(query) {Why()}

    whys?.forEach { println(it) }
  }
}