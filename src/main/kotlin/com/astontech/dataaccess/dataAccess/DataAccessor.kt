package com.astontech.dataaccess.dataAccess

import com.astontech.dataaccess.common.QueryBuilder
import com.astontech.dataaccess.domain.DataTable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DataAccessor(private val jdbcTemplate: JdbcTemplate) {

  fun <T : DataTable> get(queryBuilder: QueryBuilder<T>, blankEntitySp: () -> T): List<T>? =
    jdbcTemplate.query(queryBuilder.build(), TestMapper(queryBuilder, blankEntitySp)).toList()
}

class TestMapper<T : DataTable>(private val queryBuilder: QueryBuilder<T>, private val blankEntitySp: () -> T) : RowMapper<T> {
  override fun mapRow(rs: ResultSet, p1: Int): T? {
    val obj = blankEntitySp()

    queryBuilder.queryColumns.forEach { obj.setByResult(it, rs) }

    return obj
  }
}