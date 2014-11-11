package com.jasonnerothin.testing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:20 AM
 */
public class CharactersTest {

    private static final int NUM_TESTS = 16;

    private Characters testInstance;

    @Before
    public void setup(){
        testInstance = new Characters();
    }

    @Test
    public void testRandomLowerCase() {
        for (int i = 0; i < NUM_TESTS; i++) {
            Character actual = testInstance.lowerCase();
            assertTrue(actual.toString().equals(actual.toString().toLowerCase()));
        }

    }

    @Test
    public void testRandomUpperCase() {
        for (int i = 0; i < NUM_TESTS; i++) {
            Character actual = testInstance.upperCase();
            assertTrue(actual.toString().equals(actual.toString().toUpperCase()));
        }
    }


}
