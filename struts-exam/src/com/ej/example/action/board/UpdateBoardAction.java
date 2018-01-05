package com.ej.example.action.board;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateBoardAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        CustomActionForward actionForward = new CustomActionForward();
        BoardDAO boardDao = new BoardDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        BoardDTO dto = boardDao.selectOne(seq);

        String writer = request.getParameter("writer");
        String subject = request.getParameter("subject");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        if (!password.equals(dto.getPassword())) {
            paging.setBody(dto);
            actionForward.setRedirect(false);
            actionForward.setModel(paging);
            actionForward.setPath("/board/board_view.jsp");
            return actionForward;
        }

        dto.setWriter("".equals(writer) ? dto.getWriter() : writer);
        dto.setSubject("".equals(subject) ? dto.getSubject() : subject);
        dto.setPassword(password);
        dto.setContent("".equals(content) ? dto.getContent() : content);

        if (boardDao.update(dto) > 0) {
            paging.setBody(boardDao.selectOne(dto.getSeq()));
        }
        ;

        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_view.jsp");

        return actionForward;
    }

}
