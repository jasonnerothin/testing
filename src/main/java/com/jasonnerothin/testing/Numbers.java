package com.jasonnerothin.testing;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 11/13/14
 * Time: 7:51 PM
 */
public class Numbers {

    private static final Integer BIG_ENOUGH = (Integer.MAX_VALUE >> 4);

    private EfficientRandom random = new EfficientRandom();

    public Double positiveDouble(){
        return Math.abs(random.nextDouble());
    }

    public Long positiveLong(){
        return Math.abs(random.nextLong());
    }

    public Integer positiveInteger(){
        return Math.abs(random.nextInt(BIG_ENOUGH));
    }
}
