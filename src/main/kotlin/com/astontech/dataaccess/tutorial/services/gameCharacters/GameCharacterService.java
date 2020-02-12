package com.astontech.dataaccess.tutorial.services.gameCharacters;

import java.util.List;
import java.util.stream.Stream;

public interface GameCharacterService {

  GameCharacter save(GameCharacter character);

  default GameCharacter save(GameCharacterRepository repo, GameCharacter character) {
    return repo.save(character);
  }

  GameCharacter getCharacterByName(String name);

  Integer updateVideoGameId(Integer characterId, Integer videoGameId);

  List<GameCharacterQuery> getCharacterProjectionByVideoGameId(Integer videoGameId);

  List<GameCharacter> getCharactersWithGameInfo();

  Stream<GameCharacter> getCharactersWithGameInfoStream();
}
