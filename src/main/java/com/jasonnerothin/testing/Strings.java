package com.jasonnerothin.testing;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:04 AM
 */
public class Strings {

    private static final int DEFAULT_STRING_LENGTH = 8;

    private final EfficientRandom random = new EfficientRandom();
    private final Characters characters = new Characters();

    public String alphabetic(Integer length) {
        StringBuilder builder = new StringBuilder();
        for( int i=0; i<length; i++) {
            if (random.alternate()) builder.append(characters.lowerCase());
            else builder.append(characters.upperCase());
        }
        return builder.toString();
    }

    public String alphabetic() {
        return alphabetic(DEFAULT_STRING_LENGTH);
    }
}
