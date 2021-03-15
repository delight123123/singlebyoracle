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
<title>싱글프로젝트</title>


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
	var move;
	var dirdir;
	$(function() {
		$("tbody a").click(function() {
			 move=$(this).find("input[class=na]").val();
			dirdir=$("input[name=filedir]").val();
			//alert(dirdir+'/'+move);
			
			/*$("input[name=filedir]").val(dirdir+'/'+move);
			$("input[name=dirname]").val(move);
			$("form[name=frm]").submit();
			if(move.lastIndexOf(".")!=-1){
				$("input[name=filedir]").val(dirdir);
				$("input[name=dirname]").val("");
				$("form[name=frm]").submit();
			} */
			
			$.ajax({
				url:"<c:url value='/confirm'/>",
				type:"post",
				data:
					{
						godir : dirdir+'/'+move
					}
				,
				success:function(res){
					 if(res==1){
						//alert("폴더");
						$("input[name=filedir]").val(dirdir+'/'+move);
						$("input[name=dirname]").val(move);
						$('form[name=frm]').prop("action",
						"<c:url value='/home2'/>");
						$("form[name=frm]").submit();
					}else if(res==2){
						//alert("파일");
						$("input[name=downdir]").val(dirdir+'/'+move);
						$("input[name=dirname]").val(move);
						$('form[name=frm]').prop("action",
						"<c:url value='/download'/>");
						$("form[name=frm]").submit();
					}
					
				},
				error:function(xhr,status,error){
					alert("Error : "+status+", "+error);
				}
			});
			
		});
		
		$("table thead input[type=checkbox]").click(function(){
			$("table tbody input[type=checkbox]").prop("checked", this.checked);
		});
		
		$("#updirbtn").click(function() {
			$("input[name=filedir]").val($("input[name=original1]").val());
			$("input[name=dirname]").val($("input[name=original2]").val());
			$("input[name=updirtype]").val("2");
			$('form[name=frm]').prop("action",
			"<c:url value='/home2'/>");
			$("form[name=frm]").submit();
		});
		
		$("#filecompression").change(function() {
			if($("#filecompression").val()!=""){
				if($("tbody input[type=checkbox]:checked").length>0){
					$("form[name=frm]").prop("action","<c:url value='/compressiondown'/>");
					$("form[name=frm]").submit();
				}else{
					alert("압축 다운로드 할 파일은 선택해 주시기 바랍니다.");
					$("#filecompression").val("").prop("selected", true);
				}
			}
		});
		
		$("#logout").click(function() {
			if(confirm("로그아웃 하시겠습니까?")){
				location.href="<c:url value='/logout'/>";
			}
		});
		
		$("#authselect").click(function() {
			location.href="<c:url value='/authselect'/>";
		});
		
	});
</script>
</head>
<body>
<form action="<c:url value='/home2'/>" method="post" name="frm">
	<input type="hidden" value="${dir }" name="filedir">
	<input type="hidden" value="${dir }" name="downdir">
	<input type="hidden" value="${dirname }" name="dirname">
	<input type="hidden" value="${dir }" name="original1">
	<input type="hidden" value="${dirname }" name="original2">
	<input type="hidden" value="1" name="updirtype">
	<div class="content-wrapper">
		<div class="card">
			<div class="card-body">
				<div style="margin-bottom: 10px; margin-left: 0px; margin-right: 0px;" class="row">
					<div style="width: 83%; position: relative;" class="row">
						<input type="button" value="상위폴더로"
							class="btn btn-gradient-light btn-rounded btn-sm col-sm-2" name="updirbtn" id="updirbtn" style="position: relative;">
							
						<select name="filecompression" class="form-control form-control-sm col-sm-2" id="filecompression">
							<option value="">압축 다운</option>
							<option value="zip">.zip</option>
						</select>
					</div>
					<div style="width: 17%; float: right;" class="row">
						<input type="button" class="btn btn-gradient-light btn-rounded btn-sm" id="authselect" value="공유폴더설정" style="display: inline;">
						<input type="button" class="btn btn-gradient-light btn-rounded btn-sm" id="logout" value="로그아웃" style="display: inline;">
					</div>
				</div>

				<div>

					<table class="table text-center table-bordered">
						<colgroup>
							<col style="width: 5%;">
							<col style="width: 80%;">
							<col style="width: 15%;">
						</colgroup>
						<thead class="table-danger">
							<tr>
								<th><input type="checkbox"></th>
								<th>파일명</th>
								<th>파일크기</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="idx" value="0" />
							<c:forEach var="map" items="${list }">
							<c:set value="${map['fileName'] }"  var="name"></c:set>
							<tr>
							<td><input type="checkbox" id="chk_${idx }" 
							name="filenamelist[${idx }]" 
							value="
							<c:if test="${ fn:indexOf(name,'?')==-1}">
							${map['fileName'] }
							</c:if>
							<c:if test="${ fn:indexOf(name,'?')!=-1}">
							${fn:replace(name,'?','') }
							</c:if>
							"></td>
							<td><a href="#">
							<c:if test="${ fn:indexOf(name,'?')==-1}">
							<img alt="" src="<c:url value='/resources/images/file.gif'/>">
							<input type="hidden" value="${name }" class="na">
							${map['fileName'] }
							</c:if>
							<c:if test="${ fn:indexOf(name,'?')!=-1}">
							<input type="hidden" value='${fn:replace(name,"?","") }' class="na">
							${fn:replace(name,"?","") }
							</c:if>
							
							</a></td>
							<td>

							<fmt:formatNumber value="${map['size']}" pattern="#,###"/> KB


							</td>
							</tr>
							<c:set var="idx" value="${idx+1}" />
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
					</form>
</body>
</html>