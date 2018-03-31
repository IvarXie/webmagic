package com.xie.webmagic.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.xie.webmagic.model.DoubanComment;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author xie.wenbo
 * @Description TODO
 * @Creation Date : 2018-03-30 17:38
 * Email is xie.wenbo@jyall.com
 * Copyright is 金色家园网络科技有限公司
 */
public class DoubanCommentDao {

    public void insert(DoubanComment doubanComment){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try{
            cpds.setDriverClass("com.mysql.jdbc.Driver");
        }catch (PropertyVetoException e){
            e.printStackTrace();
        }
        cpds.setJdbcUrl("jdbc:mysql://140.143.248.15:3366/webmagic?useUnicode=true&characterEncoding=utf-8");
        cpds.setUser("root");
        cpds.setPassword("Xie15210868138!");

        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = cpds.getConnection();
            // 获取数据库操作对象
            stmt = connection.createStatement();
            String sql = String.format("INSERT INTO `webmagic`.`doubanComment` (`movieName`, `userName`, `comment`, `evaluateLevel`, `evaluateDate`, `approvalCount`) VALUES ('%s', '%s', '%s', %d, '%s', %d);",
                    doubanComment.getMovieName(),doubanComment.getUserName(),doubanComment.getComment(),doubanComment.getEvaluateLevel(),doubanComment.getEvaluateDate(),doubanComment.getApprovalCount());
            System.out.println("=================================================================================================="+sql);
            // 操作数据库获取结果集
            boolean result  = stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭结果集
            if(rs != null) {
                try { rs.close(); } catch (SQLException e) { }
            }
            // 关闭数据库操作对象
            if(stmt != null) {
                try { stmt.close(); } catch (SQLException e) { }
            }
            // 关闭数据库连接
            if(connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
            try {
                DataSources.destroy(cpds);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
