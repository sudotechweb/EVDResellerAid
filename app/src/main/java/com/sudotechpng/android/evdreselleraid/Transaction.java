package com.sudotechpng.android.evdreselleraid;

/**
 * Created by syagi on 01/21/2018.
 */

public class Transaction {
    private String name;
    private String onClickMethod;
    private String transNbr;
    private String transAmt;

    public Transaction() {}

    public String getTransNbr() {
        return transNbr;
    }

    public void setTransNbr(String transNbr) {
        this.transNbr = transNbr;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnClickMethod() {
        return onClickMethod;
    }

    public void setOnClickMethod(String onClickMethod) {
        this.onClickMethod = onClickMethod;
    }

    public Transaction(String name, String onClickMethod, String transNbr, String transAmt) {
        this.name = name;
        this.onClickMethod = onClickMethod;
        this.transNbr = transNbr;
        this.transAmt = transAmt;
    }
}
