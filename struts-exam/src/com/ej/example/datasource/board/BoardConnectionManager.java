package com.ej.example.datasource.board;

import com.ej.example.datasource.ConnectionManager;
import com.ej.example.domain.BoardDTO;

import java.sql.SQLException;
import java.util.List;

public class BoardConnectionManager extends ConnectionManager<BoardDTO> {

    protected BoardDTO createDTO(Class<BoardDTO> cls) throws SQLException {

        BoardDTO dto;
            try {
                dto = cls.newInstance();
                dto.setSeq(rs.getInt("seq"));
                dto.setWriter(rs.getString("writer"));
                dto.setPassword(rs.getString("password"));
                dto.setSubject(rs.getString("subject"));
                dto.setContent(rs.getString("content"));
                dto.setReadCount(rs.getInt("read_count"));
                dto.setCreateDate(rs.getDate("create_date"));
                dto.setUpdateDate(rs.getDate("update_date"));
                return dto;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
    }

    protected List<BoardDTO> executeQuery(String query, Object... params) {
        return executeQuery(BoardDTO.class, query, params);
    }
}
