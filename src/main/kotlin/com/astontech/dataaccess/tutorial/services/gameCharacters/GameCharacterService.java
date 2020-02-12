package com.astontech.dataaccess.tutorial.services.gameCharacters;

public interface GameCharacterService {

  GameCharacter save(GameCharacter character);

  default GameCharacter save(GameCharacterRepository repo, GameCharacter character) {
    return repo.save(character);
  }

  GameCharacter getCharacterByName(String name);

  Integer updateVideoGameId(Integer characterId, Integer videoGameId);
}
