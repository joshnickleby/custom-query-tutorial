package com.astontech.dataaccess.common.extensions

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

fun <T> T.self(fn: () -> Unit): T { fn(); return this }

fun <T> Any.merge(other: Any): T {
  Any::class.memberProperties.forEach {
    val property = other::class.memberProperties.first { prop -> it.name == prop.name }

    if (property is KMutableProperty<*>) {
      property.setter.call(it.name, it.get(this))
    }
  }

  @Suppress("UNCHECKED_CAST")
  return other as T
}