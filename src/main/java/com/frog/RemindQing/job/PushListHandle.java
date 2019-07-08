package com.frog.RemindQing.job;

import com.frog.RemindQing.entity.Remind;

/**
 * ClassName: PushHandle
 * Description: 推送提醒事项的接口，根据类型创建不同实现类完成提醒项的推送<br/>
 *              目前默认策略是事件开始5分钟前和30分中前会触发。
 * Date: 2019/7/4 16:07
 *
 * @author guoxinlu
 */
public interface PushListHandle {

    /**
     * 推送提醒内容
     * @param remind
     */
    boolean send(Remind remind);

    /**
     * 将提醒项添加到推送列表
     * @param remind
     */
    void add(Remind remind);
}
