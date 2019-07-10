package com.bright.talk.infrastructure.encode;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public final class KeyEncode {
    public static String generateKey() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
