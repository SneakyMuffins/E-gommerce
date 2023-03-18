package com.egommerce.demo.dao.User;

import com.egommerce.demo.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO users (user_id, email, username, password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getId().toString(), user.getEmail(), user.getUsername(), user.getPassword());
    }

    @Override
    public User selectUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> users = jdbcTemplate.query(sql, rowMapper, email);

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }
}
