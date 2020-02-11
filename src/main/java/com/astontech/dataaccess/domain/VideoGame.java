package com.astontech.dataaccess.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class VideoGame {

  public String name;
  // Int
  // Long
  // BigDecimal
  // Boolean
  // LocalDate
  // LocalTime
  // ZonedDateTime
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public Integer id;
}
