package com.astontech.dataaccess.tutorial.services.gameCharacters;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface GameCharacterRepository extends CrudRepository<GameCharacter, Integer> {

  @Query("select character from GameCharacter character where character.name = :name")
  GameCharacter getCharacterByName(String name);

  @Transactional
  @Modifying
  @Query("update GameCharacter character set character.videoGameId = :videoGameId where character.id = :characterId")
  Integer updateVideoGameId(Integer characterId, Integer videoGameId);
}
