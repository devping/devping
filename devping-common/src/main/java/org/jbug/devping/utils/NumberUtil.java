package org.jbug.devping.utils;

/**
 * Created by jhouse on 10/3/14.
 */
public class NumberUtil {
    public static int randomInteger(int min, int max) {
        int num = min + (int) (Math.random() * max);
        return num;
    }
}
