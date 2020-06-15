package org.MyProWeChat.base;

import org.MyProWeChat.base.utils.StringUtil;

public abstract class BUSIException extends RuntimeException {

    private static final long serialVersionUID = 8885935535960919973L;
    private String str;

    public BUSIException() {
    }

    public BUSIException(String msg) {
        super(msg);
    }

    public BUSIException(Throwable cause) {
        super(cause);
    }

    public BUSIException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public String getMessage() {
        return getMessage(false);
    }

    public String getLocalMessage() {
        return getMessage(true);
    }

    private String getMessage(boolean isLocal) {
        String message = null;
        if (str == null) {
            message = super.getMessage();
        } 
        if (StringUtil.isEmpty(getCode())) {
            return message;
        }
        String prefix = "[" + getCode() + "]";
        return prefix + message;
    }

    public abstract String getCode();
}
