package com.util.validcode;

import java.awt.*;
import java.util.Random;

public class ColorUtils {

    /**
     * 获取一个随机的颜色
     */
    public static Color getRandColor(int from, int end) {
        Random random = new Random();
        if(from > 255) {
            from = 255;
        }
        if(end > 255) {
            end = 255;
        }
        int red, green, blue;
        //定义随机数的范围取色
        red = from + random.nextInt(end - from);
        green = from + random.nextInt(end - from);
        blue = from + random.nextInt(end - from);

        return new Color(red, green, blue);
    }
}
