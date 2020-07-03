package com.example.hotel.util;

import com.example.hotel.vo.ResponseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private ResponseVO exceptionHandler(HttpServletRequest req,Exception e){
        return ResponseVO.buildFailure(e.getMessage());
    }

}
