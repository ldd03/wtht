package com.ncwu.controller;

import com.ncwu.domain.History;
import com.ncwu.service.HistoryService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HistoryController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 22:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/historys")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(historyService.getAll(), Code.GET_OK,"获取历史预约记录成功");
    }

    @GetMapping("/filter")
    public AjaxResult filterHistory(
        @RequestParam(required = false) String role,
        @RequestParam(required = false) String placeName,
        @RequestParam(required = false) String userName,
        @RequestParam(required = false) String date,
        @RequestParam(required = false) String timeRange
    ) {
        return new AjaxResult(historyService.filterHistory(role, placeName, userName, date, timeRange), Code.GET_OK, "筛选历史预约记录成功");
    }

    @PostMapping("/add")
    public AjaxResult addHistory(@RequestBody History history){
        try {
            history.setStatus("已预约");
            boolean result = historyService.addHistory(history);
            if(result){
                return new AjaxResult(null, Code.POST_OK, "预约历史添加成功");
            } else {
                return new AjaxResult(null, Code.POST_ERR, "该时间段已被预约，请选择其他时间段");
            }
        } catch (Exception e) {
            return new AjaxResult(null, Code.POST_ERR, "添加失败：" + e.getMessage());
        }
    }
}
