package com.example.filetest.Test;

/**
 * @author liaobaohuang
 * @date 2020/9/2
 */
public interface CheckService {
    /**
     * check and upload excel file
     * @param checkDto checkExcelDto
     * @return result
     */
    Boolean checkAndUploadExcel(CheckDTO checkDto);
}
