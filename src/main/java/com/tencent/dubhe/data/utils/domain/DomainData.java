package com.tencent.dubhe.data.utils.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import lombok.Data;

@Data
public class DomainData {

    // 表头
    private List<String> headList;

    // 数据
    private List<LinkedHashMap<String, Object>> dataList;

    public DomainData() {
        headList = new ArrayList<>();
        dataList = new ArrayList<>();
    }

    /**
     * 表头数量
     *
     * @return
     */
    public int headSize() {
        return Objects.isNull(headList) ? 0 : headList.size();
    }

    /**
     * 数据量
     *
     * @return
     */
    public int dataSize() {
        return Objects.isNull(dataList) ? 0 : dataList.size();
    }

    public <T> List<T> convertMapToObject(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (LinkedHashMap<String, Object> map : dataList) {
            list.add(convertMapToObject(map, clazz));
        }
        return list;
    }

    public static <T> DomainData toDomainData(List<T> objList) {
        DomainData domainData = new DomainData();
        List<String> newHeadList = domainData.getHeadList();
        // 设置表头
        LinkedHashMap<String, Object> headMap = convertObjectToMap(objList.get(0));
        for (Entry<String, Object> entry : headMap.entrySet()) {
            newHeadList.add(entry.getKey());
        }
        domainData.setHeadList(newHeadList);

        // 设置数据
        List<LinkedHashMap<String, Object>> newDataList = domainData.getDataList();
        for (Object obj : objList) {
            newDataList.add(convertObjectToMap(obj));
        }
        domainData.setDataList(newDataList);
        return domainData;
    }

    /**
     * 对象转map
     *
     * @param obj
     * @return
     */
    public static LinkedHashMap<String, Object> convertObjectToMap(Object obj) {
        String jsonString = JSON.toJSONString(obj);
        return JSON.parseObject(jsonString, new TypeReference<LinkedHashMap<String, Object>>() {
        }, Feature.OrderedField);
    }


    /**
     * map转对象
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertMapToObject(LinkedHashMap<String, Object> map, Class<T> clazz) {
        String jsonString = JSON.toJSONString(map);
        return JSON.parseObject(jsonString, clazz);
    }

}
