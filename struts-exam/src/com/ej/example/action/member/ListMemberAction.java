package com.ej.example.action.member;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ListMemberAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        MemberDAO dao = new MemberDAO();
        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        paging.calcPaging(dao.selectCount());
        List<MemberDTO> dtoList = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(dtoList);

        CustomActionForward actionForward = new CustomActionForward();
        actionForward.setRedirect(false);
        actionForward.setPath("/member/member_list.jsp");
        actionForward.setModel(paging);
        return actionForward;
    }
}
