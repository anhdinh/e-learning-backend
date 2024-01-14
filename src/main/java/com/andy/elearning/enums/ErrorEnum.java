package com.andy.elearning.enums;

public enum ErrorEnum {
    USER_HAS_ALREADY_EXISTED(1000);
    private int value;

    ErrorEnum(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
