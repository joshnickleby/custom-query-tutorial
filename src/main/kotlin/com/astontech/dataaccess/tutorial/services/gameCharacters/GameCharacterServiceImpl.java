package com.astontech.dataaccess.tutorial.services.gameCharacters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCharacterServiceImpl implements GameCharacterService {

  @Autowired
  private GameCharacterRepository gameCharacterRepository;

  @Override
  public GameCharacter save(GameCharacter character) {
    return this.save(gameCharacterRepository, character);
  }

  @Override
  public GameCharacter getCharacterByName(String name) {
    return gameCharacterRepository.getCharacterByName(name);
  }

  @Override
  public Integer updateVideoGameId(Integer characterId, Integer videoGameId) {
    return gameCharacterRepository.updateVideoGameId(characterId, videoGameId);
  }
}
