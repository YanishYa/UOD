package com.example.serverUOD.workWithData;

public class FullAnalizedData {
    public Double ch0;
    public Double ch1;
    public Double ch2;
    public Double ch3;
    public Double ch4;
    public Double ch5;
    public Double ch6;
    public Double temp;
    public String timestamp;
    public int target0;
    public int target1;
    public int target2;
    public int target3;
    public int target4;
    public int target5;
    public int target6;
    public int tempTarget;


    public FullAnalizedData(Double ch0, Double ch1,  Double ch2, Double ch3, Double ch4, Double ch5, Double ch6, Double temp, String time, int target0, int target1, int target2, int target3, int target4, int target5, int target6, int tempTarget){
        this.ch0 = ch0;
        this.ch1 = ch1;
        this.ch2 = ch2;
        this.ch3 = ch3;
        this.ch4 = ch4;
        this.ch5 = ch5;
        this.ch6 = ch6;
        this.temp = temp;
        this.timestamp = time;
        this.target0 = target0;
        this.target1 = target1;
        this.target2 = target2;
        this.target3 = target3;
        this.target4 = target4;
        this.target5 = target5;
        this.target6 = target6;
        this.tempTarget = tempTarget;
    }
    @Override
    public String toString(){
        return "{ch0 =" + ch0 + ",ch1=" + ch1 + ",ch2=" + ch2 + ",ch3=" + ch3 + ",ch4=" + ch4 + ",ch5=" + ch5 + ",ch6=" + ch6 + ",temp = " + temp + ",timestamp=" + timestamp + ",temp=" + temp + "target0 =" + target0 + ",target1=" + target1 + ",target2=" + target2 + ",target3=" + target3 + ",target4=" + target4 + ",target5=" + target5 + ",target6=" + target6 + ",tempTarget=" + tempTarget + "}";

    }
}
