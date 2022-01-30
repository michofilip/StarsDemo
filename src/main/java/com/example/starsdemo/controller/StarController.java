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

    @GetMapping("/{id}")
    public Star findById(@PathVariable int id) {
        return starService.findById(id);
    }

    @GetMapping("/{id}/str")
    public ResponseEntity<Either<String, Star>> findByIdOrString(@PathVariable int id) {
        return switch (starService.findByIdOrString(id)) {
            case Right<String, Star> right -> new ResponseEntity<>(right, HttpStatus.OK);
            case Left<String, Star> left -> new ResponseEntity<>(left, HttpStatus.NOT_FOUND);
        };
    }

    @GetMapping("/{id}/exc")
    public ResponseEntity<Either<String, Star>> findByIdOrException(@PathVariable int id) {
        return switch (starService.findByIdOrException(id)) {
            case Right<RuntimeException, Star> right -> new ResponseEntity<>(Either.right(right.value()), HttpStatus.OK);

            case Left<RuntimeException, Star> left -> switch (left.value()) {
                case ElementNotFountException ex -> new ResponseEntity<>(Either.left(ex.getMessage()), HttpStatus.NOT_FOUND);

                case ForbiddenPlanetException ex -> new ResponseEntity<>(Either.left(ex.getMessage()), HttpStatus.FORBIDDEN);

                case default -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            };
        };
    }
}
