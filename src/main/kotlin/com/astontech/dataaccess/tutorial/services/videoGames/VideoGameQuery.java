package com.astontech.dataaccess.tutorial.services.videoGames;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface VideoGameQuery {
  Integer getId();

  String getName();

  BigDecimal getAverage_rating();

  String getCreated_on();

  Boolean getEmulated();

  LocalDate getRelease_date();

  Long getUnits_sold();

  Integer getYear();
}
