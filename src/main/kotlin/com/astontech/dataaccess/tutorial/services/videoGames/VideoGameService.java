package com.astontech.dataaccess.tutorial.services.videoGames;

import java.util.List;

public interface VideoGameService {

  VideoGame save(VideoGame game);

  default VideoGame save(VideoGameRepository repo, VideoGame game) {
    return repo.save(game);
  }

  VideoGame getGameByName(String name);

  List<VideoGame> getGameWithNameLike(String name);

  VideoGameQuery getGameProjectedByName(String name);
}
