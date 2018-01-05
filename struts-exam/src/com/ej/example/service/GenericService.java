package com.ej.example.service;

import com.ej.example.dao.DAOSupport;
import com.ej.example.domain.DTO;
import com.ej.example.domain.Paging;

import java.sql.SQLException;
import java.util.List;

public class GenericService<V extends DTO, D extends DAOSupport> implements IService<V> {

    private D dao;

    public GenericService(DAOSupport dao) {
        this.dao = (D) dao;
    }

    public Paging<V> getList(int page, int rowCount) throws SQLException {

        int count = dao.selectCount();

        Paging<V> paging = new Paging<V>(page, rowCount);
        paging.calcPaging(count);

        List<V> list = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(list);

        return paging;
    }

    public V getOne(int seq) throws SQLException {
        return (V) dao.selectOne(seq);
    }

    public int regist(V dto) throws SQLException {
        return dao.insert(dto);
    }

    public int modify(V dto) throws SQLException {
        return dao.update(dto);
    }

    public int remove(int seq) throws SQLException {
        return dao.delete(seq);
    }
    
    // TODO search condition, search keyword..
    public int getCount() throws SQLException {
    	return dao.selectCount();
    }

    public D getDao() {
        return dao;
    }

}
