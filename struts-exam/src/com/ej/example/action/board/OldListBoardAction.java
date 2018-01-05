package com.ej.example.action.board;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class OldListBoardAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        BoardDAO boardDao = new BoardDAO();
        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        paging.calcPaging(boardDao.selectCount());
        List<BoardDTO> dtoList = boardDao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(dtoList);

        CustomActionForward actionForward = new CustomActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/board/board_list.jsp");
        actionForward.setModel(paging);
        return actionForward;
    }
}
