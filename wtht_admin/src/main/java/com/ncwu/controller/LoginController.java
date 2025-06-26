package com.ncwu.controller;

import com.ncwu.domain.User;
import com.ncwu.service.UserService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: LoginController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 11:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/logins")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(userService.getAll(), Code.GET_OK,"查询全部用户信息成功");
    }

    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable String id){
        return new AjaxResult(userService.getById(id),Code.GET_OK,"loginSuccess");
    }


    @PostMapping
    public AjaxResult getByIdVerify(@RequestBody User user){
        User _user = userService.getById(user.getUserId());
        String msg = "error";
        Object data = null;
        if(_user != null && user.getPassword().equals(_user.getPassword())){
            msg = "success";
            data = _user; // 登录成功，返回用户信息
        }
        return new AjaxResult(data, Code.VERIFY_OK, msg);
    }
}
