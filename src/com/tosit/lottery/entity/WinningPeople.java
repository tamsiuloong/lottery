package com.tosit.lottery.entity;

import java.io.Serializable;

/**
 * Created by DELL on 2016/8/29.
 */
public class WinningPeople implements Serializable {
    private String prize;
    private Integer area;
    private Integer row;
    private Integer seat;

    public WinningPeople() {
    }

    public WinningPeople(String prize) {
        this.prize = prize;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
