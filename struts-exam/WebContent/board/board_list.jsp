<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>

<html>
<head>
<title>글 목록</title>
<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
<%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
<script>
	function doAction(seq, command) {
		document.pagingFrm.seq.value = seq;
		document.pagingFrm.method.value = command;
		document.pagingFrm.submit();

	}

	function goPage(page) {
		document.pagingFrm.page.value = page;
		document.pagingFrm.method.value = 'list';
		document.pagingFrm.submit();
	}
</script>
<style>
ul>li>a {
	height: 34px;
}
</style>
</head>
<body>
	<c:set var="paging" scope="request" value="${requestScope.model}" />
	<div class="wrapper">
		<div class="container" style="margin-top: 100px">
			<h2>게시물 목록</h2>
			<div class="panel panel-default">
				<div class="panel-body">
					<a href="javascript: doAction(0, 'postForm')"><input
						type="button" value=" 글쓰기" class="btn btn-primary"
						style="float: right" /></a>
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>제목</th>
								<th>작성자</th>
								<th>게시일</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>

							<c:set var="list" value="${paging.body}" />
							<c:if test="${fn:length(list) eq 0}">
								<tr>
									<td>no data</td>
								</tr>
							</c:if>

							<c:if test="${fn:length(list) > 1}">
								<c:forEach var="vo" items="${list}">
									<tr>
										<th>${vo.seq}</th>
										<td><a href="javascript:doAction('${vo.seq}', 'read')">${vo.subject}</a></td>
										<td>${vo.writer}</td>
										<td>${vo.createDate}</td>
										<td>${vo.readCount}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<form name="pagingFrm" method="get" action="./board.do">
						<div class="form-group row">
							<div class="form-group col-md-10">
								<c:if test="${fn:length(paging.body) ne 0}">
									<ul class="pagination">
										<c:choose>
											<c:when
												test="${paging.firstPage < 1 || paging.firstPage == paging.page}">
												<li class="disabled"><a href="javascript:"><span
														class="glyphicon glyphicon-backward"></span></a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:goPage(${paging.firstPage});"><span
														class="glyphicon glyphicon-backward"></span></a></li>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when
												test="${paging.prevPage < 1 || paging.prevPage == paging.page}">
												<li class="disabled"><a href="javascript:"><span
														class="glyphicon glyphicon-chevron-left"></span></a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:goPage(${paging.prevPage});"><span
														class="glyphicon glyphicon-chevron-left"></span></a></li>
											</c:otherwise>
										</c:choose>

										<c:forEach begin="${paging.startPage}" end="${paging.endPage}"
											var="indexI" step="1">
											<c:choose>
												<c:when test="${indexI == paging.page}">
													<li class="active"><a href="javascript:">${indexI}</a></li>
												</c:when>
												<c:otherwise>
													<li><a href="javascript:goPage(${indexI});">${indexI}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>

										<c:choose>
											<c:when
												test="${paging.nextPage < 1 || paging.nextPage == paging.page}">
												<li class="disabled"><a href="javascript:"><span
														class="glyphicon glyphicon-chevron-right"></span></a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:goPage(${paging.nextPage});"><span
														class="glyphicon glyphicon-chevron-right"></span></a></li>
											</c:otherwise>
										</c:choose>

										<c:choose>
											<c:when
												test="${paging.lastPage < 1 || paging.lastPage == paging.page}">
												<li class="disabled"><a href="javascript:"><span
														class="glyphicon glyphicon-forward"></span></a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:goPage(${paging.lastPage});"><span
														class="glyphicon glyphicon-forward"></span></a></li>
											</c:otherwise>
										</c:choose>
									</ul>
								</c:if>
							</div>
							<div class="form-group col-md-2"></div>
						</div>
						<html:hidden property="method" value="read"/> 
						<html:hidden property="seq" name="seq" value=""/> 
						<html:hidden property="page" value="${paging.page}" /> 
						<html:hidden property="size"  value="${paging.rowCount}" />
						<%--<input type="hidden" name="keyField" value="<%=keyField%>"/>--%>
						<%--<input type="hidden" name="keyWord" value="<%=keyWord%>"/>--%>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>