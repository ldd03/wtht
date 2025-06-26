package com.ncwu.controller;

import com.ncwu.domain.User;
import com.ncwu.service.UserService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 12:12
 * @Version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(userService.getAll(), Code.GET_OK,"获取全部用户信息成功");
    }

    @GetMapping("byId/{id}")
    public AjaxResult getById(@PathVariable String id){
        return new AjaxResult(userService.getById(id),Code.GET_OK,"用指定户信息修改成功");
    }

    @GetMapping("byUserId/{userId}")
    public AjaxResult getByUserId(@PathVariable String userId){
        userId = "%"+userId+"%";
        return new AjaxResult(userService.getByUserId(userId),Code.GET_OK,"筛选条件成功");
    }

    //更新数据
    @PutMapping
    public AjaxResult update(@RequestBody User user){
        String msg = "更新用户信息失败";
        if(userService.update(user) > 0){
            msg = "更新用户信息成功";
        }
        return new AjaxResult(null,Code.PUT_OK,msg);
    }

    //删除指定id
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Integer id){
        String msg = "删除指定用户消息失败";
        if(userService.delete(id) > 0){
            msg = "删除指定用户消息成功";
        }
        return new AjaxResult(null,Code.DELETE_OK,msg);
    }

    //新增
    @PostMapping
    public AjaxResult add(@RequestBody User user){
        String msg = "新增用户信息失败";
        if(userService.add(user) > 0){
            msg = "新增用户信息成功";
        }
        return new AjaxResult(null,Code.POST_OK,msg);
    }


}
