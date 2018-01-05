package com.ej.example.action.member;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UpdateMemberAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        CustomActionForward actionForward = new CustomActionForward();
        MemberDAO dao = new MemberDAO();

        int seq = Integer.parseInt(request.getParameter("seq"));
        MemberDTO dto = dao.selectOne(seq);

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Paging<MemberDTO> paging = new Paging<MemberDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        dto.setPassword(password);
        dto.setName("".equals(name) ? dto.getName() : name);
        dto.setEmail("".equals(email) ? dto.getEmail() : email);

        if (dao.update(dto) > 0) {
            paging.setBody(dao.selectOne((int) dto.getSeq()));
        }


        actionForward.setRedirect(false);
        actionForward.setModel(paging);
        actionForward.setPath("/member/member_view.jsp");

        return actionForward;
    }

}
