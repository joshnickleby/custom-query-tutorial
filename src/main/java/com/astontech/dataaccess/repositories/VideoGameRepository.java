package com.astontech.dataaccess.repositories;

import com.astontech.dataaccess.domain.VideoGame;
import org.springframework.data.repository.CrudRepository;

public interface VideoGameRepository extends CrudRepository<VideoGame, Integer> {
}
