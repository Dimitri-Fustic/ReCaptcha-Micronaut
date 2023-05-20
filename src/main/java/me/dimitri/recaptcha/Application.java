package me.dimitri.recaptcha;

import io.micronaut.http.annotation.Controller;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}