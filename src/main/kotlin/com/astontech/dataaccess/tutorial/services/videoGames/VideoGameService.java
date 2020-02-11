package com.astontech.dataaccess.tutorial.services.videoGames;

public interface VideoGameService {

  VideoGame save(VideoGame game);

  default VideoGame save(VideoGameRepository repo, VideoGame game) {
    return repo.save(game);
  }
}
