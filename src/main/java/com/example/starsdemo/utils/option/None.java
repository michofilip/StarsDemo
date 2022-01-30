package com.example.starsdemo.utils.option;

import java.util.function.Function;

public record None<T>() implements Option<T> {
    @Override
    public <U> Option<U> flatmap(Function<T, Option<U>> function) {
        return new None<>();
    }
}
