package com.astontech.dataaccess.tutorial.services.gameCharacters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameCharacterServiceImpl implements GameCharacterService {

  private GameCharacterRepository gameCharacterRepository;

  @Autowired
  public GameCharacterServiceImpl(GameCharacterRepository gameCharacterRepository) {
    this.gameCharacterRepository = gameCharacterRepository;
  }

  @Override
  public GameCharacter save(GameCharacter character) {
    return gameCharacterRepository.save(character);
  }

  @Override
  public GameCharacter getCharacterByName(String name) {
    return gameCharacterRepository.getCharacterByName(name);
  }

  @Override
  public Integer updateVideoGameId(Integer videoGameId, Integer characterId) {
    return gameCharacterRepository.updateVideoGameId(videoGameId, characterId);
  }
}
