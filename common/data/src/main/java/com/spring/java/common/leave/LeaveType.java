package com.spring.java.common.leave;

public enum LeaveType {
    SICK("SICK", "Sick Leave"),
    PAID("PAID", "Paid Leave"),
    LOSS_OF_PAY("LOP", "Loss Of Pay");

    private final String code;
    private final String label;

    LeaveType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }}
