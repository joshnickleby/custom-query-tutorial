package com.astontech.dataaccess.tutorial.services.gameCharacters;

import com.astontech.dataaccess.tutorial.services.videoGames.VideoGameNestedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class GameCharacter {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;

  public Integer videoGameId;

  public GameCharacter() {}

  public GameCharacter(String name) {
    this.name = name;
  }

  public GameCharacter(GameCharacterQuery query) {
    this.id = query.getId();
    this.name = query.getName();
  }

  public GameCharacter(VideoGameNestedQuery query) {
    this.id = query.getCharacterId();
    this.name = query.getCharacterName();
  }

  @Override
  public String toString() {
    return String.join(", ",
        "id: " + id,
        "name: " + name,
        "videoGameId: " + (Objects.isNull(videoGameId) ? "null" : videoGameId)
    );
  }
}
