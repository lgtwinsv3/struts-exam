<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp" %>
<html>
<head>
    <title>등록</title>
    <script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>

    <script>
        $(function () {
        })
    </script>
    <%--<script src="<c:url value="/js/summernote-image-attributes.js"/>"></script>--%>
    <%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
</head>
<body>
<c:set var="paging" value="${requestScope.model}"/>
<div class="wrapper">
    <div class="container" style="margin-top: 100px">
        <h2>회원 가입</h2>
        <div class="panel panel-default">
            <div class="panel-body">
                <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
                <form action="${contextPath}/app/member.do" method="post" id="postFrm" name="postFrm" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="userId">ID </label>
                        <div class="col-sm-8">
                            <input type="text" id="userId" name="userId" class="form-control" placeholder="ID" value="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="name">이름</label>
                        <div class="col-sm-8">
                            <input type="text" id="name" name="name" class="form-control" placeholder="이름" value="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="password">비밀번호</label>
                        <div class="col-sm-8">
                            <input type="password" id="password" name="password" class="form-control"
                                   placeholder="비밀번호">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="passwordConfirm">비밀번호 확인</label>
                        <div class="col-sm-8">
                            <input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control"
                                   placeholder="비밀번호 확인">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="email">메일 주소</label>
                        <div class="col-sm-8">
                            <input type="text" id="email" name="email" class="form-control" placeholder="메일">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                            <input type="submit" value="등록" id="returnBtn" class="btn btn-default" style="float: right"/>
                            <%--<input type="button" value="추가" onclick="addRegistForm();" class="btn btn-default" />--%>
                            <%--<input type="button" value="수정" onclick="onModifyForm('${user.userId}');" id="editBtn" class="btn btn-mini" />--%>
                            <%--<input type="button" value="삭제" onclick="onRemoveAdminConfirm('${user.userId}');" class="btn btn-mini btn-danger" />--%>
                        </div>
                    </div>
                    <input type="hidden" name="method" value="post">
                    <input type="hidden" name="page" value="${paging.page}">
                    <input type="hidden" name="size" value="${paging.rowCount}">
                    <%--<input type="hidden" name="searchCondition" value="${paging.searchCondition}">--%>
                    <%--<input type="hidden" name="searchKeyword" value="${paging.searchKeyword}">--%>
                    <%--<input type="hidden" name="searchCategory" value="${paging.searchCategory}">--%>
                    <%--<input type="hidden" name="searchEnabled" value="${paging.searchEnabled}">--%>
                </form>

            </div>
        </div>
    </div>
</div>
</body>

</html>
