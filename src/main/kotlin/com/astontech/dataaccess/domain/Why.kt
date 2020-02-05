package com.astontech.dataaccess.domain

import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "why")
class Why(
    var name: String? = null,
    var testnum: Int? = null,
    var longnum: Long? = null,
    var bigDecimal: BigDecimal? = null,
    var checkFlag: Boolean = false,
    var localDate: LocalDate? = null,
    var zoneDate: ZonedDateTime? = null,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Int? = null
) {
  override fun toString(): String {
    return "{id: $id, name: $name, testnum: $testnum, longnum: $longnum, bigDecimal: $bigDecimal, " +
        "checkFlag: $checkFlag, localDate: $localDate, zoneDate: $zoneDate}"
  }
}

