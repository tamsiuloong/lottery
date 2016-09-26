package com.tosit.lottery.ui;

import com.tosit.lottery.entity.ApplicationDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by DELL on 2016/8/29.
 */
public class ResultUI extends  BaseJFrame{
    JLabel lblPrize=new JLabel(ApplicationDB.currentWinningPeople.getPrize());//奖品信息
    JLabel lblSeatNo=new JLabel(ApplicationDB.currentWinningPeople.getArea()+" 区 "+ApplicationDB.currentWinningPeople.getRow()+" 排 "+ApplicationDB.currentWinningPeople.getSeat()+"座");//座位号

//    JLabel lblPrize=new JLabel("全英雄 全皮肤");//奖品信息
//    JLabel lblSeatNo=new JLabel("1 区 2 排 3座");//座位号

    JPanel pCenter = new JPanel();

    JButton btnNext = new JButton("下一位");
    JButton btnShowAllResult = new JButton("所有中奖名单");
    @Override
    public void showUI() {
        setTitle("抽奖结果");
        defaultSize();

        lblPrize.setFont(new Font("宋体",Font.BOLD, 20));
        lblSeatNo.setFont(new Font("宋体",Font.BOLD, 20));
        pCenter.add(lblPrize);
        pCenter.add(lblSeatNo);

        add(pCenter);

        add(btnNext,BorderLayout.WEST);
        add(btnShowAllResult,BorderLayout.EAST);
        defaultOperation();
    }

    @Override
    public void addListener() {
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fatherJframe.setVisible(false);
                ApplicationDB.currentStep= ApplicationDB.SELECT_AREA;
                new MainUI();
            }
        });
        btnShowAllResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fatherJframe.setVisible(false);
                new ShowAllResultUI();
            }
        });
    }

    public ResultUI() {
        showUI();
        addListener();
    }

    public static void main(String[] args) {
        new ResultUI();
    }
}
