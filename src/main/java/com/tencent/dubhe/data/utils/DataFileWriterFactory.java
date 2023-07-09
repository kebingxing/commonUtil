package com.tencent.dubhe.data.utils;

import com.tencent.dubhe.data.utils.csv.CsvDataFileWriter;
import com.tencent.dubhe.data.utils.enums.FileType;
import com.tencent.dubhe.data.utils.excel.ExcelDataFileWriter;

public class DataFileWriterFactory {

    public static DataFileWriter getFileWriter(FileType fileType) {
        if (fileType == FileType.EXCEL) {
            return new ExcelDataFileWriter();
        } else if (fileType == FileType.CSV) {
            return new CsvDataFileWriter();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported file type: " + fileType.getCode() + " - " + fileType.getMsg());
        }
    }

}
