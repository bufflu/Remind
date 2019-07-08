package com.frog.RemindQing.utils;

import java.nio.charset.Charset;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/27 10:20
 *
 * @author guoxinlu
 */
public class Binarys {

    public static int encoder(String src) {
        return CRC16Util.calcCrc16(src.getBytes(Charset.forName("utf-8")));
    }

}
