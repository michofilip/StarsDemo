package com.example.starsdemo.view;

import com.example.starsdemo.db.model.StarEntity;

import java.util.List;

public record Star(int id, String name, List<Planet> planets) {
    public static Star from(StarEntity starEntity) {
        return new Star(
            starEntity.getId(),
            starEntity.getName(),
            starEntity.getPlanets().stream().map(Planet::from).toList()
        );
    }
}
