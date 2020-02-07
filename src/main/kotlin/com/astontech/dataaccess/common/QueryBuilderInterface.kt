package com.astontech.dataaccess.common

interface QueryBuilderInterface<T> {
  val name: String
  val joinInfo: String
  val supplier: (query: String) -> T
  fun build(): List<QueryMeta<T>>
}