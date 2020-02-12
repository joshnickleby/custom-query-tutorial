package com.astontech.dataaccess.tutorial.config;

import com.astontech.dataaccess.tutorial.services.gameCharacters.GameCharacter;
import com.astontech.dataaccess.tutorial.services.gameCharacters.GameCharacterService;
import com.astontech.dataaccess.tutorial.services.videoGames.VideoGame;
import com.astontech.dataaccess.tutorial.services.videoGames.VideoGameQuery;
import com.astontech.dataaccess.tutorial.services.videoGames.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TutorialDbInitializer implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private VideoGameService videoGameService;

  @Autowired
  private GameCharacterService gameCharacterService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    createGames();
    printBreak();

    createCharacters();
    printBreak();

    checkGetGameWithName();
    printBreak();

    checkGetWithNameCharacter();
    printBreak();

    addVideoGameTest();
    printBreak();

    addCharactersToGame("Super Mario 64", "Mario", "Bowser");
    printBreak();

    addCharactersToGame("Legend of Zelda: Majora's Mask", "Link", "Skull Kid", "Navi");
    printBreak();

    addCharactersToGame("Halo: Combat Evolved", "Master Chief", "Cortana");
    printBreak();

    getVideoGameProjected("Super Mario 64");
    printBreak();

    getNestedVideoGameProjected("Legend of Zelda: Majora's Mask");
    printBreak();

    getBrokenApart("Halo: Combat Evolved");
    printBreak();

    getCharactersWithVideoGameInfo();
  }

  public void printBreak() {
    System.out.println("=========================================\n");
  }

  private void createGames() {
    List<VideoGame> games = Arrays.asList(
        new VideoGame("Super Mario 64", 1996, LocalDate.of(1996, 6, 23), new BigDecimal("96.41"), 12000000L, true),
        new VideoGame("Legend of Zelda: Majora's Mask", 2000, LocalDate.of(2000, 4, 27), new BigDecimal("94.87"), 3360000L, true),
        new VideoGame("Cyberpunk 2077"),
        new VideoGame("XCOM: Enemy Unknown", 2012, LocalDate.of(2012, 10, 9), new BigDecimal("89.34"), null, false),
        new VideoGame("Halo: Combat Evolved", 2001, LocalDate.of(2001, 11, 15), new BigDecimal(97), 65000000L, false),
        new VideoGame("Disco Elysium", 2019, LocalDate.of(2019, 10, 15), new BigDecimal(91), 100000L, false),
        new VideoGame("The Outer Worlds", 2019, LocalDate.of(2019, 10, 25), new BigDecimal("84.56"), 2000000L, false),
        new VideoGame("Nancy Drew: The Deadly Device", 2012, LocalDate.of(2012, 10, 23), BigDecimal.ZERO, null, false),
        new VideoGame("Legend of Dragoon", 1999, LocalDate.of(1999, 12, 2), new BigDecimal("74.12"), 1200345L, true),
        new VideoGame("Luigi's Mansion 3", 2019, LocalDate.of(2019, 10, 31), new BigDecimal("86.68"), 5370000L, false),
        new VideoGame("Divinity: Original Sin 2", 2017, LocalDate.of(2017, 9, 14), new BigDecimal("94.04"), 1034987L, false),
        new VideoGame("Far Cry 4", 2017, LocalDate.of(2017, 11, 18), new BigDecimal(82), 7000000L, false)
    );

    List<VideoGame> loopSaved = games.stream()
        .map(videoGameService::save)
        .collect(Collectors.toList());

    Stream<VideoGame> gameStream = new ArrayList<>(loopSaved).stream();

    Stream<VideoGame> parallelStream = new ArrayList<>(loopSaved).parallelStream();

    Consumer<VideoGame> print = System.out::println;

    System.out.println("--------------------- STREAM");

    gameStream.forEach(print);

    System.out.println("--------------------- PARALLEL STREAM");

    parallelStream.forEach(print);
  }

  private void createCharacters() {
    List<GameCharacter> mario = Arrays.asList(new GameCharacter("Mario"), new GameCharacter("Bowser"));
    List<GameCharacter> zelda = Arrays.asList(new GameCharacter("Link"), new GameCharacter("Skull Kid"), new GameCharacter("Navi"));
    List<GameCharacter> halo = Arrays.asList(new GameCharacter("Master Chief"), new GameCharacter("Cortana"));
    List<GameCharacter> luigi = Arrays.asList(new GameCharacter("Luigi"));

    Stream.of(mario, zelda, halo, luigi).forEach(gameCharacterList -> {
      gameCharacterList.stream()
          .map(gameCharacterService::save)
          .forEach(System.out::println);
    });
  }

  private void checkGetGameWithName() {
    VideoGame mario = videoGameService.getGameByName("Super Mario 64");

    System.out.println("Game by Name");
    System.out.println(mario);

    System.out.println("Game with Name like Single");
    videoGameService.getGameWithNameLike("zelda").forEach(System.out::println);

    System.out.println("Game with Name like Multiple");
    videoGameService.getGameWithNameLike("of").forEach(System.out::println);
  }

  private void checkGetWithNameCharacter() {

  }

  private void addVideoGameTest() {
    VideoGame game = videoGameService.getGameByName("Luigi's Mansion 3");

    GameCharacter character = gameCharacterService.getCharacterByName("Luigi");

    System.out.println("Add Character to Game Test");

    System.out.println("Game: " + game.toString());

    System.out.println("Character: " + character.toString());

    Integer saved = gameCharacterService.updateVideoGameId(game.id, character.id);

    System.out.println("Save character " + saved);

    System.out.println(gameCharacterService.getCharacterByName("Luigi"));
  }

  private void addCharactersToGame(String gameName, String ... characterNames) {
    VideoGame game = videoGameService.getGameByName(gameName);

    System.out.println("Add character to game: " + gameName);

    Stream.of(characterNames)
        .map(gameCharacterService::getCharacterByName)
        .forEach(character -> {
          gameCharacterService.updateVideoGameId(game.id, character.id);
          System.out.println(gameCharacterService.getCharacterByName(character.name));
        });
  }

  private void getVideoGameProjected(String name) {
    System.out.println("Get Video Game");

    VideoGameQuery query = videoGameService.getGameProjectedByName(name);

    System.out.println("id: " + query.getId() + " date: " + query.getRelease_date());

    VideoGame game = new VideoGame(query);

    System.out.println(game);
  }

  private void getNestedVideoGameProjected(String name) {

  }

  private void getBrokenApart(String name) {

  }

  private void getCharactersWithVideoGameInfo() {

  }
}
