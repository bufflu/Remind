package com.frog.RemindQing.entity;

import lombok.Data;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/7/2 17:01
 *
 * @author guoxinlu
 */
@Data
public class RemindAdd {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private String type;
    private String strategy = "0";
    private String desc;
}
