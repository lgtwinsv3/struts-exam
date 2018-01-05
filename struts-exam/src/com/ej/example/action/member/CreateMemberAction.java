package com.ej.example.action.member;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CreateMemberAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        MemberDAO dao = new MemberDAO();
        CustomActionForward actionForward = new CustomActionForward();

        Paging<MemberDTO> paging = new Paging<MemberDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }
        paging.calcPaging(dao.selectCount());

        MemberDTO dto = new MemberDTO();
        dto.setEmail(request.getParameter("email"));
        dto.setName(request.getParameter("name"));
        dto.setPassword(request.getParameter("password"));
        dto.setUserId(request.getParameter("userId"));

        int seq = dao.insert(dto);

        if (seq < 0) {
            throw new SQLException();
        }

        List<MemberDTO> dtoList = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());
        paging.setBody(dtoList);
        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/member/member_list.jsp");

        return actionForward;
    }

}
