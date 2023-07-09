package com.tencent.dubhe.data.utils;

import com.tencent.dubhe.data.utils.domain.DomainData;

public interface DataFileReader {
    DomainData readFile(String filePath);
}