package com.coviam.util;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class RandomGenerator {

    public String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}
