package com.ej.example.action.member;

import com.ej.example.action.CustomActionForward;
import com.ej.example.action.IAction;
import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class DeleteMemberAction implements IAction {

    public CustomActionForward action(HttpServletRequest request) throws SQLException {
        CustomActionForward actionForward = new CustomActionForward();
        MemberDAO dao = new MemberDAO();
        int seq = Integer.parseInt(request.getParameter("seq"));

        Paging<MemberDTO> paging = new Paging<MemberDTO>();
        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
        }
        if (!"".equals(request.getParameter("size"))) {
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }
        paging.calcPaging(dao.selectCount());

        int result = dao.delete(seq);
        actionForward.setRedirect(false);

        if (result > 0) {
            List<MemberDTO> dtoList = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());
            paging.setBody(dtoList);
            actionForward.setModel(paging);
            actionForward.setPath("/member/member_list.jsp");
            return actionForward;
        }

        MemberDTO dto = dao.selectOne(seq);
        paging.setBody(dto);
        actionForward.setModel(paging);
        actionForward.setPath("/member/member_view.jsp");

        return actionForward;
    }

}
