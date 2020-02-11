package com.astontech.dataaccess.services.videoGames;

import com.astontech.dataaccess.domain.VideoGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoGameServiceImpl implements VideoGameService {

  @Autowired
  private VideoGameRepository videoGameRepository;

  @Override
  public VideoGame save(VideoGame game) {
    return this.save(videoGameRepository, game);
  }
}
