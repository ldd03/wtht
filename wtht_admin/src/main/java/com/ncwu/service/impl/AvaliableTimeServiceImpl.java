package com.ncwu.service.impl;

import com.ncwu.dao.AvaliableTimeDao;
import com.ncwu.domain.AvaliableTime;
import com.ncwu.service.AvaliableTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliableTimeServiceImpl implements AvaliableTimeService {
    @Autowired
    private AvaliableTimeDao avaliableTimeDao;

    @Override
    public List<AvaliableTime> getAll() {
        return avaliableTimeDao.getAll();
    }
} 