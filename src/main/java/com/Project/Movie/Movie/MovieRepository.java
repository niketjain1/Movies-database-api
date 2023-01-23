package com.Project.Movie.Movie;

import com.Project.Movie.users.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {
    public List<MovieEntity> findByUserId(int user_id);
}
