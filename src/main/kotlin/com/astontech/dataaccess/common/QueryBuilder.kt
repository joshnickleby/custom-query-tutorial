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
abstract class QueryBuilder<ENTITY, SELF>(var tableName: String, var supplier: (query: String) -> ENTITY) {

  /** Stores the query prop name and the getter function passed down */
  protected val querySet = HashSet<QueryMeta<ENTITY>>()

  val queryColumns = HashSet<String>()

  var whereClauses = HashSet<String>()

  var joinInfo = ""

  var order: String = ""

  var hasOrder: Boolean = false

  var ascending: Boolean = true

  fun build(): String {
    val columns = queryColumns.joinToString(", ") { "$tableName.$it" }

    val where = if (whereClauses.isNotEmpty()) "where " + whereClauses.joinToString(" ") { "$tableName.$it" } else ""

    val order = if (order != "") {
      val direction = if (ascending) "asc" else "desc"

      "order by $tableName.$order $direction"
    } else ""

    return "select $columns from $tableName $tableName $where $order"
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
    if (this.hasOrder) {
      this.order = propName
      this.hasOrder = false
    } else {
      this.queryColumns.add(propName)
    }
  }

  @SuppressWarnings("UNCHECKED_CAST")
  fun orderBy(): SELF {
    this.hasOrder = true

    return this as SELF
  }

  @SuppressWarnings("UNCHECKED_CAST")
  fun desc(): SELF {
    this.ascending = false

    return this as SELF
  }
}


class WhereSet(val preposition: String, val condition: String, val assertion: String)

/**
 *  Extended by a specific where clause. T refers to the database object
 *  and U refers to the class extending QueryBuilder.
 *
 *  Pass in the query builder extended class so that it gets returned on build.
 *  This allows for multiple where clauses.
 *
 *  eg.
 *    val query = Why.query()
 *                      .name
 *                      .testnum
 *                      .bigDecimal
 *                      .checkFlag
 *                      .id
 *                      .longnum
 *                      .localDate
 *                      .zoneDate
 *                        .where()
 *                          .name.equals("a")   // defined in extended class
 *                        .build()
 */
abstract class WhereClause<ENTITY, QUERY : QueryBuilder<ENTITY, QUERY>>(private val query: QUERY) {
  val statementArguments = HashSet<WhereSet>()

  var tempColumn: String = ""

  fun build(): QUERY {
    query.whereClauses = statementArguments.map {
      "${it.preposition} ${it.condition} ${it.assertion}"
    }.toHashSet()

    return query
  }

  fun set(value: String) {
    tempColumn = value
  }
}