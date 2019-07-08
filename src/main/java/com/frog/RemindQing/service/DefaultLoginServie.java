package com.frog.RemindQing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.RemindQing.common.Constant;
import com.frog.RemindQing.common.RemindException;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.mapper.UserMapper;
import com.frog.RemindQing.utils.Binarys;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: DefaultLoginServie
 * Description: desc
 * Date: 2019/6/27 9:59
 *
 * @author guoxinlu
 */
public class DefaultLoginServie implements LoginService{

    private UserMapper userMapper;

    public DefaultLoginServie(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void login(User user, HttpServletRequest request) {
        check(user);
        User userDB = userMapper.selectOne(new QueryWrapper<User>().eq("name", user.getName()));
        if (userDB == null) {
            throw new RemindException("该用户未注册，请先注册");
        }
        if (!userDB.getPwd().equals(user.getPwd())) {
            throw new RemindException("密码不正确");
        }
        saveLoginStatus(userDB, request);
    }

    private void saveLoginStatus(User userDB, HttpServletRequest request) {
        int encoder = Binarys.encoder(userDB.getName() + userDB.getId());
        request.getSession().setAttribute(Constant.TOKEN, encoder);
        request.getSession().setAttribute(Constant.USERNAME, userDB.getName());
    }

    private void check(User user) {
        if (StringUtils.isEmpty(user.getName())) {
            throw new RemindException("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPwd())) {
            throw new RemindException("密码不能为空");
        }
    }

    @Override
    public boolean checkLoginStatus(Object... objects) {
        User userDB = userMapper.selectOne(new QueryWrapper<User>().eq("name", objects[0]));
        if (userDB == null) {
            throw new RemindException("该用户未注册，请先注册");
        }
        int encoder1 = Binarys.encoder(userDB.getName() + userDB.getId());
        return (Integer) objects[1] == encoder1;
    }

    @Override
    public User getSessionUser(HttpServletRequest request) {
        Object username = request.getSession().getAttribute(Constant.USERNAME);
        Object token = request.getSession().getAttribute(Constant.TOKEN);
        if (checkLoginStatus(username, token)) {
            return userMapper.selectOne(new QueryWrapper<User>().eq("name", username));
        } else {
            throw new RemindException("请重新登录");
        }
    }
}
