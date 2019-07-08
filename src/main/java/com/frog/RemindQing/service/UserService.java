package com.frog.RemindQing.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.RemindQing.common.RemindException;
import com.frog.RemindQing.entity.InviteCode;
import com.frog.RemindQing.entity.User;
import com.frog.RemindQing.mapper.InviteCodeMapper;
import com.frog.RemindQing.mapper.UserMapper;
import com.frog.RemindQing.utils.PrimaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 15:29
 *
 * @author guoxinlu
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    @Transactional
    public void register(User user, String code) {
        check(user, code);
        InviteCode inviteCode = inviteCodeMapper.selectById(code);
        if (inviteCode.getCount() < 1) {
            throw new RemindException("此激活码已过期");
        }
        Integer count = userMapper.selectCount(new QueryWrapper<User>().eq("name", user.getName()));
        if (count > 0) {
            throw new RemindException("该用户名已经存在");
        }
        user.setId(PrimaryKey.create());
        userMapper.insert(user);
        inviteCode.setCount(inviteCode.getCount() - 1);
        inviteCodeMapper.updateById(inviteCode);
    }

    private void check(User user, String code) {
        if (StringUtils.isEmpty(code)) {
            throw new RemindException("激活码不能为空");
        }
        if (StringUtils.isEmpty(user.getName())) {
            throw new RemindException("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPwd())) {
            throw new RemindException("密码不能为空");
        }
    }
}
