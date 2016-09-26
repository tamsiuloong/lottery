package com.tosit.lottery.ui;

import com.tosit.lottery.entity.ApplicationDB;
import com.tosit.lottery.entity.WinningPeople;
import com.tosit.lottery.service.TcpRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DELL on 2016/8/29.
 */
public class ShowAllResultUI extends  BaseJFrame{
    TcpRequest request = new TcpRequest();
    JTable table;
    JButton btnUpload = new JButton("上传抽奖结果");
    // 定义二维数组作为表格数据
    Object[][] tableData =
            {
                    new Object[]{"李清照" , 29 , "女"},
                    new Object[]{"苏格拉底", 56 , "男"},
                    new Object[]{"李白", 35 , "男"},
                    new Object[]{"弄玉", 18 , "女"},
                    new Object[]{"虎头" , 2 , "男"}
            };
    // 定义一维数据作为列标题
    Object[] columnTitle = {"奖品" , "区" , "排","座"};


    public ShowAllResultUI() {
        initData();
        showUI();
        addListener();
    }


    /**
     * 初始化数据
     */
    private void initData()
    {
        tableData = new Object[ApplicationDB.winningPeopleList.size()][4];
        for(int i = 0 ;i < tableData.length;i++)
        {
            WinningPeople winningPeople = ApplicationDB.winningPeopleList.get(i);
            tableData[i] = new Object[]{winningPeople.getPrize(),winningPeople.getArea(),winningPeople.getRow(),winningPeople.getSeat()};
        }
    }
    @Override
    public void showUI() {
        defaultSize();
        // 以二维数组和一维数组来创建一个JTable对象
        table = new JTable(tableData , columnTitle);
        // 将JTable对象放在JScrollPane中，
        // 并将该JScrollPane放在窗口中显示出来
        this.add(new JScrollPane(table));
        this.add(btnUpload, BorderLayout.SOUTH);
        defaultOperation();
    }

    @Override
    public void addListener() {
        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                request.upload(ApplicationDB.winningPeopleList);
            }
        });
    }

    public static void main(String[] args) {
        new ShowAllResultUI();
    }
}
