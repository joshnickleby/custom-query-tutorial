package com.astontech.dataaccess.tutorial.services.videoGames;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoGameRepository extends CrudRepository<VideoGame, Integer> {

  @Query("select game from VideoGame game where game.name = :name")
  VideoGame getGameByName(String name);

  @Query("select game from VideoGame game where game.name like concat('%', ?1, '%')")
  List<VideoGame> getGameWithNameLike(String name);

  @Query(value =
      "select " +
          "game.id, " +
          "game.name, " +
          "game.year, " +
          "game.release_date, " +
          "game.average_rating, " +
          "game.created_on, " +
          "game.emulated, " +
          "game.units_sold " +
      "from video_game game where game.name = ?1", nativeQuery = true)
  VideoGameQuery getGameProjectedByName(String name);

  @Query(value =
      "select " +
          "game.id, " +
          "game.name, " +
          "game.year, " +
          "game.release_date, " +
          "game.average_rating, " +
          "game.created_on, " +
          "game.emulated, " +
          "game.units_sold, " +
          "gc.id as characterId, " +
          "gc.name as characterName " +
      "from video_game game " +
      "inner join game_character gc on gc.video_game_id = game.id " +
      "where game.name = ?1", nativeQuery = true)
  List<VideoGameNestedQuery> getGameProjectedNestedByName(String name);
}
