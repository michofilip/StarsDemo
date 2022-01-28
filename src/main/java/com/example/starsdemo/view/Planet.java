package com.example.starsdemo.view;

import com.example.starsdemo.db.model.PlanetEntity;

public record Planet(int id, String name) {
    public static Planet from(PlanetEntity planetEntity) {
        return new Planet(
            planetEntity.getId(),
            planetEntity.getName()
        );
    }
}
