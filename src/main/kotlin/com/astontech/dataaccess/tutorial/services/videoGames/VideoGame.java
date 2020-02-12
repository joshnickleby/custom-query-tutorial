package com.astontech.dataaccess.tutorial.services.videoGames;

import com.astontech.dataaccess.tutorial.services.gameCharacters.GameCharacter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.Objects;

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

  public VideoGame(VideoGameQuery query) {
    this.setItems(query);
  }

  private void setItems(VideoGameQuery query) {
    this.id = query.getId();
    this.name = query.getName();
    this.year = query.getYear();
    this.releaseDate = query.getRelease_date();
    this.averageRating = query.getAverage_rating();

    String dateString = query.getCreated_on();

    String[] dateTimeParts = dateString.split(" ");

    String[] dateParts = dateTimeParts[0].split("-");

    String[] timeParts = dateTimeParts[1].split("\\.")[0].split(":");

    LocalDate date = LocalDate.of(
        Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));

    LocalTime time = LocalTime.of(Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]), Integer.parseInt(timeParts[2]));

    LocalDateTime dateTime = LocalDateTime.of(date, time);

    this.createdOn = ZonedDateTime.of(dateTime, ZoneId.of("America/Chicago"));

    this.emulated = query.getEmulated();

    this.unitsSold = query.getUnits_sold();
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
        propToString("emulated", !Objects.isNull(emulated) && emulated ? "YES" : "NO"),
        propToString("createdOn", createdOn)
    );
  }

  private <T> String propToString(String name, T value) {

    return name + ": " + (Objects.isNull(value) ? "NA" : value.toString());
  }
}
