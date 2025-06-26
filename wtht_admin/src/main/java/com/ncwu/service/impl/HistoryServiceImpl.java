package com.ncwu.service.impl;

import com.ncwu.dao.AvaliableTimeDao;
import com.ncwu.dao.HistoryDao;
import com.ncwu.dao.PlaceDao;
import com.ncwu.dao.UserDao;
import com.ncwu.domain.History;
import com.ncwu.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: HistoryServiceImpl
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 21:48
 * @Version 1.0
 */
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryDao historyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PlaceDao placeDao;
    @Autowired
    private AvaliableTimeDao avaliableTimeDao;

    @Override
    public List<History> getAll() {
        List<History> _list = historyDao.getAll();
        List<History> list = new ArrayList<>();
        for(History history : _list){
            history.setUser(userDao.getById(history.getUser().getUserId()));
            history.setPlace(placeDao.getById(history.getPlace().getPlaceId()));
            history.setAvaliableTime(avaliableTimeDao.getById(history.getAvaliableTime().getTimeId()));
            list.add(history);
        }
        return list;
    }

    @Override
    public List<History> filterHistory(String role, String placeName, String userName, String date, String timeRange) {
        return historyDao.filterHistory(role, placeName, userName, date, timeRange);
    }

    @Override
    public boolean addHistory(History history) {
        try {
            // 冲突校验：同一时间段同一场地不能重复预约
            List<History> exist = historyDao.getByPlaceAndTime(
                history.getPlace().getPlaceId(),
                history.getAvaliableTime().getTimeId()
            );
            if (exist != null && !exist.isEmpty()) {
                return false;
            }
            int result = historyDao.addHistory(history);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
