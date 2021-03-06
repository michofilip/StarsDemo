package com.example.starsdemo.utils.either;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public sealed interface Either<L, R> permits Left, Right {
    static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    boolean isLeft();

    boolean isRight();

    <R1> Either<L, R1> flatMap(Function<R, Either<L, R1>> function);

    default <R1> Either<L, R1> map(Function<R, R1> function) {
        return flatMap((R r) -> Either.right(function.apply(r)));
    }

    default Either<R, L> swap() {
        return switch (this) {
            case Right<L, R> right -> Either.left(right.value());
            case Left<L, R> left -> Either.right(left.value());
        };
    }

    default Either<L, R> filterOrElse(Predicate<R> predicate, Supplier<L> orElse) {
        return switch (this) {
            case Right<L, R> right -> predicate.test(right.value()) ? this : Either.left(orElse.get());
            case Left<L, R> left -> this;
        };
    }
}
