package com.ncwu.service;

import com.ncwu.domain.History;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: HistoryService
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 21:41
 * @Version 1.0
 */
@Service
public interface HistoryService {

    /*
    * 获取历史预约信息
    * */
    public List<History> getAll();

    // 多条件筛选
    List<History> filterHistory(String role, String placeName, String userName, String date, String timeRange);

    // 新增预约历史
    boolean addHistory(History history);
}
