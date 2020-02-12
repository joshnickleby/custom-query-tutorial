package com.astontech.dataaccess.tutorial.services.videoGames;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoGameServiceImpl implements VideoGameService {
  private VideoGameRepository repo;

  @Autowired
  public VideoGameServiceImpl(VideoGameRepository repo) {
    this.repo = repo;
  }

  @Override
  public VideoGame save(VideoGame game) {
    return this.save(repo, game);
  }

  @Override
  public VideoGame getGameByName(String name) {
    return repo.getGameByName(name);
  }

  @Override
  public List<VideoGame> getGameWithNameLike(String name) {
    return repo.getGameWithNameLike(name);
  }

  @Override
  public VideoGameQuery getGameProjectedByName(String name) {
    return repo.getGameProjectedByName(name);
  }
}
