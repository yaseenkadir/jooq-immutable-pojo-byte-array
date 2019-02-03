package com.github.yaseenkadir.jipai.tables.pojos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoobarTest {

    @Test
    public void testImmutableByteArray() {
        byte[] bytes = {1, 2, 3, 4};
        Foobar f = new Foobar(1L, bytes);
        Foobar copy = new Foobar(f);
        copy.getData()[0] = 10;
        assertEquals(10, f.getData()[0]);
        assertEquals(10, copy.getData()[0]);
    }
}
