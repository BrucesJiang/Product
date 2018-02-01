package com.product.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class C3P0UtilsTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void getDataSource() throws Exception {
        // c3p0的DataSource实例
        DataSource dataSource = C3P0Utils.getDataSource();
        String sql = "SELECT * FROM user";
        try {
            // 获取连接
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            result.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

}