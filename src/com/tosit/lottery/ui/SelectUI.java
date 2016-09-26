package com.tosit.lottery.ui;

import com.tosit.lottery.entity.ApplicationDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 选区
 * Created by DELL on 2016/8/29.
 */
public class SelectUI extends BaseJFrame{
    JLabel lblTitle = new JLabel("选区");
    JButton btnStop = new JButton("停");
    JButton btnStart = new JButton("开始");
    JButton btnNext = new JButton("选排");
    JPanel pCenter = new JPanel();//显示随机数
    JButton[] btnNums;//中间所有按钮
    JButton btnSelected;//当前变色按钮
    Boolean isGoon = true;
    Integer selectNum;
    Integer count=ApplicationDB.TOTAL_AREAS ;//生成按钮数量(默认区数)
    @Override
    public void showUI() {
        defaultSize();

        btnNext.setEnabled(false);
        btnStop.setEnabled(false);

        add(lblTitle, BorderLayout.NORTH);
        drawCenter();
        add(btnNext, BorderLayout.SOUTH);
        add(btnStop,BorderLayout.EAST);
        add(btnStart,BorderLayout.WEST);
        defaultOperation();

    }

    public void start()
    {
        new Thread(new Runnable() {

            @Override
            public void run() {
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                while(isGoon)
                {
                    //产生随机数
                    int randomNum = new Random().nextInt(count)+1;
//                    System.out.println(randomNum);
                    //改变随机数对应按钮背景
                    //从数组中拿到对应按钮
                    try{
                        //先恢复之前选中按钮颜色
                        if(btnSelected!=null)
                        {
                            btnSelected.setBackground(new Color(Integer.decode("#FFF0F5")));
                        }

                        JButton btnRandom = btnNums[randomNum-1];
                        btnSelected=btnRandom;
                        selectNum = randomNum;
                        btnRandom.setBackground(new Color(Integer.decode("#FFA500")));

                    }catch (ArrayIndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }

                    //改变他的背景
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    @Override
    public void addListener() {

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        KeyAdapter keyAdapter = new SelectUIKeyAdapter();
        //设置快捷键
        btnStart.addKeyListener(keyAdapter);
        btnStart.addKeyListener(keyAdapter);
        btnStop.addKeyListener(keyAdapter);
    }

    private void stop()
    {
        btnStop.setEnabled(false);
        btnNext.setEnabled(true);
        isGoon = false;
        switch (ApplicationDB.currentStep)
        {
            case ApplicationDB.SELECT_AREA:
                ApplicationDB.currentWinningPeople.setArea(selectNum);
                break;
            case ApplicationDB.SELECT_ROW:
                ApplicationDB.currentWinningPeople.setRow(selectNum);
                break;
            case ApplicationDB.SELECT_SEAT:
                ApplicationDB.currentWinningPeople.setSeat(selectNum);
                break;
            default:
                ApplicationDB.currentWinningPeople.setArea(selectNum);
                break;
        }
        //在下一步之前，不能再次点击
//        btnStart.setEnabled(false);
    }
    private void next()
    {
        btnNext.setEnabled(false);
        btnStart.setEnabled(true);
        //                fatherJframe.setVisible(false);
        if(ApplicationDB.currentStep==ApplicationDB.SELECT_SEAT)
        {
            //将当前中奖人加入 中奖名单
            ApplicationDB.winningPeopleList.add(ApplicationDB.currentWinningPeople);
            new ResultUI();
        }
        else
        {
            isGoon = true;
            //重新绘制pCenter
            ApplicationDB.currentStep++;
            drawCenter();
            btnStart.setEnabled(true);
        }
    }

    /**
     * 绘制中间部分
     */
    private void drawCenter(){
        //清空pcenter
        fatherJframe.remove(pCenter);

        //选排
        if(ApplicationDB.currentStep==ApplicationDB.SELECT_AREA)
        {
            count=ApplicationDB.TOTAL_AREAS;
        }
        switch (ApplicationDB.currentStep)
        {
            case ApplicationDB.SELECT_AREA:
                count=ApplicationDB.TOTAL_AREAS;
                lblTitle.setText("选区");
                btnNext.setText("选排");
                break;
            case ApplicationDB.SELECT_ROW:
                count=ApplicationDB.TOTAL_ROWS;
                lblTitle.setText("选排");
                btnNext.setText("选座");
                break;
            case ApplicationDB.SELECT_SEAT:
                count=ApplicationDB.TOTAL_SEATS;
                lblTitle.setText("选座");
                btnNext.setText("显示结果");
                break;
            default:
                count=ApplicationDB.TOTAL_AREAS;
        }


        btnNums = new JButton[count];
        pCenter = new JPanel();
        for(int i = 1 ; i <= count;i++)
        {
            JButton btnNum = new JButton(""+i);
            btnNum.setBackground(new Color(Integer.decode("#FFF0F5")));
            //添加到数组中
            btnNums[i-1]=btnNum;
            //添加到容器面板
            pCenter.add(btnNum);
        }
        add(pCenter);
    }

    public SelectUI()  {
        showUI();
        addListener();
    }

    class SelectUIKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            int keyCode=e.getKeyCode();
            if(keyCode==KeyEvent.VK_DOWN ){
                next();
            }else if(keyCode==KeyEvent.VK_LEFT ){
                start();
            }else if(keyCode==KeyEvent.VK_RIGHT ){
                stop();
            }
        }
    }
    public static void main(String[] args) {
        new SelectUI();
        //产生随机数
        System.out.println(new Random().nextInt(ApplicationDB.TOTAL_AREAS+1));
    }
}
