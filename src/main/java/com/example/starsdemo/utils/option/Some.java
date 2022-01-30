package com.example.starsdemo.utils.option;

import java.util.function.Function;

public record Some<T>(T value) implements Option<T> {
    @Override
    public <U> Option<U> flatmap(Function<T, Option<U>> function) {
        return function.apply(value());
    }
}
