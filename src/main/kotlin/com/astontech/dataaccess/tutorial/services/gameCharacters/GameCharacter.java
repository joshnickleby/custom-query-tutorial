package com.astontech.dataaccess.tutorial.services.gameCharacters;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GameCharacter {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;

  public String name;

  public GameCharacter(String name) {
    this.name = name;
  }
}
