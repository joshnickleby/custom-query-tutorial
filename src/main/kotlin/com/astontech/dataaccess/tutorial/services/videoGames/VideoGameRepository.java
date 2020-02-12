package com.astontech.dataaccess.tutorial.services.videoGames;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoGameRepository extends CrudRepository<VideoGame, Integer> {

  @Query("select game from VideoGame game where game.name = :name")
  VideoGame getGameByName(String name);

  @Query("select game from VideoGame game where game.name like concat('%', ?1, '%')")
  List<VideoGame> getGameWithNameLike(String name);
}
