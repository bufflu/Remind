package com.frog.RemindQing.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/7/1 10:04
 *
 * @author guoxinlu
 */
@Data
public class Remind {

    private String id;
    private String userid;
    private LocalDateTime createdate;
    private LocalDateTime reminddate;
    private String type;
    private String strategy;
    private String desc;
}
