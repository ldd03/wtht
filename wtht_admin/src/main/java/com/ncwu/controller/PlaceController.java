package com.ncwu.controller;

import com.ncwu.domain.Place;
import com.ncwu.service.PlaceService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: PlaceController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 9:00
 * @Version 1.0
 */
@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(placeService.getAll(), Code.GET_OK,"获取场地信息成功");
    }

    @GetMapping("byId/{id}")
    public AjaxResult getById(@PathVariable Integer id){
        return new AjaxResult(placeService.getById(id),Code.GET_OK,"场地信息修改成功");
    }

    @GetMapping("byType/{type}")
    public AjaxResult getByType(@PathVariable String type){
        return new AjaxResult(placeService.getByType(type),Code.GET_OK,"筛选条件成功");
    }

    //更新数据
    @PutMapping
    public AjaxResult update(@RequestBody Place place){
        String msg = "更新场地信息失败";
        if(placeService.update(place) > 0){
            msg = "更新场地信息成功";
        }
        return new AjaxResult(null,Code.PUT_OK,msg);
    }

    //删除指定id
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Integer id){
        String msg = "删除指定场地消息失败";
        if(placeService.delete(id) > 0){
            msg = "删除指定场地消息成功";
        }
        return new AjaxResult(null,Code.DELETE_OK,msg);
    }

    //新增
    @PostMapping
    public AjaxResult add(@RequestBody Place place) {
        String msg = "新增场地失败";
        if (placeService.add(place) > 0) {
            msg = "新增场地成功";
        }
        return new AjaxResult(null, Code.POST_OK, msg);
    }

    //新增
   /* @PostMapping
    public AjaxResult addSubscribeInfo(@RequestBody History history){

    }*/
}
