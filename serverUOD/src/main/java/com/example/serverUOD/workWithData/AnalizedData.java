package com.example.serverUOD.workWithData;

public class AnalizedData {
    public Double ch0;
    public Double ch1;
    public Double ch2;
    public Double ch3;
    public Double ch4;
    public Double ch5;
    public Double ch6;
    public Double temp;
    public String timestamp;
    public String target;
    public AnalizedData(Double ch0, Double ch1,  Double ch2, Double ch3, Double ch4, Double ch5, Double ch6, String time, String target, Double temp){
        this.ch0 = ch0;
        this.ch1 = ch1;
        this.ch2 = ch2;
        this.ch3 = ch3;
        this.ch4 = ch4;
        this.ch5 = ch5;
        this.ch6 = ch6;
        this.timestamp = time;
        this.target = target;
        this.temp = temp;
    }
    @Override
    public String toString(){
        return "{ch0 =" + ch0 + ",ch1=" + ch1 + ",ch2=" + ch2 + ",ch3=" + ch3 + ",ch4=" + ch4 + ",ch5=" + ch5 + ",ch6=" + ch6 + ",timestamp=" + timestamp + ",temp=" + temp + ",target=" + target + "}";
    }
}
