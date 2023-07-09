package com.tencent.dubhe.data.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.tencent.dubhe.data.utils.DataFileWriter;
import com.tencent.dubhe.data.utils.domain.DomainData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelDataFileWriter implements DataFileWriter {

    @Override
    public void writeFile(String filePath, DomainData domainData) {
        EasyExcel.write(filePath)
                .head(head(domainData))
                // 设置自定义样式
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("sheet1")
                .doWrite(data(domainData));
    }

    private List<List<String>> head(DomainData domainData) {
        List<String> headList = domainData.getHeadList();
        List<List<String>> list = new ArrayList<>();

        for (String headStr : headList) {
            List<String> head = new ArrayList<>();
            head.add(headStr);
            list.add(head);
        }
        return list;
    }

    private List<List<Object>> data(DomainData domainData) {
        List<LinkedHashMap<String, Object>> dataList = domainData.getDataList();
        List<String> headList = domainData.getHeadList();

        List<List<Object>> list = new ArrayList<>();

        for (LinkedHashMap<String, Object> map : dataList) {
            List<Object> data = new ArrayList<>();
            // 遍历表头
            for (String headStr : headList) {
                data.add(map.getOrDefault(headStr, ""));
            }
            list.add(data);
        }
        return list;
    }

}
