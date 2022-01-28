package com.example.starsdemo.db.repository;

import com.example.starsdemo.db.model.StarEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface StarRepository extends JpaRepository<StarEntity, Integer> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = "planets")
    List<StarEntity> findAll();

    @Override
    @NonNull
    @EntityGraph(attributePaths = "planets")
    Optional<StarEntity> findById(@NonNull Integer integer);
}
