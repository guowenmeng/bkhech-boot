package com.bkhech.boot.configure.mvc.error;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.bkhech.boot.configure.common.code.StatusCode;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.bkhech.boot.configure.common.constants.ConfigureConstants.*;

/**
 * Created by guowm on 2017/4/6.
 */
@Controller
@RequestMapping("/")
public class HttpErrorHandler extends AbstractErrorController {

    private static final String CODE = "code";
    private static final String MSG = "message";

    private static final List<String> ERROR_NULL_CODES = Lists.newArrayList("NotBlank", "NotEmpty", "NotNull");

    private static final String REQUEST_BODY_NULL = "Required request body is missing";

    private static final String REQUEST_HEADER_NULL = "Missing request header";

    public HttpErrorHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResultResponse error(HttpServletRequest request, HttpServletResponse response) {
        return convertMessage(request, response);
    }

    @RequestMapping(value = NOT_FOUND_PATH)
    @ResponseBody
    public ResultResponse notFound(HttpServletRequest request, HttpServletResponse response) {
        return convertMessage(request, response);
    }

    private ResultResponse convertMessage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request, false);
        HttpStatus status = getStatus(request);
        Integer code = status.value();

        if (request.getAttribute(GLOBAL_ERROR_KEY) != null) {
            code = (Integer) request.getAttribute(GLOBAL_ERROR_KEY);
        }

        String message = body.get(MSG).toString();
        if (status.equals(HttpStatus.BAD_REQUEST) || status.equals(HttpStatus.NOT_FOUND)) {

            if (message.contains(REQUEST_BODY_NULL)) {
                message = REQUEST_BODY_NULL;
                code = StatusCode.PARAMETER_NULL.getCode();
                response.setStatus(HttpStatus.OK.value());
            }

            if (message.contains(REQUEST_HEADER_NULL)) {
                code = StatusCode.HEADER_NULL.getCode();
                response.setStatus(HttpStatus.OK.value());
            }

            List<ObjectError> errors = (List<ObjectError>) body.get("errors");
            if (errors != null && errors.size() > 0) {
                JSONArray array = new JSONArray();
                for (ObjectError error : errors) {
                    response.setStatus(HttpStatus.OK.value());
                    String[] codes = error.getCodes();

                    if (ERROR_NULL_CODES.contains(codes[codes.length - 1])) {
                        code = StatusCode.PARAMETER_NULL.getCode();
                    } else {
                        code = StatusCode.PARAMETER_ERROR.getCode();
                    }
                    if (error.getArguments().length > 0) {
                        DefaultMessageSourceResolvable resolvable = (DefaultMessageSourceResolvable) error.getArguments()[0];
                        JSONObject messageData = new JSONObject(16, true);
                        messageData.put("property", resolvable.getDefaultMessage());
                        messageData.put("message", error.getDefaultMessage());
                        array.add(messageData);
                    }
                }
                return ResultResponse.custom(code, message, array);
            }
        }

        return ResultResponse.custom(code, message);
    }

}
