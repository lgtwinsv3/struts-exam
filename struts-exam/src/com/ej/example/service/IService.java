package com.ej.example.service;

import com.ej.example.domain.Paging;

import java.sql.SQLException;

public interface IService<T> {

    Paging<T> getList(int page, int rowCount) throws SQLException;

    T getOne(final int seq) throws SQLException;

    int regist(final T dto) throws SQLException;

    int modify(final T dto) throws SQLException;

    int remove(final int seq) throws SQLException;
    
    // TODO search condition, search keyword..
    int getCount() throws SQLException;
}
