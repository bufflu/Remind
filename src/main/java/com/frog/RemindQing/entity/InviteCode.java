package com.frog.RemindQing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 15:18
 *
 * @author guoxinlu
 */
@Data
@TableName("invite_code")
public class InviteCode {

    @TableId("code")
    private String code;

    @TableField("count")
    private int count;
}
