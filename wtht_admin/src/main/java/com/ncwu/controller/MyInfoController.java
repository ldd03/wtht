package com.ncwu.controller;

import com.ncwu.service.UserService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MyInfoController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 15:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/myInfo")
public class MyInfoController {
    @Autowired
    private UserService userService;

    @GetMapping({"/{id}"})
    public AjaxResult getMyInfo(@PathVariable String id){
        return new AjaxResult(userService.getById(id), Code.GET_OK,"获取个人信息成功");
    }

}
