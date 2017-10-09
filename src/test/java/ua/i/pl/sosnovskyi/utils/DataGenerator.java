package ua.i.pl.sosnovskyi.utils;

import java.util.Random;

/**
 * Created by A Sosnovskyi on 08.10.2017.
 */
public class DataGenerator {
    private static Random random = new Random(System.currentTimeMillis());


    private char[] chars = ("abcdefghigklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
//       private int nameLength = DataGenerator.random.nextInt(20);

    public String getName() {
        int nameLength = DataGenerator.random.nextInt(20);

        if (nameLength < 3) {
            nameLength = 3;
        }
        char[] buf = new char[nameLength];
        for (int i = 0; i < nameLength; i++) {
            buf[i] = chars[random.nextInt(chars.length)];
        }
        return String.copyValueOf(buf);     //.toString();
    }

    public int getQuantity() {
        int result = random.nextInt(99);
        return result + 1;
    }

    public float getPrice() {
        int result = (random.nextInt(999)) + 1;
        return result / 10f;
    }

}
