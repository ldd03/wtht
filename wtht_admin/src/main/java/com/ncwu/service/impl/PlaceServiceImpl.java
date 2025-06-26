package com.ncwu.service.impl;

import com.ncwu.dao.PlaceDao;
import com.ncwu.domain.Place;
import com.ncwu.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PlaceServiceImpl
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 8:48
 * @Version 1.0
 */
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;

    @Override
    public List<Place> getAll() {
        return placeDao.getAll();
    }

    @Override
    public Place getById(Integer id) {
        return placeDao.getById(id);
    }

    @Override
    public List<Place> getByType(String status) {
        return placeDao.getByName(status);
    }

    @Override
    public int update(Place place) {
        return placeDao.update(place);
    }

    @Override
    public int delete(Integer id){
        return placeDao.delete(id);
    }

    @Override
    public int add(Place place) {
        return placeDao.add(place);
    }
}
