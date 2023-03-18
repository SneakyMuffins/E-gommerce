package com.egommerce.demo.dao.User;

import com.egommerce.demo.model.User;

public interface UserDao {
    int insertUser(User user);

    User selectUserByEmail(String email);
}
