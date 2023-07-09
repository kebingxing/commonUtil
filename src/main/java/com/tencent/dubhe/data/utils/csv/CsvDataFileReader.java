package com.tencent.dubhe.data.utils.csv;

import com.tencent.dubhe.data.utils.DataFileReader;
import com.tencent.dubhe.data.utils.domain.DomainData;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class CsvDataFileReader implements DataFileReader {

    // 元数据缓存
    private DomainData domainData;

    public CsvDataFileReader() {
        domainData = new DomainData();
    }

    @Override
    public DomainData readFile(String filePath) {
        getFileHeader(filePath);
        try (Reader reader = new FileReader(filePath);
                CSVParser records = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : records) {
                Map<String, String> map = record.toMap();
                List<LinkedHashMap<String, Object>> dataList = domainData.getDataList();

                LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>(map);
                dataList.add(dataMap);
                domainData.setDataList(dataList);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return domainData;
    }


    private void getFileHeader(String filePath) {
        // 获取文件头
        try (Reader reader = new FileReader(filePath);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            // 获取CSV文件头
            Map<String, Integer> headerMap = new LinkedHashMap<>(csvParser.getHeaderMap());
            List<String> headList = domainData.getHeadList();
            for (Entry<String, Integer> entry : headerMap.entrySet()) {
                headList.add(entry.getKey());
            }
            domainData.setHeadList(headList);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
