package com.ncwu.controller;

import com.ncwu.domain.AvaliableTime;
import com.ncwu.domain.Item;
import com.ncwu.domain.Place;
import com.ncwu.service.PlaceService;
import com.ncwu.service.PlaceSubscribeService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PlaceSubscribeController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 22:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/placeSubscribes")
public class PlaceSubscribeController {
    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceSubscribeService placeSubscribeService;

    /*
    * 因为这里是需要进行预约，所以只需要返回处于“可用”状态的场地即可
    * */
    @GetMapping
    public AjaxResult getAllPlace(){
        List<Place> _places = placeService.getAll();
        List<Place> places = new ArrayList<>();
        for(Place place : _places){
            if(!place.getStatus().equals("维护中")){
                places.add(place);
            }
        }
        return new AjaxResult(places, Code.GET_OK,"获取预约场地信息成功");
    }

    /*
    * 获取点击预约之后，获取那一行的信息
    * */
    @GetMapping("byId/{id}")
    public AjaxResult getById(@PathVariable Integer id){
        return new AjaxResult(placeService.getById(id),Code.GET_OK,"获取点击预约的，那一行的信息，成功");
    }

    @GetMapping("byType/{type}")
    public AjaxResult getByType(@PathVariable String type){
        return new AjaxResult(placeService.getByType(type),Code.GET_OK,"筛选条件成功");
    }


    /*
    * 获取预约可选有效时间片段
    * */
    @GetMapping("getTimeRange")
    public AjaxResult getTimeRange(){
        List<AvaliableTime> _list = placeSubscribeService.getAll();
        List<Item> items = new ArrayList<>();
        //筛选可用时间段
        List<AvaliableTime> list = new ArrayList<>();
        for(AvaliableTime avaliableTime : _list){
            if(!avaliableTime.getStatus().equals("不可用")){
                list.add(avaliableTime);

            }
        }

        for(int i = 0;i < list.size();i++){
            Item item = new Item();
            item.setLabel(list.get(i).getTimeRange());
            item.setValue(list.get(i).getTimeId());
            items.add(item);
        }
        return new AjaxResult(items,Code.GET_OK,"获取时间段成功");

    }


}


