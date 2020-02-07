package com.astontech.dataaccess.domain

import com.astontech.dataaccess.common.QueryBuilder
import com.astontech.dataaccess.common.ReadWriteDelegate
import com.astontech.dataaccess.common.extensions.fromString
import com.astontech.dataaccess.common.extensions.self
import com.astontech.dataaccess.common.extensions.toZonedDateTime
import org.hibernate.annotations.Type
import java.math.BigDecimal
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*
import kotlin.jvm.Transient
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface DataTable {
  fun setByResult(prop: String, rs: ResultSet)
}

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
) : DataTable {


  override fun setByResult(prop: String, rs: ResultSet) {
    when (prop) {
      "name" -> name = rs.getString(prop)
      "testnum" -> testnum = rs.getInt(prop)
      "longnum" -> longnum = rs.getLong(prop)
      "big_decimal" -> bigDecimal = rs.getBigDecimal(prop)
      "check_flag" -> checkFlag = rs.getBoolean(prop)
      "local_date" -> localDate = LocalDate.now().fromString(rs.getString(prop))
      "zone_date" -> zoneDate = LocalDateTime.now().fromString(rs.getString(prop)).toZonedDateTime()
      else -> id = rs.getInt(prop)
    }
  }

  override fun toString(): String {
    return "{id: $id, name: $name, testnum: $testnum, longnum: $longnum, bigDecimal: $bigDecimal, " +
        "checkFlag: $checkFlag, localDate: $localDate, zoneDate: $zoneDate}"
  }


  companion object {
    fun query(): WhyQuery = WhyQuery()
  }
}

class WhyQuery : QueryBuilder<Why>("why", {Why()}) {
  val name       get(): WhyQuery = self { add("name") }
  val testnum    get(): WhyQuery = self { add("testnum") }
  val longnum    get(): WhyQuery = self { add("longnum") }
  val bigDecimal get(): WhyQuery = self { add("big_decimal") }
  val checkFlag  get(): WhyQuery = self { add("check_flag") }
  val localDate  get(): WhyQuery = self { add("local_date") }
  val zoneDate   get(): WhyQuery = self { add("zone_date") }
  val id         get(): WhyQuery = self { add("id") }
}

//@SuppressWarnings("UNCHECKED_CAST")
//class DatabaseDelegate<U, in R, T>(val builder: QueryBuilder<T>, val mapper: (String) -> ((T, ResultSet) -> T)) :
//    ReadOnlyProperty<R, U>  {
//  override operator fun getValue(thisRef: R, property: KProperty<*>): U {
//    builder.addToSet(property.name, mapper)
//    builder.queryColumns.add(property.name)
//    return builder as U
//  }
//}

@SuppressWarnings("UNCHECKED_CAST")
class DatabaseDelegate<U, in R, T>(val builder: QueryBuilder<T>) :
    ReadOnlyProperty<R, U>  {
  override operator fun getValue(thisRef: R, property: KProperty<*>): U {
    builder.queryColumns.add(property.name)
    return builder as U
  }
}