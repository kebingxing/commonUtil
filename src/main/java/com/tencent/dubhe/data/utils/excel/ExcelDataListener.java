package com.tencent.dubhe.data.utils.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tencent.dubhe.data.utils.domain.DomainData;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDataListener extends AnalysisEventListener<Map<Integer, String>> {

    // 元数据缓存
    private DomainData domainData;

    public ExcelDataListener() {
        domainData = new DomainData();
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        List<String> list = sortList(data);
        // 拼接表头
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        List<String> headList = domainData.getHeadList();
        for (int i = 0; i < headList.size(); i++) {
            map.put(headList.get(i), list.get(i));
        }
        List<LinkedHashMap<String, Object>> dataList = domainData.getDataList();
        dataList.add(map);
        domainData.setDataList(dataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 所有都处理完成
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        domainData.setHeadList(sortList(headMap));
    }

    /**
     * 顺序处理
     */
    private List<String> sortList(Map<Integer, String> data) {
        // 排序
        Map<Integer, String> treeMap = new TreeMap<>(data);
        List<String> list = new ArrayList<>();
        for (Entry<Integer, String> entry : treeMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public DomainData getDomainData() {
        return domainData;
    }

}
