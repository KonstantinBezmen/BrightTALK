package com.bright.talk.infrastructure.encode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class KeyEncodeTest {

    @Test
    public void generateKey_ValidKey() {
        String key = KeyEncode.generateKey();
        assertNotNull(key);
        assertTrue(key.length() < 33);
    }
}
