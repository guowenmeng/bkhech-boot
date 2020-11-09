package com.bkhech.boot.sample.swagger.controller;

import com.bkhech.boot.configure.common.dto.ResultResponse;
import com.bkhech.boot.sample.swagger.dto.ParametersValidationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Description: ParametersValidationController
 * 防参数注入校验示例
 * @author guowm 2018/9/25
 */
@RestController
public class ParametersValidationController {

    @PostMapping(value = "/param/validate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultResponse queryColligateData(@Valid @RequestBody ParametersValidationDto dto) {
        return ResultResponse.success();
    }

}
