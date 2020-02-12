package com.astontech.dataaccess.tutorial.services.gameCharacters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  @Override
  public List<GameCharacterQuery> getCharacterProjectionByVideoGameId(Integer videoGameId) {
    return gameCharacterRepository.getCharacterProjectionByVideoGameId(videoGameId);
  }

  @Override
  public List<GameCharacter> getCharactersWithGameInfo() {
    return gameCharacterRepository.getCharactersWithGameInfo()
        .stream()
        .map(GameCharacter::new)
        .collect(Collectors.toList());
  }

  @Override
  public Stream<GameCharacter> getCharactersWithGameInfoStream() {
    return gameCharacterRepository.getCharactersWithGameInfo().stream().map(GameCharacter::new);
  }
}
