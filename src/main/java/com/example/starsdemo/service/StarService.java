package com.example.starsdemo.service;

import com.example.starsdemo.db.model.StarEntity;
import com.example.starsdemo.db.repository.StarRepository;
import com.example.starsdemo.exception.ElementNotFountException;
import com.example.starsdemo.utils.Either;
import com.example.starsdemo.view.Star;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Either<RuntimeException, Star> findById1(int id) {
        return starRepository.findById(id)
            .map(Either::<RuntimeException, StarEntity>right)
            .orElseGet(() -> Either.left(new ElementNotFountException(String.format("Star id: %s not found!", id))))
            .map(Star::from);
    }
}
