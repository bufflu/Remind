package com.frog.RemindQing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frog.RemindQing.common.Constant;
import com.frog.RemindQing.entity.Calendar;
import com.frog.RemindQing.entity.Remind;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.mapper.CalendarMapper;
import com.frog.RemindQing.mapper.RemindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/7/1 10:19
 *
 * @author guoxinlu
 */
@Service
public class CalendarService {

    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private LoginService loginServie;

    public List<List> list(String date, HttpServletRequest request) {
        String month = date.substring(0, 7);
        String[] split = date.split(Constant.DATE_SPLIT);
        LocalDate localDate = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1);
        List<Calendar> calendarList = calendarMapper.selectList(new QueryWrapper<Calendar>().likeRight("date", month));

        User user = loginServie.getSessionUser(request);

        // 每天有多少个提醒项
        List<Remind> remindList = remindMapper.selectList(new QueryWrapper<Remind>()
                .eq("userid", user.getId())
                .ge("reminddate", localDate)
                .lt("reminddate", localDate.plusMonths(1)));
        Map<String, Long> map = remindList.stream()
                .peek(t -> t.setReminddate(LocalDateTime.of(t.getReminddate().toLocalDate(), LocalTime.MIN)))
                .map(t -> t.getReminddate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        // 返回echars格式
        List<List> list = new ArrayList<>();
        List sub = null;
        for (Calendar calendar : calendarList) {
            sub = new ArrayList();
            sub.add(calendar.getDate());
            sub.add(calendar.getSolarterms() != null ? calendar.getSolarterms() : calendar.getLunar());
            sub.add(map.containsKey(calendar.getDate()) ? map.get(calendar.getDate()) : 0);
            list.add(sub);
        }
        return list;
    }

    public List<String> getMonth() {
        return calendarMapper.selectList(new QueryWrapper<Calendar>().ge("date", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)))
                .stream().map(t -> t.getDate().substring(0, 7))
                .distinct()
                .collect(Collectors.toList());
    }
}
