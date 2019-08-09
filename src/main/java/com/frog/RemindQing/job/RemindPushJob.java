package com.frog.RemindQing.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frog.RemindQing.entity.Remind;
import com.frog.RemindQing.entity.RemindJob;
import com.frog.RemindQing.mapper.RemindJobMapper;
import com.frog.RemindQing.mapper.RemindMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: RemindPushJob
 * Description: desc
 * Date: 2019/7/3 17:25
 *
 * @author guoxinlu
 */
@Component
public class RemindPushJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RemindJobMapper remindJobMapper;
    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private PushListHandle pushHandle;

    @Scheduled(initialDelay = 100 * 60 * 1000, fixedDelay = 60000)
    public void push() {
        LocalDateTime now = LocalDateTime.now();
        List<String> remindIdList = remindJobMapper.selectList(new QueryWrapper<RemindJob>().lt("date", now))
                .stream().map(RemindJob::getRemindid).collect(Collectors.toList());
        List<Remind> remindList = remindMapper.selectBatchIds(remindIdList);
        try {
            for (Remind remind : remindList) {
                //System.out.println("推送给[" + remind.getUserid() + "]的内容是："+ remind.getReminddate() + "您的提醒事项是：" + remind.getDesc());
                //remind.getUserid();     // 用户id
                //remind.getReminddate(); // 事件时间
                //remind.getDesc();       // 内容
                //remind.getType();       // 提醒类型
                boolean boo = pushHandle.send(remind);
                if (boo) {
                    remindJobMapper.deleteBatchIds(remindIdList);
                }
            }
        } catch (Exception e) {
            logger.error("推送提醒事项时异常：", e);
        }
    }
}
