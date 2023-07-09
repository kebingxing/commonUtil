package com.tencent.dubhe.data.utils.example.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Order {

    @JSONField(name = "order_no")
    private String orderNo;

    @JSONField(name = "order_status")
    private String orderStatus;

}
