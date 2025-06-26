package com.ncwu.service.impl;

import com.ncwu.dao.NoticeDao;
import com.ncwu.domain.Notice;
import com.ncwu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: NoticeServiceImpl
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/7 8:58
 * @Version 1.0
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Override
    public List<Notice> getAll() {
        return noticeDao.getAll();
    }

    @Override
    public Notice getById(Integer id) {
        return noticeDao.getById(id);
    }

    @Override
    public List<Notice> getByStatus(String status) {
        return noticeDao.getByStatus(status);
    }

    @Override
    public int update(Notice notice) {
        return noticeDao.update(notice);
    }

    @Override
    public int delete(Integer id){
        return noticeDao.delete(id);
    }

    @Override
    public int add(Notice notice) {
        return noticeDao.add(notice);
    }
}
