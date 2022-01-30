package com.example.starsdemo.controller;

import com.example.starsdemo.service.PlanetService;
import com.example.starsdemo.view.Planet;
import com.example.starsdemo.view.PlanetCount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    public List<Planet> findAll() {
        return planetService.findAll();
    }

    @GetMapping("/random")
    public List<Planet> findAllRandom() {
        return planetService.findAllRandom();
    }

    @GetMapping("/count")
    public List<PlanetCount> getPlanetCount() {
        return planetService.getPlanetCount();
    }
}
