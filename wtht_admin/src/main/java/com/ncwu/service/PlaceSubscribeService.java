package com.ncwu.service;

import com.ncwu.domain.AvaliableTime;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: PlaceSubscribes
 * Package: com.ncwu.service
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/9 0:18
 * @Version 1.0
 */
@Service
public interface PlaceSubscribeService {
    public List<AvaliableTime> getAll();
}
