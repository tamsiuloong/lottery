package com.tosit.lottery.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局数据存储
 * Created by DELL on 2016/8/29.
 */
public class ApplicationDB {

    public static  List<WinningPeople> winningPeopleList = new ArrayList<>();//所有中奖人名单
    public static WinningPeople currentWinningPeople;//当前中奖人
    public final static Integer TOTAL_AREAS=20;//场馆一共几区
    public final static Integer TOTAL_ROWS=30;//一区一共几排
    public final static Integer TOTAL_SEATS=40;//每一排一共几座

    public final static int SELECT_AREA=1;
    public final static int SELECT_ROW=2;
    public final static int SELECT_SEAT=3;
    public final static int SHOW_RESULT=4;//公布结果
    public static int currentStep =SELECT_AREA;//当前抽奖步骤
}
