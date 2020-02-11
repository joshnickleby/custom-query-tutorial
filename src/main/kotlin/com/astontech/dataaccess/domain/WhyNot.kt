package com.astontech.dataaccess.domain

import com.astontech.dataaccess.common.QueryBuilder
import com.astontech.dataaccess.common.extensions.self
import java.sql.ResultSet
import javax.persistence.*


@Entity
@Table(name = "why_not")
class WhyNot(
    var name: String? = null,
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Int? = null
) : DataTable {
  override fun setByResult(prop: String, rs: ResultSet) {
    when (prop) {
      "name" -> name = rs.getString(prop)
      else   -> id = rs.getInt(prop)
    }
  }
}

class WhyNotQuery : QueryBuilder<WhyNot, WhyNotQuery>("why_not", {WhyNot()}) {
  val name get(): WhyNotQuery = self { add("name") }
  val id   get(): WhyNotQuery = self { add("id")   }
}