package com.tencent.dubhe.data.utils;

import com.tencent.dubhe.data.utils.domain.DomainData;

public interface DataFileWriter {

    void writeFile(String filePath, DomainData domainData);

}
