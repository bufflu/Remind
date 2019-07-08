package com.frog.RemindQing.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.RemindQing.common.Result;
import com.frog.RemindQing.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ClassName: CalendarController
 * Description: desc
 * Date: 2019/7/1 10:09
 *
 * @author guoxinlu
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    /**
     * 返回日历列表数据，包括农历和提醒项数量
     * @param date 月份，为空使用当月
     * @param request
     * @return list
     */
    @GetMapping("/list")
    public Result list(String date, HttpServletRequest request) {
        date = StringUtils.isEmpty(date) ? LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) : date;
        List list = calendarService.list(date, request);
        return Result.ok(list);
    }

    /**
     * 返回提醒项添加或查看可用的月份
     * @return list
     */
    @GetMapping("getMonth")
    public Result getMonth() {
        return Result.ok(calendarService.getMonth());
    }

}
