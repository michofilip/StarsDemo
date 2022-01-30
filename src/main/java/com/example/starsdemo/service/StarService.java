package com.example.starsdemo.service;

import com.example.starsdemo.db.model.StarEntity;
import com.example.starsdemo.db.repository.StarRepository;
import com.example.starsdemo.exception.ElementNotFountException;
import com.example.starsdemo.exception.ForbiddenPlanetException;
import com.example.starsdemo.utils.either.Either;
import com.example.starsdemo.view.Planet;
import com.example.starsdemo.view.Star;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarService {

    private final StarRepository starRepository;

    public List<Star> findAll() {
        return starRepository.findAll().stream()
            .map(Star::from)
            .toList();
    }

    public Star findById(int id) {
        return starRepository.findById(id)
            .map(Star::from)
            .orElseThrow(() -> new ElementNotFountException(String.format("Star id: %s not found.", id)));
    }

    public Either<String, Star> findByIdOrString(int id) {
        return starRepository.findById(id)
            .map(Either::<String, StarEntity>right)
            .orElseGet(() -> Either.left(String.format("Star id: %s not found!", id)))
            .map(Star::from);
    }

    public Either<RuntimeException, Star> findByIdOrException(int id) {
        return starRepository.findById(id)
            .map(Either::<RuntimeException, StarEntity>right)
            .orElseGet(() -> Either.left(new ElementNotFountException(String.format("Star id: %s not found!", id))))
            .map(Star::from)
            .filterOrElse(this::hasNoForbiddenPlanet, () -> new ForbiddenPlanetException("Forbidden planet detected"));
    }

    private boolean hasNoForbiddenPlanet(Star star) {
        return star.planets().stream().map(Planet::name).noneMatch(name -> name.equals("Kepler-90h"));
    }
}
