package com.ej.example.action.board;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateBoardFormAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        CustomActionForward actionForward = new CustomActionForward();
        BoardDAO boardDao = new BoardDAO();

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        BoardDTO dto = new BoardDTO();
        if (request.getParameter("seq") != null) {
            dto = boardDao.selectOne(Integer.parseInt(request.getParameter("seq")));
        }

        paging.setBody(dto);
        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_update_form.jsp");

        return actionForward;
    }

}
