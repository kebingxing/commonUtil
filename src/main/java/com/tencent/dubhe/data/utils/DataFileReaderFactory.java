package com.tencent.dubhe.data.utils;

import com.tencent.dubhe.data.utils.csv.CsvDataFileReader;
import com.tencent.dubhe.data.utils.enums.FileType;
import com.tencent.dubhe.data.utils.excel.ExcelDataFileReader;

public class DataFileReaderFactory {
    public static DataFileReader getFileReader(FileType fileType) {
        if (fileType == FileType.EXCEL) {
            return new ExcelDataFileReader();
        } else if (fileType == FileType.CSV) {
            return new CsvDataFileReader();
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileType.getCode() + " - " + fileType.getMsg());
        }
    }
}