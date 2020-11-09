package com.bkhech.boot.sample.swagger.controller;

import com.bkhech.boot.sample.swagger.config.swagger.ApiVersionConst;
import com.bkhech.boot.sample.swagger.dto.EventLogDto;
import com.bkhech.boot.sample.swagger.model.user.UserInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.bkhech.boot.configure.common.builder.table.TableBuilder;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import com.bkhech.boot.configure.common.builder.table.response.TableResponse;
import com.bkhech.boot.sample.swagger.constants.AppStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author guowm
 * @date 2020/11/9
 * @description
 */

@Api(value = "swagger测试")
@RestController
public class SampleController {

    @ApiOperation("page查询")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResultResponse<TableResponse> page(@RequestBody EventLogDto eventLogDto) {
        Map map = Maps.newHashMap();
        map.put("ad_id", "广告ID");
        List<Map<String, String>> thead =  Lists.newArrayList(map);
        return ResultResponse.custom(AppStatusCode.OK, () ->
             TableBuilder
                    .newBuilder()
                    .thead(thead)
                    .body(Collections.emptyList())
                    .build()
        );

    }

    @ApiOperation("welcome api")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello, spring mvc!";
    }


    @ApiOperation("error api")
    @RequestMapping(value = "/error1", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse error(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            System.out.print(s);
            System.out.println(": " + request.getHeader(s));
        }
        return ResultResponse.success();
    }

    @ApiOperation(value = "userinfo api", tags = ApiVersionConst.DA_APP_100)
    @RequestMapping(value = "/testUserInfo", method = RequestMethod.GET)
    public ResultResponse<List<UserInfo>> testUserInfo(UserInfo userInfo) {

        List<UserInfo> select = Collections.emptyList();
        return ResultResponse.success(select);

    }




}
