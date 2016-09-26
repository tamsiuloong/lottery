package com.tosit.lottery.ui;

import com.tosit.lottery.entity.ApplicationDB;
import com.tosit.lottery.entity.WinningPeople;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DELL on 2016/8/29.
 */
public class MainUI extends BaseJFrame {
    JLabel lblTitle = new JLabel("周年庆典现场抽奖");
    JTable prizeTable = new JTable();//奖品列表
    JButton btnNext = new JButton("选区");
    // 定义二维数组作为表格数据
    Object[][] tableData =
            {
                    new Object[]{"全英雄，全皮肤" },
                    new Object[]{"三国全英雄，全皮肤" }
            };
    // 定义一维数据作为列标题
    Object[] columnTitle = {"奖品"};

    @Override
    public void showUI() {
        defaultSize();
        // 以二维数组和一维数组来创建一个JTable对象
        prizeTable = new JTable(tableData , columnTitle);

        add(lblTitle, BorderLayout.NORTH);
        add(btnNext,BorderLayout.SOUTH);
        // 将JTable对象放在JScrollPane中，
        // 并将该JScrollPane放在窗口中显示出来
        add(new JScrollPane(prizeTable));

        defaultOperation();
    }

    @Override
    public void addListener() {
        //添加事件监听
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取当前选中的奖品
                String prize = (String)prizeTable.getValueAt(prizeTable.getSelectedRow(),prizeTable.getSelectedColumn());

                //在全局数据对象中设置奖品
                WinningPeople winningPeople = new WinningPeople(prize);
                ApplicationDB.currentWinningPeople = winningPeople;


                fatherJframe.setVisible(false);
                //打开新窗口
                new SelectUI();
            }
        });
    }

    public MainUI(){
        showUI();
        addListener();
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
