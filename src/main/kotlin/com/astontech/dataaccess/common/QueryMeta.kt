package com.astontech.dataaccess.common

import com.astontech.dataaccess.common.extensions.self
import java.sql.ResultSet

class QueryMeta<T>(
    var columnName: String? = null,
    var mapperFn: ((T, ResultSet) -> T)? = null
) {
  fun columnName(columnName: String): QueryMeta<T> = self { this.columnName = columnName }
  fun mapperFn(mapperFn: (T, ResultSet) -> T): QueryMeta<T> = self { this.mapperFn = mapperFn }
}
