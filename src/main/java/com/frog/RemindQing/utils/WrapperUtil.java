package com.frog.RemindQing.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frog.RemindQing.common.RemindException;

import java.lang.reflect.Field;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 16:16
 *
 * @author guoxinlu
 */
public class WrapperUtil {

    public static <T> QueryWrapper<T> getQueryWrapperLike(T t) {
        if (t == null) return null;
        Field[] fields = t.getClass().getDeclaredFields();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(t) != null) {
                    queryWrapper.like(field.getName(), field.get(t));
                }
            }
        } catch (Exception e) {
            throw new RemindException(e.getMessage());
        }
        return queryWrapper;
    }

}
