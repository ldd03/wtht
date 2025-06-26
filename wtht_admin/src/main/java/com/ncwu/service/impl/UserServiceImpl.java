package com.ncwu.service.impl;

import com.ncwu.dao.UserDao;
import com.ncwu.domain.User;
import com.ncwu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserServiceimpl
 * Package: com.ncwu.service.impl
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 12:08
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(String id) {
        return userDao.getById(id);
    }
    
    @Override
    public List<User> getByUserId(String userId) {
        return userDao.getByUserId(userId);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(Integer id){
        return userDao.delete(id);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }
}
