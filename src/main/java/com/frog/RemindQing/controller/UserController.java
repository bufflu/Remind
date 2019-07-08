package com.frog.RemindQing.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.frog.RemindQing.common.Constant;
import com.frog.RemindQing.common.RemindException;
import com.frog.RemindQing.common.Result;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.mapper.UserMapper;
import com.frog.RemindQing.service.UserService;
import com.frog.RemindQing.utils.WrapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 11:48
 *
 * @author guoxinlu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 用户列表分页
     * @param current
     * @param size
     * @param user 模糊查询条件
     * @return
     */
    @PostMapping("/page/{current}/{size}")
    public Result<User> page(@PathVariable int current, @PathVariable int size, @RequestBody User user) {
        Page<User> page = new Page<>(current, size);
        page.addOrder(OrderItem.desc("id"));
        IPage<User> list = userMapper.selectPage(page, WrapperUtil.getQueryWrapperLike(user));
        return new Result<>(list.getRecords(), list.getTotal());
    }

    /**
     * 用户重置密码
     * @param id
     * @return
     */
    @GetMapping("/reset/{id}")
    public Result resetPwd(@PathVariable String id) {
        User user = new User();
        user.setId(id);
        user.setPwd(Constant.RESET_PWD);
        userMapper.updateById(user);
        return Result.ok();
    }

    /**
     * 用户注册
     * @param code 邀请码
     * @param user 用户信息
     * @return
     */
    @PostMapping("/register/{code}")
    public Result registerUser(@PathVariable String code, @RequestBody User user) {
        if (!user.getPhone().matches(Constant.REGEX_MOBILE)) {
            throw new RemindException("请输入正确的手机号");
        }
        userService.register(user, code);
        return Result.ok();
    }
}
