package com.jasonnerothin.testing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NumbersTest {

    private static final Integer NUM_ITERATIONS = 12;

    private Numbers testInstance;

    @Before
    public void setUp() throws Exception {
        testInstance = new Numbers();
    }

    @Test
    public void testPositiveDouble() throws Exception {
        for( int i=0; i<NUM_ITERATIONS; i++)
            assertTrue(testInstance.positiveDouble() > 0 );
    }

    @Test
    public void testPositiveLong() throws Exception {
        for( int i=0; i<NUM_ITERATIONS; i++)
            assertTrue(testInstance.positiveLong() > 0 );
    }

    @Test
    public void testPositiveInteger() throws Exception {
        for( int i=0; i<NUM_ITERATIONS; i++)
            assertTrue(testInstance.positiveInteger() > 0 );
    }
}