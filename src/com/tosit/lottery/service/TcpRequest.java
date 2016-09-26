package com.tosit.lottery.service;

import com.google.gson.Gson;
import com.tosit.lottery.entity.RequestData;
import com.tosit.lottery.entity.WinningPeople;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by DELL on 2016/8/18.
 */
public class TcpRequest {

    public static final String IP = "localhost";
    public static final int PORT = 8888;

    /**
     * 保存这次的所有中奖名单
     * @param winningPeopleList
     */
    public void upload(List<WinningPeople> winningPeopleList) {
        RequestData requestData = new RequestData();
        requestData.setCmd("upload");
        requestData.setData(winningPeopleList);

        try {
            Socket socket = new Socket(IP,PORT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(requestData);
            //获取响应
            boolean isOk = ois.readBoolean();
            System.out.println("更新请求结果:"+isOk);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
//    public void update(Student student) {
//
//        //准备一个封装数据对象
//        RequestData requestData = new RequestData();
//        requestData.setCmd("update");
//        requestData.setData(student);
//
//
//        //将封装数据对象传输到server
//        try {
//            Socket socket = new Socket(IP,PORT);
//            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//
//            oos.writeObject(requestData);
//            //获取响应
//            boolean isOk = ois.readBoolean();
//            System.out.println("更新请求结果:"+isOk);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        RequestData requestData = new RequestData();
        requestData.setCmd("update");
        requestData.setData(new String("hello"));

        System.out.println(gson.toJson(requestData));
    }
}
