package com.coviam.util;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class UUIDUtil {

    public String getUniqueId() {
        return UUID.randomUUID().toString();
    }
}
