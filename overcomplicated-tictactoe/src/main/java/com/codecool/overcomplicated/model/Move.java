package com.codecool.overcomplicated.model;

public class Move {
    private int cellNumber;
    private String sign;

    public Move(int cellNumber, String sign) {
        this.cellNumber = cellNumber;
        this.sign = sign;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
