package com.ej.example.action.board;

import com.ej.example.dao.board.BoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.Paging;
import com.ej.example.service.BoardService;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListBoardAction extends Action {
	
	private static final Logger logger = Logger.getLogger(ListBoardAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        BoardDAO boardDao = new BoardDAO();
        Paging<BoardDTO> paging = new Paging<BoardDTO>();
        if (request.getParameter("page") != null && request.getParameter("size") != null) {
            paging.setPage(Integer.parseInt(request.getParameter("page")));
            paging.setRowCount(Integer.parseInt(request.getParameter("size")));
        }

        paging.calcPaging(boardDao.selectCount());
        List<BoardDTO> dtoList = boardDao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(dtoList);
        
      //logs debug
      		if(logger.isDebugEnabled()){
      			logger.debug("ListBoardAction.execute()");
      		}

      		//logs exception
      		logger.error("This is Error message", new Exception("Testing"));


      		request.setAttribute("model", paging);

        return mapping.findForward("success");
    }
    


}
