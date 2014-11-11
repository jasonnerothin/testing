package com.jasonnerothin.testing;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/11/14
 * Time: 10:09 AM
 *
 * Provides an abstraction that we can use a more efficient RNG than java.util.Random
 * at some point in the future (when I have internet access).
 */
public class EfficientRandom {

    private Random random = new Random(System.currentTimeMillis());

    /**
     * This random instance should only be used from the alternate method...
     */
    private boolean flipMe = random.nextBoolean();

    /**
     * Alternates between true and false. (But starts at random.)
     * @return true or false, in an alternating sequence.
     */
    public Boolean alternate(){
        flipMe = !flipMe;
        return flipMe;
    }

    public int nextInt(int n) {
        return random.nextInt(n);
    }
}
