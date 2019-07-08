package com.frog.RemindQing.job;

import com.frog.RemindQing.entity.Remind;
import com.frog.RemindQing.entity.RemindJob;
import com.frog.RemindQing.mapper.RemindJobMapper;
import com.frog.RemindQing.utils.PrimaryKey;

import java.time.LocalDateTime;

/**
 * ClassName: DefaultPushHandle
 * Description: desc
 * Date: 2019/7/4 16:13
 *
 * @author guoxinlu
 */
public class DefaultPushHandle implements PushListHandle {

    private RemindJobMapper remindJobMapper;

    public DefaultPushHandle(RemindJobMapper remindJobMapper) {
        this.remindJobMapper = remindJobMapper;
    }

    @Override
    public boolean send(Remind remind) {
        System.out.println(LocalDateTime.now() + "-推送给[" + remind.getUserid() + "]的内容是："+ remind.getReminddate() + "您的提醒事项是：" + remind.getDesc());
        return true;
    }


    @Override
    public void add(Remind remind) {
        RemindJob job = new RemindJob();
        job.setId(PrimaryKey.create());
        job.setRemindid(remind.getId());
        job.setDate(remind.getReminddate().minusMinutes(5));
        remindJobMapper.insert(job);
        if (remind.getCreatedate().plusMinutes(30).isBefore(remind.getReminddate())) {
            job.setId(PrimaryKey.create());
            job.setDate(remind.getReminddate().minusMinutes(30));
            remindJobMapper.insert(job);
        }
    }
}
