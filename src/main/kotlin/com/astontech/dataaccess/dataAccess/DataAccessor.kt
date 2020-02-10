package com.astontech.dataaccess.dataAccess

import com.astontech.dataaccess.common.QueryBuilder
import com.astontech.dataaccess.domain.DataTable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DataAccessor(private val jdbcTemplate: JdbcTemplate) {

  fun <T : DataTable, U> get(queryBuilder: QueryBuilder<T, U>, blankEntitySp: () -> T): List<T>? =
    jdbcTemplate.query(queryBuilder.build(), TestMapper(queryBuilder, blankEntitySp)).toList()
}

class TestMapper<T : DataTable, U>(private val queryBuilder: QueryBuilder<T, U>, private val blankEntitySp: () -> T) : RowMapper<T> {
  override fun mapRow(rs: ResultSet, p1: Int): T? {
    val obj = blankEntitySp()

    queryBuilder.queryColumns.forEach { obj.setByResult(it, rs) }

    return obj
  }
}