package com.tosit.lottery.entity;

import java.io.Serializable;

/**
 * Created by DELL on 2016/8/22.
 */
public class RequestData implements Serializable{
    //请求指令(增删改查...)
    private  String cmd;//edit
    //请求数据
    private  Object data;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
