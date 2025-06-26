package com.ncwu.service.impl;

import com.ncwu.dao.AvaliableTimeDao;
import com.ncwu.domain.AvaliableTime;
import com.ncwu.service.PlaceSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PlaceSubscribeServiceimpl
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/9 0:20
 * @Version 1.0
 */
@Service
public class PlaceSubscribeServiceImpl implements PlaceSubscribeService {
    @Autowired
    private AvaliableTimeDao avaliableTimeDao;

    @Override
    public List<AvaliableTime> getAll() {
        return avaliableTimeDao.getAll();
    }
}
