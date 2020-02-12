package com.astontech.dataaccess.tutorial.services.videoGames;

import com.astontech.dataaccess.tutorial.services.gameCharacters.GameCharacter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class VideoGame {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  public String name;
  public Integer year;
  public LocalDate releaseDate;
  public BigDecimal averageRating = BigDecimal.ZERO;
  public ZonedDateTime createdOn;
  public Boolean emulated = false;
  public Long unitsSold;

  public VideoGame() {}

  public VideoGame(String name) {
    this.name = name;
    this.createdOn = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Chicago"));
  }

  public VideoGame(String name,
                   Integer year,
                   LocalDate releaseDate,
                   BigDecimal averageRating,
                   Long unitsSold,
                   Boolean emulated) {
    this(name);

    this.year = year;
    this.unitsSold = unitsSold;
    this.averageRating = averageRating;
    this.emulated = emulated;
    this.releaseDate = releaseDate;
  }

  @Override
  public String toString() {
    return String.join(", ",
        propToString("id", id),
        propToString("name", name),
        propToString("year", year),
        propToString("releaseDate", releaseDate),
        propToString("unitsSold", unitsSold),
        propToString("averageRating", averageRating),
        propToString("emulated", emulated ? "YES" : "NO"),
        propToString("createdOn", createdOn)
    );
  }

  private <T> String propToString(String name, T value) {

    return name + ": " + (Objects.isNull(value) ? "NA" : value.toString());
  }
}
