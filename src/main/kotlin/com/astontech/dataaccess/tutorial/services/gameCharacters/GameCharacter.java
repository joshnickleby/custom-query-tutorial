package com.astontech.dataaccess.tutorial.services.gameCharacters;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameCharacter {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;

  public Integer videoGameId;

  public GameCharacter(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.join(", ", "id: " + id, "name: " + name);
  }
}
