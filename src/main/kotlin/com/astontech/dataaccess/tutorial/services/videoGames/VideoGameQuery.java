package com.astontech.dataaccess.tutorial.services.videoGames;

import com.astontech.dataaccess.tutorial.services.gameCharacters.GameCharacterQuery;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface VideoGameQuery {
  Integer getId();
  String getName();
  Integer getYear();
  LocalDate getRelease_date();
  BigDecimal getAverage_rating();
  String getCreated_on();
  Boolean getEmulated();
  Long getUnits_sold();

  GameCharacterQuery getCharacter();
}
