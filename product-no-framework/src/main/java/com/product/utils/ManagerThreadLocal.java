package com.product.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 本地线程数据库事务管理, 将连接挂载到本连接上
 *
 */
public final class ManagerThreadLocal {
    private static Logger logger = LoggerFactory.getLogger(ManagerThreadLocal.class);

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    /**
     * 获取一个连接
     * @return Connection
     */
    public static Connection getConnection() {
        Connection conn = threadLocal.get(); //从当前线程中取出一个连接
        if(conn == null) {
            conn = C3P0Utils.getConnection(); //从连接池中获取一个连接
            threadLocal.set(conn);
        }
        return conn;
    }

    /**
     * 开始事务，设置手动提交
     */
    public static void startTransaction() {
        try{
            Connection conn = getConnection();//从当前线程对象中取出的连接，并开始事务
            conn.setAutoCommit(false);
        }catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 提交事务
     */
    public static void commit() {
        try{
            getConnection().commit(); //提交事务
        }catch(SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        try{
            getConnection().rollback();
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
        }
    }

    public static void close() {
        try{
            getConnection().close(); //将连接放回连接池
            threadLocal.remove(); //将当前线程对象中的conn溢出
        }catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
