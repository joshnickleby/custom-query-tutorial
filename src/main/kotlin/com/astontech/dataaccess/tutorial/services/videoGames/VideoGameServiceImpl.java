package com.astontech.dataaccess.tutorial.services.videoGames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameServiceImpl implements VideoGameService {

  private VideoGameRepository videoGameRepository;

  @Autowired
  public VideoGameServiceImpl(VideoGameRepository videoGameRepository) {
    this.videoGameRepository = videoGameRepository;
  }

  @Override
  public VideoGame save(VideoGame game) {
    return this.save(videoGameRepository, game);
  }

  @Override
  public VideoGame getVideoGameByName(String name) {
    return videoGameRepository.getGameByName(name);
  }

  @Override
  public List<VideoGame> getVideoGameWithNameLike(String name) {
    return videoGameRepository.getGameWithNameLike(name);
  }

  @Override
  public VideoGameQuery getGameProjectedByName(String name) {
    return videoGameRepository.getGameProjectedByName(name);
  }

  @Override
  public List<VideoGameNestedQuery> getVideoGameAndNestedByName(String name) {
    return videoGameRepository.getGameProjectedNestedByName(name);
  }
}
