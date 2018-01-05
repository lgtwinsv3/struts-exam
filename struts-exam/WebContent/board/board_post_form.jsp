<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
<title>등록(신규)</title>
<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="/webjars/summernote/0.8.8/dist/summernote.js"></script>
<script src="/webjars/summernote/0.8.8/dist/summernote.min.js"></script>
<script src="/webjars/summernote/0.8.8/dist/lang/summernote-ko-KR.js"></script>
<script
	src="/webjars/summernote/0.8.8/dist/lang/summernote-ko-KR.min.js"></script>
<link href="/webjars/summernote/0.8.8/dist/summernote.css"
	rel="stylesheet">


<script>
	$(function() {
		$('#summernote').summernote(
				{
					lang : 'ko-KR',
					height : 300, // set editor height
					minHeight : null, // set minimum height of editor
					maxHeight : null, // set maximum height of editor
					focus : false, // set focus to editable area after initializing summernote
					airMode : false,
					toolbar : [ [ 'style', [ 'style' ] ],
							[ 'fontsize', [ 'fontsize' ] ],
							[ 'font', [ 'bold', 'underline', 'clear' ] ],
							[ 'fontname', [ 'fontname' ] ],
							[ 'color', [ 'color' ] ],
							[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
							[ 'table', [ 'table' ] ],
							[ 'insert', [ 'link', 'picture', 'video' ] ],
							[ 'view', [ 'fullscreen', 'help' ] ] ],
					fontNames : [ 'NanumGothic', 'MoebiusRegular',
							'MoebiusBold', 'Arial', 'Arial Black',
							'Comic Sans MS', 'Courier New' ],
					fontNamesIgnoreCheck : [ 'MoebiusRegular', 'MoebiusBold' ],
					fontSizes : [ '8', '9', '10', '11', '12', '13', '18', '24',
							'36', '48', '64', '82', '150' ],
					popover : {
						image : [
								[ 'custom', [ 'imageAttributes' ] ],
								[
										'imagesize',
										[ 'imageSize100', 'imageSize50',
												'imageSize25' ] ],
								[
										'float',
										[ 'floatLeft', 'floatRight',
												'floatNone' ] ],
								[ 'remove', [ 'removeMedia' ] ] ],
					},
					imageAttributes : {
						icon : '<i class="note-icon-pencil"/>',
						removeEmpty : false, // true = remove attributes | false = leave empty if present
						disableUpload : false
					// true = don't display Upload Options | Display Upload Options
					}
				});

	})
</script>
<%--<script src="<c:url value="/js/summernote-image-attributes.js"/>"></script>--%>
<%--<link href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">--%>
</head>
<body>
	<c:set var="paging" value="${requestScope.model}" />
	<div class="wrapper">
		<div class="container" style="margin-top: 100px">
			<h2>게시물 등록</h2>
			<div class="panel panel-default">
				<div class="panel-body">

					<html:form action="board" method="post" styleClass="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-2" for="subject">제목</label>
							<div class="col-sm-8">
								<html:text property="subject" styleClass="form-control" />

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="writer">작성자</label>
							<div class="col-sm-8">
								<html:text property="writer" styleClass="form-control" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="password">비밀번호</label>
							<div class="col-sm-8">
								<html:password property="password" name="password"
									styleClass="form-control" value="" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="summernote">내용</label>
							<div class="col-sm-8">
								<html:textarea rows="15" styleId="summernote" property="content"
									styleClass="form-control">
								</html:textarea>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class="col-sm-8">
								<html:submit value="등록" styleId="returnBtn"
									styleClass="btn btn-default" style="float: right" />
								<%--<input type="button" value="추가" onclick="addRegistForm();" class="btn btn-default" />--%>
								<%--<input type="button" value="수정" onclick="onModifyForm('${user.userId}');" id="editBtn" class="btn btn-mini" />--%>
								<%--<input type="button" value="삭제" onclick="onRemoveAdminConfirm('${user.userId}');" class="btn btn-mini btn-danger" />--%>
							</div>
						</div>

						<html:hidden property="method" value="post" />
						<html:hidden property="page" name="page" value="${paging.page}" />
						<html:hidden property="size" name="size" value="${paging.rowCount}" />
						<%--<input type="hidden" name="searchCondition" value="${paging.searchCondition}">--%>
						<%--<input type="hidden" name="searchKeyword" value="${paging.searchKeyword}">--%>
						<%--<input type="hidden" name="searchCategory" value="${paging.searchCategory}">--%>
						<%--<input type="hidden" name="searchEnabled" value="${paging.searchEnabled}">--%>
					</html:form>

				</div>
			</div>
		</div>
	</div>
</body>

</html>
