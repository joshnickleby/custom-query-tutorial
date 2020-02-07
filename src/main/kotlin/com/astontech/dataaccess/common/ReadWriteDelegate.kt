package com.astontech.dataaccess.common

import kotlin.reflect.KProperty

public interface ReadWriteDelegate<in R, T> {
  /**
   * Returns the value of the property for the given object.
   * @param thisRef the object for which the value is requested.
   * @param property the metadata for the property.
   * @return the property value.
   */
  public operator fun getValue(thisRef: R, property: KProperty<*>): T

  /**
   * Sets the value of the property for the given object.
   * @param thisRef the object for which the value is requested.
   * @param property the metadata for the property.
   * @param value the value to set.
   */
  public operator fun setValue(thisRef: R, property: KProperty<*>, value: T)
}