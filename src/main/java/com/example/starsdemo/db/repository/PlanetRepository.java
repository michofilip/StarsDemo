package com.example.starsdemo.db.repository;

import com.example.starsdemo.db.model.PlanetEntity;
import com.example.starsdemo.view.PlanetCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Integer> {

    @Query("""
        select new com.example.starsdemo.view.PlanetCount(s.name, count(p))
        from StarEntity s
        join s.planets p
        group by s.name
        """)
    List<PlanetCount> getPlanetCount();

}
