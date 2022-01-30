package com.example.starsdemo.controller;

import com.example.starsdemo.exception.ElementNotFountException;
import com.example.starsdemo.exception.ForbiddenPlanetException;
import com.example.starsdemo.service.StarService;
import com.example.starsdemo.utils.either.Either;
import com.example.starsdemo.utils.either.Left;
import com.example.starsdemo.utils.either.Right;
import com.example.starsdemo.view.Star;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stars")
@RequiredArgsConstructor
public class StarController {

    private final StarService starService;

    @GetMapping
    public List<Star> findAll() {
        return starService.findAll();
    }

    @GetMapping("/v1/{id}")
    public Star findById(@PathVariable int id) {
        return starService.findById(id);
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Either<String, Star>> findByIdV2(@PathVariable int id) {
        return switch (starService.findByIdOrString(id)) {
            case Right<String, Star> right -> new ResponseEntity<>(right, HttpStatus.OK);
            case Left<String, Star> left -> new ResponseEntity<>(left, HttpStatus.NOT_FOUND);
        };
    }

    @GetMapping("/v3/{id}")
    public Star findByIdV3(@PathVariable int id) {
        return switch (starService.findByIdOrString(id)) {
            case Right<String, Star> right -> right.value();
            case Left<String, Star> left -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, left.value());
        };
    }

    @GetMapping("/v4/{id}")
    public ResponseEntity<Either<String, Star>> findByIdV4(@PathVariable int id) {
        return switch (starService.findByIdOrException(id)) {
            case Right<RuntimeException, Star> right -> new ResponseEntity<>(Either.right(right.value()), HttpStatus.OK);

            case Left<RuntimeException, Star> left -> switch (left.value()) {
                case ElementNotFountException ex -> new ResponseEntity<>(Either.left(ex.getMessage()), HttpStatus.NOT_FOUND);
                case ForbiddenPlanetException ex -> new ResponseEntity<>(Either.left(ex.getMessage()), HttpStatus.FORBIDDEN);
                case default -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            };
        };
    }

    @GetMapping("/v5/{id}")
    public Star findByIdV5(@PathVariable int id) {
        return switch (starService.findByIdOrException(id)) {
            case Right<RuntimeException, Star> right -> right.value();

            case Left<RuntimeException, Star> left -> {
                switch (left.value()) {
                    case ElementNotFountException ex -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
                    case ForbiddenPlanetException ex -> throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
                    case default -> throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        };
    }
}
