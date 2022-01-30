package com.example.starsdemo.utils.option;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface Option<T> permits Some, None {

    <U> Option<U> flatmap(Function<T, Option<U>> function);

    default <U> Option<U> map(Function<T, U> function) {
        return flatmap((T t) -> new Some<>(function.apply(t)));
    }

    default Option<T> filter(Predicate<T> predicate) {
        return flatmap((T t) -> predicate.test(t) ? this : new None<>());
    }
}
