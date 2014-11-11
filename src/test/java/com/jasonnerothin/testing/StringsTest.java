package com.jasonnerothin.testing;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:28 AM
 */
public class StringsTest {

    private Strings testInstance;

    @Before
    public void setup(){
        testInstance = new Strings();
    }

    @Test
    public void testAlphabeticLength() {
        int testLen = 4;
        assertEquals(testInstance.alphabetic(testLen).length(), testLen, 0);
    }

    @Test
    public void testAlphabeticIsAlphabetic(){

        String actual = testInstance.alphabetic(100);

        for( char ch : actual.toCharArray() ){
            boolean little = ch >= 'a' && ch <= 'z';
            boolean big = ch >= 'A' && ch <= 'Z';

            assertTrue( little || big );
        }

    }

}
