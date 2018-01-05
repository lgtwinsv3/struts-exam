package com.ej.example.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;
import com.ej.example.service.MemberService;

public class MemberAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(MemberAction.class);
	
	private MemberService service = new MemberService(new MemberDAO());

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Paging<MemberDTO> paging = new Paging<MemberDTO>();
		if (request.getParameter("page") != null && request.getParameter("size") != null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
			paging.setRowCount(Integer.parseInt(request.getParameter("size")));
		}

		paging.calcPaging(service.getCount());
		paging = service.getList(paging.getPage(), paging.getRowCount());

		// logs debug
		if (logger.isDebugEnabled()) {
			logger.debug("MemberAction.execute()");
		}

		// logs exception
		logger.error("This is Error message", new Exception("Testing"));

		request.setAttribute("model", paging);

		return mapping.findForward("list");
	}

	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Paging<MemberDTO> paging = new Paging<MemberDTO>();
		if (!"".equalsIgnoreCase(request.getParameter("page"))) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (!"".equals(request.getParameter("size"))) {
			paging.setRowCount(Integer.parseInt(request.getParameter("size")));
		}
		
		MemberDTO dto = new MemberDTO();
		if ( request.getParameter("seq") != null) {
			dto = service.getOne(Integer.parseInt(request.getParameter("seq")));
		}
		
		paging.setBody(dto);
		request.setAttribute("model", paging);

		return mapping.findForward("read");
	}
	
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		int page = 1;
		int size = 10;
		if (!"".equalsIgnoreCase(request.getParameter("page"))) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (!"".equals(request.getParameter("size"))) {
			size = Integer.parseInt(request.getParameter("size"));
		}

		MemberDTO dto = new MemberDTO();
		dto.setUserId(request.getParameter("userId"));
		dto.setName(request.getParameter("name"));
		dto.setPassword(request.getParameter("password"));
		
		if (request.getParameter("email") != null && !"".equals(request.getParameter("email")) ) {
			dto.setEmail(request.getParameter("email"));	
		}
		
		int seq = service.regist(dto);
		
		Paging<MemberDTO> paging = service.getList(page, size);

		request.setAttribute("model", paging);

		return mapping.findForward("list");
	}
	
	public ActionForward postForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Paging<MemberDTO> paging = new Paging<MemberDTO>();
		if (!"".equalsIgnoreCase(request.getParameter("page"))) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (!"".equals(request.getParameter("size"))) {
			paging.setRowCount(Integer.parseInt(request.getParameter("size")));
		}
		return mapping.findForward("post_form");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int seq = Integer.parseInt(request.getParameter("seq"));
		MemberDTO dto = service.getOne(seq);

		String userId = request.getParameter("userId");
		String name = request.getParameter("name"); 
		String password = request.getParameter("password");
		
		//TODO email null check//
		String email = request.getParameter("email");

		Paging<MemberDTO> paging = new Paging<MemberDTO>();
		if (!"".equalsIgnoreCase(request.getParameter("page"))) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (!"".equals(request.getParameter("size"))) {
			paging.setRowCount(Integer.parseInt(request.getParameter("size")));
		}

		dto.setUserId("".equals(userId) ? dto.getUserId() : userId);
		dto.setName("".equals(name) ? dto.getName() : name);
		dto.setPassword(password);
		dto.setEmail("".equals(email) ? dto.getEmail() : email);

		if (service.modify(dto) > 0) {
			paging.setBody(service.getOne((int) dto.getSeq()));
		}

		request.setAttribute("model", paging);

		return mapping.findForward("read");
	}

	public ActionForward updateForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	        Paging<MemberDTO> paging = new Paging<MemberDTO>();
	        if (!"".equalsIgnoreCase(request.getParameter("page"))) {
	            paging.setPage(Integer.parseInt(request.getParameter("page")));
	        }
	        if (!"".equals(request.getParameter("size"))) {
	            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
	        }

	        MemberDTO dto = new MemberDTO();
	        if (request.getParameter("seq") != null) {
	            dto = service.getOne(Integer.parseInt(request.getParameter("seq")));
	        }

	        paging.setBody(dto);
	        request.setAttribute("model", paging);

		return mapping.findForward("update_form");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int seq = Integer.parseInt(request.getParameter("seq"));

		Paging<MemberDTO> paging = new Paging<MemberDTO>();
		if (!"".equalsIgnoreCase(request.getParameter("page"))) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
		}
		if (!"".equals(request.getParameter("size"))) {
			paging.setRowCount(Integer.parseInt(request.getParameter("size")));
		}


		int result = service.remove(seq);

		if (result > 0) {
			paging =  service.getList(paging.getPage(), paging.getRowCount());
			
			request.setAttribute("model", paging);
			
			ActionForward forward = mapping.findForward("list");
			forward.setRedirect(true);
			
			return forward;
		}

		MemberDTO dto = service.getOne(seq);
		paging.setBody(dto);

		request.setAttribute("model", paging);

		return mapping.findForward("read");
	}
	
}
