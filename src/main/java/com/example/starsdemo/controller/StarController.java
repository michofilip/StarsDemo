package com.example.starsdemo.controller;

import com.example.starsdemo.service.StarService;
import com.example.starsdemo.utils.Either;
import com.example.starsdemo.utils.Left;
import com.example.starsdemo.utils.Right;
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

    @GetMapping("v1/{id}")
    public Star findById1(@PathVariable int id) {
        return starService.findById(id);
    }

    @GetMapping("v2/{id}")
    public ResponseEntity<Either<String, Star>> findById2(@PathVariable int id) {
        return switch (starService.findById1(id)) {
            case Right<RuntimeException, Star> right -> new ResponseEntity<>(Either.right(right.value()), HttpStatus.OK);
            case Left<RuntimeException, Star> left -> new ResponseEntity<>(Either.left(left.value().getMessage()), HttpStatus.NOT_FOUND);
        };
    }

    @GetMapping("v3/{id}")
    public ResponseEntity<Either<String, Star>> findById3(@PathVariable int id) {
        Either<String, Star> result = starService.findById1(id).swap().map(Throwable::getMessage).swap();

        return switch (result) {
            case Right<String, Star> right -> new ResponseEntity<>(right, HttpStatus.OK);
            case Left<String, Star> left -> new ResponseEntity<>(left, HttpStatus.NOT_FOUND);
        };
    }
}
