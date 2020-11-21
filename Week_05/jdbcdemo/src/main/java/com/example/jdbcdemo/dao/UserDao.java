package com.example.jdbcdemo.dao;

import com.example.jdbcdemo.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

public class UserDao {
    private static final int MAX_BATCH_SIZE = 500;
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void truncate() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            String sql = "truncate table t_user";
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public User insert(User user) throws Exception {
        if (Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new Exception("update user failed, user/user id is null");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            // close auto commit
            conn.setAutoCommit(false);

            String sql = "insert t_user (id, name) values (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getName());

            int count = pstmt.executeUpdate();
            if (count != 1) {
                throw new Exception("Insert user failed, user id: " + user.getId());
            }
            // submit commit
            conn.commit();

            return byId(user.getId());
        } catch (Exception e) {
            // rollback commit
            if (Objects.nonNull(conn)) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new Exception("insert user failed!");
    }

    public User byId(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            String sql = "Select id, name from t_user where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            if (Objects.isNull(rs) || !rs.next()) {
                return null;
            }

            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public User update(User user) throws Exception {
        if (Objects.isNull(user) || Objects.isNull(user.getId())) {
            throw new Exception("update user failed, user/user id is null");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            String sql = "update t_user set name = ? where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setLong(2, user.getId());

            int count = pstmt.executeUpdate();
            if (count != 1) {
                throw new Exception("Update user failed, user id: " + user.getId());
            }

            return byId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        throw new Exception("update user failed!");
    }

    public void delete(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new Exception("delete user failed, user id is null");
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            String sql = "delete from t_user where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int batchInsert(List<User> users) throws Exception {
        if (Objects.isNull(users) || users.isEmpty()) {
            return 0;
        }

        if (users.size() > MAX_BATCH_SIZE) {
            throw new Exception("Batch insert failed, elements size is exceed " + MAX_BATCH_SIZE);
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dataSource.getConnection();
            // close auto commit
            conn.setAutoCommit(false);

            String sql = "insert t_user (id, name) values (?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (User user : users) {
                pstmt.setLong(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.addBatch();
            }

            int[] count = pstmt.executeBatch();
            // submit commit
            conn.commit();

            int successCount = 0;
            for (int i : count) {
                if (i == 1 || i == -2) {
                    successCount++;
                }
            }

            return successCount;
        } catch (Exception e) {
            // rollback commit
            if (Objects.nonNull(conn)) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
