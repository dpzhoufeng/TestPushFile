package com.dawnpro.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.http.multipart.max-file-size}")
    private static String MAX_FILE_SIZE ;

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("message", "文件大小超出限制，文件最大为"+MAX_FILE_SIZE+",请检查后重新上传。");
        return "redirect:/uploadStatus";

    }



}
