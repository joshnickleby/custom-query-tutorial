package com.astontech.dataaccess.tutorial.services.gameCharacters;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface GameCharacterRepository extends CrudRepository<GameCharacter, Integer> {

  @Query("select character from GameCharacter character where character.name = :name")
  GameCharacter getCharacterByName(String name);

  @Transactional
  @Modifying
  @Query("update GameCharacter character set character.videoGameId = :videoGameId where character.id = :characterId")
  Integer updateVideoGameId(Integer characterId, Integer videoGameId);

  @Query(value = "select gc.id, gc.name from game_character gc where gc.video_game_id = ?1", nativeQuery = true)
  List<GameCharacterQuery> getCharacterProjectionByVideoGameId(Integer videoGameId);
}
