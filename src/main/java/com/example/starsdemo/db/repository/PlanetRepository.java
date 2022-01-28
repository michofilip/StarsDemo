package com.example.starsdemo.db.repository;

import com.example.starsdemo.db.model.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Integer> {
}
