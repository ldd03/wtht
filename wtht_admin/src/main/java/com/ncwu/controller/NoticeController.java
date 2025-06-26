package com.ncwu.controller;

import com.ncwu.domain.Notice;
import com.ncwu.service.NoticeService;
import com.ncwu.util.AjaxResult;
import com.ncwu.util.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: NoticeController
 * Package: com.ncwu.controller
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/7 8:59
 * @Version 1.0
 */

@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public AjaxResult getAll(){
        return new AjaxResult(noticeService.getAll(), Code.GET_OK,"获取公告信息成功");
    }

    @GetMapping("byId/{id}")
    public AjaxResult getById(@PathVariable Integer id){
        return new AjaxResult(noticeService.getById(id),Code.GET_OK,"公告信息修改成功");
    }

    @GetMapping("byStatus/{status}")
    public AjaxResult getByStatus(@PathVariable String status){
        return new AjaxResult(noticeService.getByStatus(status),Code.GET_OK,"筛选条件成功");
    }

    //更新数据
    @PutMapping
    public AjaxResult update(@RequestBody Notice notice){
        String msg = "更新公告信息失败";
        if(noticeService.update(notice) > 0){
            msg = "更新公告信息成功";
        }
        return new AjaxResult(null,Code.PUT_OK,msg);
    }

    //删除指定id
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Integer id){
        String msg = "删除指定公告消息失败";
        if(noticeService.delete(id) > 0){
            msg = "删除指定公告消息成功";
        }
        return new AjaxResult(null,Code.DELETE_OK,msg);
    }

    //新增
    @PostMapping
    public AjaxResult add(@RequestBody Notice notice){
        //这里对日期格式进行调整
        String resOutTime = "";
        //2025-01-13T16:00:00.000Z

        String outTime = notice.getOutTime();
        String _resOutTime = outTime.replace("T"," ");
        resOutTime = _resOutTime.substring(0,_resOutTime.length()-5);
        //将修改后的数据存放进去
        notice.setOutTime(resOutTime);

        String msg = "新增公告信息失败";
        if(noticeService.add(notice) > 0){
            msg = "新增公告信息成功";
        }
        return new AjaxResult(null,Code.POST_OK,msg);
    }
}
