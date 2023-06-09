package com.tah.housewarming.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    public static Integer integer() {
        return randomInt(null);
    }

    public static Integer integer(Integer bound) {
        return randomInt(bound);
    }

    private static Integer randomInt(Integer bound) {
        if(bound == null)
            bound = Integer.MAX_VALUE;

        return new Random().nextInt(bound);
    }

    public static String string() {
        return randomString(8);
    }

    public static String string(Integer bound) {
        return randomString(bound);
    }

    private static String randomString(Integer bound) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bound; i++) {
            char c = (char) (randomInt(null) + 'a');
            sb.append(c);
        }

        return sb.toString();
    }

    public static List<String> stringList() {
        return randomList(randomInt(15));
    }

    private static List<String> randomList(Integer count) {
        List<String> list = new ArrayList<>();

        for(var i = 0; i < count; i++){
            list.add(randomString(randomInt(45)));
        }

        return list;
    }
}
