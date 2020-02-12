package com.astontech.dataaccess.tutorial.services.gameCharacters;

public interface GameCharacterService {

  GameCharacter save(GameCharacter character);

  GameCharacter getCharacterByName(String name);

  Integer updateVideoGameId(Integer videoGameId, Integer characterId);
}
