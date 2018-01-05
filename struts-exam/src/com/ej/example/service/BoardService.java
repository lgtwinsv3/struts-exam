package com.ej.example.service;

import com.ej.example.dao.DAOSupport;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;

import java.sql.SQLException;

public class BoardService extends GenericService<BoardDTO, BoardDAO> implements IService<BoardDTO> {
    public BoardService(DAOSupport dao) {
        super(dao);
    }

    @Override
    public BoardDTO getOne(int seq) throws SQLException {
        BoardDTO boardDTO = getDao().selectOne(seq);
        if (boardDTO != null) {
            getDao().updateReadCount(boardDTO);
        }
        return boardDTO;
    }
}
