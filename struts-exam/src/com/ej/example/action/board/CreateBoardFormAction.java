package com.ej.example.action.board;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateBoardFormAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        CustomActionForward actionForward = new CustomActionForward();
        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_post_form.jsp");
        return actionForward;
    }

}
