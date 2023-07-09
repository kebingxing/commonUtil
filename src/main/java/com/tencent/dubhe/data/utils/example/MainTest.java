package com.tencent.dubhe.data.utils.example;


import com.alibaba.fastjson.JSONObject;
import com.tencent.dubhe.data.utils.DataFileReader;
import com.tencent.dubhe.data.utils.DataFileReaderFactory;
import com.tencent.dubhe.data.utils.DataFileWriter;
import com.tencent.dubhe.data.utils.DataFileWriterFactory;
import com.tencent.dubhe.data.utils.domain.DomainData;
import com.tencent.dubhe.data.utils.enums.FileType;
import com.tencent.dubhe.data.utils.example.entity.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MainTest {

    /**
     * 读取Excel,CSV
     */
    @Test
    public void testRead() {
        String excelFilePath = "/Users/workspace/test/data/excel1.xlsx";
        String csvFilePath = "/Users/workspace/test/data/csv1.csv";

        DataFileReader excelReader = DataFileReaderFactory.getFileReader(FileType.EXCEL);
        // 读取Excel
        DomainData excelData = excelReader.readFile(excelFilePath);
        log.info("excelData:{}", JSONObject.toJSONString(excelData.getDataList().get(0)));
        log.info("excel head size:{}", excelData.headSize());
        log.info("excel data size:{}", excelData.dataSize());

        DataFileReader csvReader = DataFileReaderFactory.getFileReader(FileType.CSV);
        // 读取CSV
        DomainData csvData = csvReader.readFile(csvFilePath);
        log.info("csvData:{}", JSONObject.toJSONString(csvData.getDataList().get(0)));
        log.info("csv head size:{}", csvData.headSize());
        log.info("csv data size:{}", csvData.dataSize());

        // 类型转换
        List<Order> orders = csvData.convertMapToObject(Order.class);
        log.info("orders:{}", JSONObject.toJSONString(orders));
    }

    /**
     * 写入Excel,CSV
     */
    @Test
    public void testWrite() {
        String excelFilePath = "/Users/workspace/test/data/excel2.xlsx";
        String csvFilePath = "/Users/workspace/test/data/csv2.csv";

        DataFileWriter excelDataFileWriter = DataFileWriterFactory.getFileWriter(FileType.EXCEL);
        DataFileWriter csvDataFileWriter = DataFileWriterFactory.getFileWriter(FileType.CSV);

        List<Order> orderList = new ArrayList<>();

        Order order1 = new Order();
        order1.setOrderNo("order1");
        order1.setOrderStatus("CREATED");
        orderList.add(order1);

        Order order2 = new Order();
        order2.setOrderNo("order2");
        order2.setOrderStatus("PAYED");
        orderList.add(order2);

        // 转换为元数据
        DomainData domainData = DomainData.toDomainData(orderList);

        // 写入Excel
        excelDataFileWriter.writeFile(excelFilePath, domainData);
        log.info("excel write success.");
        // 写入CSV
        csvDataFileWriter.writeFile(csvFilePath, domainData);
        log.info("csv write success.");
    }

}
