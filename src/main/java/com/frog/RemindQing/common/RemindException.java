package com.frog.RemindQing.common;

/**
 * ClassName: className
 * Description: desc
 * Date: 2019/6/26 16:45
 *
 * @author guoxinlu
 */
public class RemindException extends RuntimeException {

    public RemindException() {
    }

    public RemindException(String message) {
        super(message);
    }

    public RemindException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemindException(Throwable cause) {
        super(cause);
    }
}
