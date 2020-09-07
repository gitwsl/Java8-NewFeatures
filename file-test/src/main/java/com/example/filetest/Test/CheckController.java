package com.example.filetest.Test;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liaobaohuang
 * @date 2020/9/2
 */
@RestController
@RequestMapping("/check")
@CrossOrigin
public class CheckController {
    @Resource
    private CheckService checkService;

    @PostMapping("/verify/excel")
    public ResultDosmVO verifyExcel(@RequestBody CheckDTO checkDto) {
        Boolean result = checkService.checkAndUploadExcel(checkDto);
        if (result) {
            return new ResultDosmVO(100000, "success", null);
        } else {
            return ResultDosmVO.getException();
        }
    }
}