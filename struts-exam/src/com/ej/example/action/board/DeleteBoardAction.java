package com.ej.example.action.board;


import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DeleteBoardAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        CustomActionForward actionForward = new CustomActionForward();
        BoardDAO boardDao = new BoardDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));

        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        paging.calcPaging(boardDao.selectCount());

        int result = boardDao.delete(seq);
        actionForward.setRedirect(false);

        if (result > 0) {
            List<BoardDTO> dtoList = boardDao.selectList(paging.getSkipRowCount(), paging.getRowCount());
            paging.setBody(dtoList);
            actionForward.setModel(paging);
            actionForward.setPath("/board/board_list.jsp");
            return actionForward;
        }

        BoardDTO dto = boardDao.selectOne(seq);
        paging.setBody(dto);
        actionForward.setModel(paging);
        actionForward.setPath("/board/board_view.jsp");

        return actionForward;
    }

}
