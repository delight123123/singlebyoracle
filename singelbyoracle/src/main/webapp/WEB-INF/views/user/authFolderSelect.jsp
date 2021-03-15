<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>공유 폴더 선택</title>
<link rel="stylesheet"
	href="<c:url value='/resources/assets/vendors/mdi/css/materialdesignicons.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/vendors/css/vendor.bundle.base.css'/>">

<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/style.css'/>">
<!-- End layout styles -->
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$("#reset").click(function() {
			location.href="<c:url value='/home2'/>";
		});
		
		$("form[name=frm]").submit(function() {
			if($("input[type=file]").val().length<=0){
				alert("파일을 선택해주시기 바랍니다.");
				event.preventDefault();
			}
		});
	});
</script>
</head>
<body>
<form action="" name="frm" method="post" enctype="multipart/form-data">
	<div class="content-wrapper">
		<div class="card">
			<div class="card-body">
				<div class="card-body display-4 text-danger ">
					공유하고자 하는 폴더 내의 파일 하나를 지정해 주시기 바랍니다.
				</div>
				<div class="card-body">
					<input type="file" name="authfolder">
				</div>
				<div class="card-body" style="text-align: right;">
					<input type="submit" value="설정" id="select" class="btn btn-gradient-success btn-rounded btn-sm">
					<input type="button" value="취소" id="reset" class="btn btn-gradient-dark btn-rounded btn-sm">
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>