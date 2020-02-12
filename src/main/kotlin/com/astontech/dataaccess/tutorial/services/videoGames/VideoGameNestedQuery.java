package com.astontech.dataaccess.tutorial.services.videoGames;

public interface VideoGameNestedQuery extends VideoGameQuery {
  Integer getCharacterId();

  String getCharacterName();
}