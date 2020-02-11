package com.astontech.dataaccess.dataAccess

import com.astontech.dataaccess.common.QueryBuilder
import com.astontech.dataaccess.domain.DataTable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class DataAccessor(private val jdbcTemplate: JdbcTemplate) {

  fun <ENTITY : DataTable, QUERY> getTopLayer(queryBuilder: QueryBuilder<ENTITY, QUERY>, blankEntitySp: () -> ENTITY): List<ENTITY>? =
    jdbcTemplate.query(queryBuilder.build(), SingleLayerMapper(queryBuilder, blankEntitySp)).toList()
}

class SingleLayerMapper<ENTITY : DataTable, QUERY>(private val queryBuilder: QueryBuilder<ENTITY, QUERY>,
                                                   private val blankEntitySp: () -> ENTITY) : RowMapper<ENTITY> {
  override fun mapRow(rs: ResultSet, p1: Int): ENTITY? {
    val obj = blankEntitySp()

    queryBuilder.queryColumns.forEach { obj.setByResult(it, rs) }

    return obj
  }
}