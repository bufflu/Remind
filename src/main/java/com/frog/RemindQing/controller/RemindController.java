package com.frog.RemindQing.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.RemindQing.common.Result;
import com.frog.RemindQing.entity.RemindAdd;
import com.frog.RemindQing.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: RemindController
 * Description: desc
 * Date: 2019/7/2 16:04
 *
 * @author guoxinlu
 */
@RestController
@RequestMapping("remind")
public class RemindController {

    @Autowired
    private RemindService remindService;

    @GetMapping("/list")
    public Result list(String date, HttpServletRequest request) {
        return Result.ok(remindService.list(date, request));
    }

    @PostMapping("/add")
    public Result add(@RequestBody RemindAdd remindAdd, HttpServletRequest request) {
        if (StringUtils.isEmpty(remindAdd.getDesc())) {
            throw new RuntimeException("提醒内容不能为空");
        }
        if (remindAdd.getYear() < 1 || remindAdd.getMonth() < 1 || remindAdd.getDay() < 1 ||
                remindAdd.getYear() > 2099 || remindAdd.getMonth() > 12 || remindAdd.getDay() > 31) {
            throw new RuntimeException("请输入正确的日期");
        }
        remindService.add(remindAdd, request);
        return Result.ok();
    }

    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id) {
        remindService.remove(id);
        return Result.ok();
    }
}
