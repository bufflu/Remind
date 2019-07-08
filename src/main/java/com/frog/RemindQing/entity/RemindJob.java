package com.frog.RemindQing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/7/1 10:05
 *
 * @author guoxinlu
 */
@Data
@TableName("remind_job")
public class RemindJob {

    @TableId("id")
    private String id;
    @TableField("remindid")
    private String remindid;
    @TableField("date")
    private LocalDateTime date;
}
