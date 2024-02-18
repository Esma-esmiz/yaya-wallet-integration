package com.yaya.yaya.wallet.Auth;

public class serverTime {
    private String time;

    public serverTime(String time) {
        this.time = time;
    }

    public serverTime() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "serverTime{" +
                "time='" + time + '\'' +
                '}';
    }
}
