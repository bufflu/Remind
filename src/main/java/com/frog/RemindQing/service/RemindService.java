package com.frog.RemindQing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frog.RemindQing.common.Constant;
import com.frog.RemindQing.common.RemindException;
import com.frog.RemindQing.entity.Remind;
import com.frog.RemindQing.entity.RemindAdd;
import com.frog.RemindQing.entity.RemindJob;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.job.PushListHandle;
import com.frog.RemindQing.mapper.RemindJobMapper;
import com.frog.RemindQing.mapper.RemindMapper;
import com.frog.RemindQing.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/7/3 8:36
 *
 * @author guoxinlu
 */
@Service
public class RemindService {

    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private LoginService loginServie;
    @Autowired
    private RemindJobMapper remindJobMapper;
    @Autowired
    private PushListHandle pushHandle;

    public List<Remind> list(String date, HttpServletRequest request) {
        User user = loginServie.getSessionUser(request);
        String[] split = date.split(Constant.DATE_SPLIT);
        LocalDateTime time = LocalDateTime.of(LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])), LocalTime.MIN);
        return remindMapper.selectList(new QueryWrapper<Remind>()
                .eq("userid", user.getId())
                .ge("reminddate", time)
                .lt("reminddate", time.plusDays(1)));
    }

    @Transactional
    public void add(@RequestBody RemindAdd remindAdd, HttpServletRequest request) {
        User user = loginServie.getSessionUser(request);

        // 查询
        LocalDateTime time = LocalDateTime.of(remindAdd.getYear(), remindAdd.getMonth(), remindAdd.getDay(), 0, 0);
        Integer count = remindMapper.selectCount(new QueryWrapper<Remind>().eq("userid", user.getId())
                .ge("reminddate", time)
                .lt("reminddate", time.plusDays(1)));
        if (count >= 10) {
            throw new RemindException("已经有10条提醒事项哟，再添加就忙不过来了呢");
        }

        // 校验
        LocalDateTime reminddate = LocalDateTime.of(remindAdd.getYear(),remindAdd.getMonth(),remindAdd.getDay(),remindAdd.getHour(),remindAdd.getMinute());
        LocalDateTime nowTime = LocalDateTime.now();
        if (nowTime.isAfter(reminddate)) {
            throw new RemindException("提醒时间要大于当前时间呦");
        }
        if (nowTime.plusMinutes(10).isAfter(reminddate)) {
            throw new RemindException("10分钟之后的事情就不需要提醒了吧");
        }

        // 添加1
        Remind remind = new Remind();
        remind.setId(PrimaryKey.create());
        remind.setUserid(user.getId());
        remind.setCreatedate(nowTime);
        remind.setReminddate(reminddate);
        remind.setStrategy(remindAdd.getStrategy());
        remind.setType(remindAdd.getType());
        remind.setDesc(remindAdd.getDesc());
        remindMapper.insert(remind);

        // 添加2
        pushHandle.add(remind);
    }


    @Transactional
    public void remove(@PathVariable String id) {
        remindMapper.deleteById(id);
        remindJobMapper.delete(new QueryWrapper<RemindJob>().eq("remindid", id));
    }
}
