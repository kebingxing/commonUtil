package com.tencent.dubhe.data.utils.csv;

import com.tencent.dubhe.data.utils.DataFileWriter;
import com.tencent.dubhe.data.utils.domain.DomainData;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

@Slf4j
public class CsvDataFileWriter implements DataFileWriter {

    @Override
    public void writeFile(String filePath, DomainData domainData) {
        List<String> headList = domainData.getHeadList();
        try (FileWriter fileWriter = new FileWriter(filePath);
                CSVPrinter csvPrinter = new CSVPrinter(fileWriter,
                        CSVFormat.DEFAULT.withHeader(headList.toArray(new String[0])))) {
            // 写入数据
            List<LinkedHashMap<String, Object>> dataList = domainData.getDataList();
            for (LinkedHashMap<String, Object> map : dataList) {
                List<Object> data = new ArrayList<>();
                // 遍历表头
                for (String headStr : headList) {
                    data.add(map.getOrDefault(headStr, ""));
                }
                csvPrinter.printRecord(data.toArray(new Object[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
