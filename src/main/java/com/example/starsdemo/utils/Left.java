package com.example.starsdemo.utils;

import java.util.function.Function;

public record Left<L, R>(L value) implements Either<L, R> {
    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public <R1> Either<L, R1> flatMap(Function<R, Either<L, R1>> function) {
        return Either.left(value);
    }
}
