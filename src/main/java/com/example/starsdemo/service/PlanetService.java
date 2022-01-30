package com.example.starsdemo.service;

import com.example.starsdemo.db.repository.PlanetRepository;
import com.example.starsdemo.utils.CollectionUtils;
import com.example.starsdemo.view.Planet;
import com.example.starsdemo.view.PlanetCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;

    public List<Planet> findAll() {
        return planetRepository.findAll().stream()
            .map(Planet::from)
            .toList();
    }

    public List<Planet> findAllRandom() {
        return CollectionUtils.shuffle(findAll());
    }

    public List<PlanetCount> getPlanetCount() {
        return planetRepository.getPlanetCount();
    }
}
