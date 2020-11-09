package com.bkhech.boot.sample.swagger.controller;

import com.bkhech.boot.sample.swagger.dto.EventLogDto;
import com.bkhech.boot.sample.swagger.dto.PageDto;
import com.bkhech.boot.sample.swagger.model.event.EventLog;
import com.bkhech.boot.sample.swagger.model.user.UserInfo;
import com.bkhech.boot.sample.swagger.service.event.EventLogService;
import com.bkhech.boot.sample.swagger.service.user.UserInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.bkhech.boot.configure.common.builder.table.TableBuilder;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import com.bkhech.boot.configure.common.builder.table.response.TableResponse;
import com.bkhech.boot.sample.swagger.constants.AppStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * SampleController
 *
 * Created by guowm on 18-5-3.
 */
@RestController
public class SampleController {

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @apiDefine global_api
     * @apiSuccess code 0代表成功
     * @apiError (错误码) -1 failed
     * @apiError (错误码) -2 unknown
     */

    /**
     * @api {post} /page 事件日志分页示例
     * @apiName page
     * @apiGroup sample
     * @apiVersion 1.0.0
     *
     * @apiParam {EventLog} log 事件日志查询条件 Y
     * @apiParam {Page} page 分页对象 Y
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "code": 0,
     *       "message": "successful",
     *       "data": [{...}, {...}],
     *       "page": {
     *         "page_size": 10,
     *         "page_num": 1,
     *         "total_page": 5943,
     *         "total_rows": 59429
     *       },
     *       "thead": ["游戏ID", "广告ID",...]
     *     }
     *
     * @apiErrorExample Error-Response:
     *     {
     *       "code": -1,
     *       "message": "failed"
     *     }
     *
     * @apiUse global_api
     */
    @RequestMapping("/page")
    public ResultResponse<TableResponse> page(@Valid @RequestBody EventLogDto eventLogDto) {
        Map map = Maps.newHashMap();
        map.put("ad_id", "广告ID");
        List<Map<String, String>> thead =  Lists.newArrayList(map);
        return ResultResponse.custom(AppStatusCode.OK, () ->
             TableBuilder
                    .newBuilder()
                    .thead(thead)
                    .body(eventLogService.selectPageList(eventLogDto, eventLogDto.getPage()))
                    .build()
        );

    }

    @RequestMapping("/nonpage")
    public ResultResponse<TableResponse> page(EventLog log) {
        Map map = Maps.newHashMap();
        map.put("ad_id", "广告ID");
        List<Map<String, String>> thead =  Lists.newArrayList(map);
        return ResultResponse.custom(AppStatusCode.OK, () -> {
            return TableBuilder
                        .newBuilder()
                        .thead(thead)
                        .body(eventLogService.select(log))
                        .build();
        });
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello, spring mvc!";
    }

    @RequestMapping("/exception")
    public ResultResponse exception() {
        throw new RuntimeException("this is a exception");
    }

    @RequestMapping("/param")
    public ResultResponse param(@RequestBody PageDto pageDto) {
        return ResultResponse
                .custom(AppStatusCode.PAGE_NULL);
    }

    @RequestMapping(value = "/error1", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/error1")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            System.out.print(s);
            System.out.println(": " + request.getHeader(s));
        }
        return new ModelAndView("a");
    }


    @RequestMapping("/testUserInfo")
    public ResultResponse<List<UserInfo>> testUserInfo(@Valid UserInfo userInfo) {

        List<UserInfo> select = userInfoService.select(new UserInfo());
        System.out.println("select ============== " + select);
        return ResultResponse.success(select);

    }




}
