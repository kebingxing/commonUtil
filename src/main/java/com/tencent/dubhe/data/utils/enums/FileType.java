package com.tencent.dubhe.data.utils.enums;

public enum FileType {
    EXCEL("EXCEL", "Excel文件"),
    CSV("CSV", "CSV文件");

    private final String code;
    private final String msg;

    FileType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}