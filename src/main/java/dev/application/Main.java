package dev.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static Initial initial;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        initial.initial();
    }

    @Autowired
    public void setInit(Initial init) {
        Main.initial = init;
    }
}