package com.ncwu.service;

import com.ncwu.domain.Place;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PlaceService
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/8 8:47
 * @Version 1.0
 */
@Service
public interface PlaceService {
    public List<Place> getAll();

    public Place getById(Integer id);

    public List<Place> getByType(String type);

    public int update(Place place);

    public int delete(Integer id);

    public int add(Place place);
}
