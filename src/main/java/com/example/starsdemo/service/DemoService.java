package com.example.starsdemo.service;

import com.example.starsdemo.utils.option.None;
import com.example.starsdemo.utils.option.Option;
import com.example.starsdemo.utils.option.Some;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DemoService {

    record User(String name, int age, boolean active) {
    }

    //    @PostConstruct
    public void demo() {

        User ala = new User("Ala", 20, true);
        User ela = new User("Ela", 30, false);
        User ola = new User("Ola", 40, true);
        User ula = new User("Ula", 50, false);

        Option<User> maybeUser1 = new Some<>(ala);
        Option<User> maybeUser2 = new None<>();

        patternMatchUser(maybeUser1);
        patternMatchUser(maybeUser2);

        Option<User> userOption = maybeUser1.filter(User::active);
        Option<User> userOption1 = maybeUser1.map(user -> new User(user.name().toUpperCase(Locale.ROOT), user.age() + 1, false));
        Option<String> stringOption = maybeUser1.flatmap(user -> user.active() ? new Some<>(user.name()) : new None<>());
        patternMatchUser(userOption1);

        patternMatchString(stringOption);
    }

    private void patternMatchUser(Option<User> maybeUser) {
        String result = switch (maybeUser) {
            case Some<User> some -> some.value().toString();
            case None<User> ignored -> "No user";
        };

        System.out.println(result);
    }

    private void patternMatchString(Option<String> maybeString) {
        String result = switch (maybeString) {
            case Some<String> some -> some.value();
            case None<String> ignored -> "No string";
        };

        System.out.println(result);
    }
}
