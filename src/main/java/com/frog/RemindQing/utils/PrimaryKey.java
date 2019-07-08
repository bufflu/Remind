package com.frog.RemindQing.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Random;

import static java.time.temporal.ChronoField.*;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 17:52
 *
 * @author guoxinlu
 */
public class PrimaryKey {

    private final static String CHARS = "0123456789QWERTYUIOPLKJHGFDSAZXCVBNM";

    /**
     * 主键生成，yyyyMMddHHmiss + 6位随机数
     * @return
     */
    public static String create() {
        return getNowTime() + getRandom(6);
    }

    public static String getNowTime() {
        return LocalDateTime.now()
                .format(new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendValue(YEAR, 4)
                        .appendValue(MONTH_OF_YEAR, 2)
                        .appendValue(DAY_OF_MONTH, 2)
                        .appendValue(HOUR_OF_DAY, 2)
                        .appendValue(MINUTE_OF_HOUR, 2)
                        .appendValue(SECOND_OF_MINUTE, 2)
                        .optionalStart()
                        .toFormatter());
    }

    public static String getRandom(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(create());
    }
}
