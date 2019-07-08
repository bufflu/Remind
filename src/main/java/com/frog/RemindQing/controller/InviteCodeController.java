package com.frog.RemindQing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.frog.RemindQing.common.Result;
import com.frog.RemindQing.entity.InviteCode;
import com.frog.RemindQing.mapper.InviteCodeMapper;
import com.frog.RemindQing.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: InviteCodeController
 * Description: 注册时使用邀请码
 * Date: 2019/6/26 17:39
 *
 * @author guoxinlu
 */
@RestController
@RequestMapping("/inviteCode")
public class InviteCodeController {

    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    /**
     * 邀请码列表
     * @param current 当前页
     * @param size 每页数量
     * @return list
     */
    @PostMapping("/page/{current}/{size}")
    public Result<InviteCode> page(@PathVariable int current, @PathVariable int size) {
        Page<InviteCode> page = new Page<>(current, size);
        page.addOrder(OrderItem.desc("count"));
        IPage<InviteCode> list = inviteCodeMapper.selectPage(page, null);
        return new Result<>(list.getRecords(), list.getTotal());
    }

    /**
     * 生成一个随机的邀请码
     * @return
     */
    @GetMapping("/create")
    public Result create() {
        InviteCode code = new InviteCode();
        code.setCode(PrimaryKey.getRandom(16));
        code.setCount(50);
        inviteCodeMapper.insert(code);
        return Result.ok();
    }

    /**
     * 禁用所有邀请码
     * @return
     */
    @GetMapping("/stop")
    public Result stop() {
        InviteCode code = new InviteCode();
        code.setCount(0);
        inviteCodeMapper.update(code, null);
        return Result.ok();
    }
}
