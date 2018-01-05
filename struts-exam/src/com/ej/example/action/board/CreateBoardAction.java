package com.ej.example.action.board;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;
import com.ej.example.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CreateBoardAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        BoardService service = new BoardService(new BoardDAO());
        CustomActionForward actionForward = new CustomActionForward();

        int page = 1;
        int size = 10;
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (!"".equals(request.getParameter("size"))) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        BoardDTO dto = new BoardDTO();
        dto.setSubject(request.getParameter("subject"));
        dto.setWriter(request.getParameter("writer"));
        dto.setPassword(request.getParameter("password"));
        dto.setContent(request.getParameter("content"));

        int seq = service.regist(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        Paging<BoardDTO> paging = service.getList(page, size);

        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_list.jsp");

        return actionForward;
    }

}
