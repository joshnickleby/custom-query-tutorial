package com.astontech.dataaccess.common

import com.astontech.dataaccess.common.extensions.fromString
import com.astontech.dataaccess.common.extensions.toZonedDateTime
import java.math.BigDecimal
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime

/**
 *  The top level class for a query builder.
 *
 *  The supplier creates a blank object to add values to based on the prop name.
 *
 *  ChildQueryBuilder : QueryBuilder<Object> ("object", { Object(it) })
 */
abstract class QueryBuilder<T>(var tableName: String, var supplier: (query: String) -> T) {

  /** Stores the query prop name and the getter function passed down */
  protected val querySet = HashSet<QueryMeta<T>>()

  val queryColumns = HashSet<String>()

  var whereClauses = HashSet<String>()

  var joinInfo = ""

  fun build(): String {
    val columns = queryColumns.joinToString(", ") { "$tableName.$it" }

    val where = if (whereClauses.isNotEmpty()) "where " + whereClauses.joinToString(" ") { "$tableName.$it" } else ""

    return "select $columns from $tableName $tableName $where"
  }


  /** GETTERS FOR RESULT SET **/
  fun getBigDecimal(rs: ResultSet, propName: String): BigDecimal = rs.getBigDecimal(propName)

  fun getBoolean(rs: ResultSet, propName: String): Boolean = rs.getBoolean(propName)

  fun getInt(rs: ResultSet, propName: String): Int = rs.getInt(propName)

  fun getLocalDate(rs: ResultSet, propName: String): LocalDate = LocalDate.now()
      .fromString(rs.getString(propName))

  fun getString(rs: ResultSet, propName: String): String = rs.getString(propName)

  fun getZoneDateTime(rs: ResultSet, propName: String): ZonedDateTime = LocalDateTime.now()
      .fromString(rs.getString(propName))
      .toZonedDateTime()

  fun add(propName: String) {
    this.queryColumns.add(propName)
  }
}


class WhereSet(val preposition: String, val condition: String, val assertion: String)

abstract class WhereClause<T, U: QueryBuilder<T>>(val query: U) {
  val statementArguments = HashSet<WhereSet>()

  var tempColumn: String = ""

  fun build(): U {
    query.whereClauses = statementArguments.map {
      "${it.preposition} ${it.condition} ${it.assertion}"
    }.toHashSet()

    return query
  }
}