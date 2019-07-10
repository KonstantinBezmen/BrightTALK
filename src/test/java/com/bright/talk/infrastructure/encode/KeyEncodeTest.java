package com.bright.talk.infrastructure.encode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class KeyEncodeTest {

    @Test
    public void generateKey_ValidKey() {
        String key = KeyEncode.generateKey();
        assertNotNull(key);
        assertEquals(32, key.length());
    }
}
