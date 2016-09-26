package com.tosit.lottery.ui;

import javax.swing.*;

/**
 * Created by DELL on 2016/8/29.
 */
public abstract class BaseJFrame extends JFrame {

    /**
     * 父窗口
     */
    JFrame fatherJframe = this;
    public BaseJFrame() {

    }

    /**
     * 设置默认大小
     */
    public void defaultSize()
    {
        setSize(400,400);
    }

    /**
     * 设置默认其他操作
     */
    public void defaultOperation()
    {
        setLocationRelativeTo(null);
//        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 显示界面
     */
    public abstract  void showUI();

    /**
     * 添加事件处理
     */
    public abstract  void addListener();
}
