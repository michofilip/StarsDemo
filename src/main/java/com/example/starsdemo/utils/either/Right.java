package com.example.starsdemo.utils.either;

import java.util.function.Function;

public record Right<L, R>(R value) implements Either<L, R> {
    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public <R1> Either<L, R1> flatMap(Function<R, Either<L, R1>> function) {
        return function.apply(value);
    }
}
