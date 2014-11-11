package com.jasonnerothin.testing;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:05 AM
 */
public class Characters {

    private static final char A = 'A';
    private static final char a = 'a';

    private final EfficientRandom random = new EfficientRandom();

    public Character upperCase() {
        return (char) (A + random.nextInt(26));
    }

    public Character lowerCase(){
        return (char) (a+ random.nextInt(26));
    }
}
