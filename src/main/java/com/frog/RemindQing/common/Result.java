package com.frog.RemindQing.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Result
 * Description: Result
 * Date: 2018/9/13 16:09
 *
 * @author guoxinlu
 */
@Data
public class Result<T> {

    private boolean success     = true;
    private String  message     = "成功";
    private long    total       = 0;
    private String  username    = "NA";
    private List<T> data        = new ArrayList<>();

    public Result() {}

    public Result(List<T> data) {
        if (data != null && data.size() > 0) {
            this.data = data;
        }
    }

    public Result(T data) {
        this.data.add(data);
    }

    public Result(List<T> data, long total) {
        if (data != null && data.size() > 0) {
            this.data = data;
        }
        this.total = total;
    }

    public static Result ok() {
        return new Result();
    }

    public static <T> Result ok(List<T> data) {
        return new Result<>(data);
    }

    public static <T> Result ok(T data) {
        return new Result<>(data);
    }

    public static Result error(String message) {
        Result r = new Result();
        r.setSuccess(false);
        r.setMessage(message);
        return r;
    }

}
