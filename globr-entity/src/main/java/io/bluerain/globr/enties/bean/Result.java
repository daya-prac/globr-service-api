package io.bluerain.globr.enties.bean;

import java.io.Serializable;

public class Result implements Serializable {

    public static final String SUCCESS = "SUCCESS";
    private int code;
    private String message;
    private String devMessage;
    private Object attachData;

    public Result(int code, String message, String devMessage, Object attachData) {
        super();
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
        this.attachData = attachData;
    }

    public Result(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, String devMessage) {
        super();
        this.code = code;
        this.message = message;
        this.devMessage = devMessage;
    }

    public Result() {
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
        this.attachData = attachData;
    }

    public static Result SUCCESS() {
        return new Result(0, SUCCESS);
    }
}
