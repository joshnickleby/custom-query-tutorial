package com.astontech.dataaccess.tutorial.services.gameCharacters;

import com.astontech.dataaccess.tutorial.services.videoGames.VideoGame;
import com.astontech.dataaccess.tutorial.services.videoGames.VideoGameNestedQuery;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class GameCharacter {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;

  public String name;

  public Integer videoGameId;

  @Transient
  public VideoGame game;

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

  public GameCharacter(GameCharacterGameInfoQuery query) {
    this((GameCharacterQuery) query);

    this.game = new VideoGame(query.getGameName());
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
