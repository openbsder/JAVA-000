package com.example.jdbcdemo;

import com.example.jdbcdemo.dao.UserDao;
import com.example.jdbcdemo.datasource.HikariDataSourceFactory;
import com.example.jdbcdemo.datasource.JdbcDataSource;
import com.example.jdbcdemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class JdbcApp {
    public static void main(String[] args) throws Exception {
        // use jdbc
        System.out.println("Use JDBC...");
        UserDao userDao = new UserDao(new JdbcDataSource());
        run(userDao);

        // use Hikari
        System.out.println("Use JDBC with Hikari...");
        UserDao userDaoWithPool = new UserDao(new HikariDataSourceFactory().create());
        run(userDaoWithPool);
    }

    private static void run(UserDao userDao) throws Exception {
        // Truncate table t_user
        userDao.truncate();

        // insert user {"id":1, "name":"test user"}
        User user = new User();
        user.setId(1L);
        user.setName("test user");
        System.out.println("insert user: " + userDao.insert(user));

        // Get user by user id
        System.out.println("get user(1): " + userDao.byId(1L));

        // Update user set name to "new name"
        User updateUser = new User();
        updateUser.setId(1L);
        updateUser.setName("new name");
        System.out.println("update user: " + userDao.update(updateUser));

        // delete user(1)
        System.out.println("delete user(1)");
        userDao.delete(1L);
        System.out.println("get user(1): " + userDao.byId(1L));

        // batch insert users
        List<User> users = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            User user1 = new User();
            user1.setId((long) (i + 1));
            user1.setName("batch test" + (long) (i + 1));

            users.add(user1);
        }

        int count = userDao.batchInsert(users);
        System.out.println("batch insert " + count + " users.");
    }
}
