package com.ej.example.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class ConnectionManager<T> {

    private static BasicDataSource dataSource;

    private Connection conn;
    private PreparedStatement pstmt;
    protected ResultSet rs;


    private BasicDataSource getDataSource() {
        Properties properties = readProperties();
        String url = properties.getProperty("url");
        String driverClassName = properties.getProperty("driverClassName");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);
        ds.setUsername(userName);
        ds.setPassword(password);

        dataSource = ds;
        return dataSource;

    }

    
    protected Properties readProperties() {
        Properties properties = new Properties();
        try {
        	
        	
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("../db.properties"));
            
            System.out.println(Thread.currentThread().getContextClassLoader().getResource("../db.properties").getPath());
           

        } catch (IOException e) {
            e.printStackTrace();

        }

        return properties;
    }

    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    public int executeUpdate(String query, Object... params) {

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);

            for (int i = 1; i < params.length + 1; i++) {
                pstmt.setObject(i, params[i - 1]);
            }

            int affectRows = pstmt.executeUpdate();
            int seq = 0;
            if (affectRows > 0) {       // update  �꽦怨�
                rs = pstmt.getGeneratedKeys();

                while (rs.next()) {
                    seq = (int) rs.getLong(1);
                }
                if (seq == 0) { // pk 諛섑솚 x
                    return affectRows;
                }

            }

            int count = 0;
            if (params.length == 0) {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }
                return count;
            }

            return seq;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return 0;
    }


    protected List executeQuery(Class<T> cls, String query, Object... params) {
        List<T> dtoList = new ArrayList<T>();

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(query);
            for (int i = 1; i < params.length + 1; i++) {
                pstmt.setObject(i, params[i - 1]);
            }
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    T dto = createDTO(cls);
                    dtoList.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return dtoList;
    }

    protected abstract T createDTO(Class<T> cls) throws SQLException;

    protected abstract List<T> executeQuery(String query, Object... params);
}
