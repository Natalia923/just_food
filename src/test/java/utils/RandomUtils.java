package utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {


    public static Long getRandomLong(long min, long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

}
