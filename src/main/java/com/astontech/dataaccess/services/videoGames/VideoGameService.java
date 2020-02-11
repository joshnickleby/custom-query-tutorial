package com.astontech.dataaccess.services.videoGames;

import com.astontech.dataaccess.domain.VideoGame;

public interface VideoGameService {

  VideoGame save(VideoGame game);

  default VideoGame save(VideoGameRepository repo, VideoGame game) {
    return repo.save(game);
  }
}
