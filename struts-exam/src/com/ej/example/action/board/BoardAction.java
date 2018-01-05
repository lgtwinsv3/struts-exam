package com.ej.example.action.board;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardActionForm;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;
import com.ej.example.service.BoardService;

public class BoardAction extends DispatchAction {

	private static final Logger logger = Logger.getLogger(BoardAction.class);

	private BoardService service = new BoardService(new BoardDAO());

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;
		Paging<BoardDTO> paging = new Paging<BoardDTO>();
		paging = service.getList(boardForm.getPage(), boardForm.getSize());

		// logs debug
		if (logger.isDebugEnabled()) {
			logger.debug("BoardAction.execute()");
		}

		// logs exception
		logger.error("This is Error message", new Exception("Testing"));

		request.setAttribute("model", paging);

		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;

		Paging<BoardDTO> paging = new Paging<BoardDTO>(boardForm.getPage(), boardForm.getSize());
		BoardDTO dto = service.getOne(boardForm.getSeq());

		paging.setBody(dto);

		request.setAttribute("model", paging);

		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;
		BoardService service = new BoardService(new BoardDAO());

		BoardDTO dto = new BoardDTO();
		dto.setSubject(boardForm.getSubject());
		dto.setWriter(boardForm.getWriter());
		dto.setPassword(boardForm.getPassword());
		dto.setContent(boardForm.getContent());

		int seq = service.regist(dto);

		if (seq < 0) {
			throw new SQLException();
		}

		Paging<BoardDTO> paging = service.getList(boardForm.getPage(), boardForm.getSize());

		request.setAttribute("model", paging);

		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward postForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;
		Paging<BoardDTO> paging = new Paging<>(boardForm.getPage(), boardForm.getSize());

		request.setAttribute("model", paging);
		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;

		int seq = boardForm.getSeq();
		BoardDTO dto = service.getOne(seq);

		String writer = boardForm.getWriter();
		String subject = boardForm.getSubject();
		String password = boardForm.getPassword();
		String content = boardForm.getContent();

		Paging<BoardDTO> paging = new Paging<BoardDTO>();
		paging.setPage(boardForm.getPage());
		paging.setRowCount(boardForm.getSize());
	
		if (!password.equals(dto.getPassword())) {
			paging.setBody(dto);
			request.setAttribute("model", paging);
			return mapping.findForward(boardForm.getMethod());
		}

		dto.setWriter("".equals(writer) ? dto.getWriter() : writer);
		dto.setSubject("".equals(subject) ? dto.getSubject() : subject);
		dto.setPassword(password);
		dto.setContent("".equals(content) ? dto.getContent() : content);

		if (service.modify(dto) > 0) {
			paging.setBody(service.getOne(dto.getSeq()));
		}

		request.setAttribute("model", paging);

		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward updateForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardActionForm boardForm = (BoardActionForm) form;

		Paging<BoardDTO> paging = new Paging<BoardDTO>();
		paging.setPage(boardForm.getPage());
		paging.setRowCount(boardForm.getSize());

		BoardDTO dto = new BoardDTO();
		if (request.getParameter("seq") != null) {
			dto = service.getOne(boardForm.getSeq());
		}

		paging.setBody(dto);
		request.setAttribute("model", paging);

		return mapping.findForward(boardForm.getMethod());
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) throws Exception {

		BoardActionForm boardForm = (BoardActionForm) form;
		
		int seq = boardForm.getSeq();

		Paging<BoardDTO> paging = new Paging<BoardDTO>();
		paging.setPage(boardForm.getPage());
		paging.setRowCount(boardForm.getSize());

		int result = service.remove(seq);

		if (result > 0) {
			paging = service.getList(paging.getPage(), paging.getRowCount());

			request.setAttribute("model", paging);

			ActionForward forward = mapping.findForward("list");
			return forward;
		}

		BoardDTO dto = service.getOne(seq);
		paging.setBody(dto);
		request.setAttribute("model", paging);
		
		System.out.println(boardForm.getMethod());

		return mapping.findForward(boardForm.getMethod());
	}

}
