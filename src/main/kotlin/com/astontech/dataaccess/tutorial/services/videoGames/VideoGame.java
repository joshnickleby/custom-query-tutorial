package com.astontech.dataaccess.tutorial.services.videoGames;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class VideoGame {
  public BigDecimal averageRating = BigDecimal.ZERO;
  public ZonedDateTime createdOn;
  public Boolean emulated = false;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  public String name;
  public LocalDate releaseDate;
  public Long unitsSold;
  public Integer year;


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

  public VideoGame(String name) {
    this.name = name;
    this.createdOn = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/Chicago"));
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
