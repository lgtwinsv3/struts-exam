package com.ej.example.dao;

import com.ej.example.domain.DTO;

import java.sql.SQLException;
import java.util.List;

public interface DAOSupport<T> {

    List<T> selectList(int page, int rowCount) throws SQLException;

    int selectCount() throws SQLException;
    T selectOne(final int seq) throws SQLException;
    int insert(final T dto) throws SQLException;
    int update(final T dto) throws SQLException;
    int delete(final int seq) throws SQLException;
}
