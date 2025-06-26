package com.ncwu.service;

import com.ncwu.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserService
 * Package: com.ncwu.service
 * Description:
 *
 * @Author Lidoudou
 * @Create 2025/1/6 12:05
 * @Version 1.0
 */
@Service
public interface UserService {
    public List<User> getAll();

    public User getById(String id);

    public List<User> getByUserId(String userId);

    public int update(User user);

    public int delete(Integer id);

    public int add(User user);
}
