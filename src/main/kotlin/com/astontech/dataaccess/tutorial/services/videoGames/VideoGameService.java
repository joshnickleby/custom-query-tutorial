package com.astontech.dataaccess.tutorial.services.videoGames;

import java.util.List;

public interface VideoGameService {

  VideoGame save(VideoGame game);

  default VideoGame save(VideoGameRepository repo, VideoGame game) {
    return repo.save(game);
  }

  VideoGame getVideoGameByName(String name);

  List<VideoGame> getVideoGameWithNameLike(String name);
}
