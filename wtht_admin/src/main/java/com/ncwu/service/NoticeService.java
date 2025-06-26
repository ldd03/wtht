package com.ncwu.service;

import com.ncwu.domain.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: NoticeService
 * Package: com.ncwu.service
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/7 8:55
 * @Version 1.0
 */
@Service
public interface NoticeService {
    public List<Notice> getAll();

    public Notice getById(Integer id);

    public List<Notice> getByStatus(String status);

    public int update(Notice notice);

    public int delete(Integer id);

    public int add(Notice notice);
}
