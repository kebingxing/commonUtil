package com.tencent.dubhe.data.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.tencent.dubhe.data.utils.DataFileReader;
import com.tencent.dubhe.data.utils.domain.DomainData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDataFileReader implements DataFileReader {

    @Override
    public DomainData readFile(String filePath) {
        ExcelDataListener listener = new ExcelDataListener();
        EasyExcel.read(filePath, listener).sheet().doRead();
        return listener.getDomainData();
    }
}
