package com.ncwu.controller;

import com.ncwu.service.AvaliableTimeService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliableTimes")
public class AvaliableTimeController {
    @Autowired
    private AvaliableTimeService avaliableTimeService;

    @GetMapping
    public AjaxResult getAll() {
        return new AjaxResult(avaliableTimeService.getAll(), Code.GET_OK, "获取所有时间段成功");
    }
} 