<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!doctype html>
<html>
<head>
    <title>조회</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
    <script>
        function goListPage() {
            document.viewFrm.submit();
        }

        function doAction(seq, command) {
            document.actionForm.seq.value = seq;
            document.actionForm.method.value = command;
            document.actionForm.submit();

        }
    </script>
    <style>
        ul > li > a {
            height: 34px;
        }
    </style>
</head>
<body>
<c:set var="paging" value="${requestScope.model}"/>
<c:set var="vo" value="${paging.body}"/>
<div class="wrapper">
    <div class="container" style="margin-top: 100px">
        <h2>회원 정보 조회</h2>
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="./member.do" method="get" id="viewFrm" name="viewFrm" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userId">사용자 ID</label>
                        <div class="col-sm-10">
                            <input type="text" id="userId" name="userId" value="${vo.userId}" class="form-control" placeholder="User ID" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">이름</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" value="${vo.name}" class="form-control" placeholder="이름" disabled>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="email">E-mail</label>
                        <div class="col-sm-10">
                            <input type="text" id="email" name="email"
                                   <%--value='<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/>' --%>value="${vo.email}"
                                   class="form-control"
                                   placeholder="메일" disabled>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="createDate">가입일</label>
                        <div class="col-sm-10">
                            <input type="text" id="createDate" class="form-control" placeholder="생성일" disabled value='${vo.createDate}'>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-10">
                            <input type="button" value="목록" onclick="doAction(0, 'list');" class="btn btn-default"/>
                            <input type="button" value="추가" onclick="doAction(0, 'postForm');" class="btn btn-default"/>
                            <input type="button" value="수정" onclick="doAction('${vo.seq}', 'updateForm');" id="editBtn" class="btn btn-mini"/>
                            <input type="button" value="삭제" onclick="doAction('${vo.seq}', 'delete');" class="btn btn-mini btn-danger"/>
                        </div>
                    </div>
                    <input type="hidden" name="page" value="${paging.page}">
                    <input type="hidden" name="size" value="${paging.rowCount}">
                    <%-- <input type="hidden" name="searchCondition" value="${paging.searchCondition}">
                     <input type="hidden" name="searchKeyword" value="${paging.searchKeyword}">
                     <input type="hidden" name="searchCategory" value="${paging.searchCategory}">
                     <input type="hidden" name="searchEnabled" value="${paging.searchEnabled}">--%>
                </form>

            </div>
        </div>
    </div>
</div>
<form name="actionForm" method="get" action="./member.do">
    <input type="hidden" name="method">
    <input type="hidden" name="seq"/>
    <input type="hidden" name="page" value="${paging.page}">
    <input type="hidden" name="size" value="${paging.rowCount}">
    <%--<input type="hidden" name="keyField" value="<%=keyField%>"/>--%>
    <%--<input type="hidden" name="keyWord" value="<%=keyWord%>"/>--%>
</form>


</body>
</html>
