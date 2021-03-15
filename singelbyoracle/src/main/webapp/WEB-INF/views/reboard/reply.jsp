<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/mainTop.jsp" %>

<div class="content-wrapper">
	<div class="card">
		<div class="card-body">

            <form name="write" id="fileform" method="post" action="<c:url value='/reply'/>" enctype="multipart/form-data">
            <input type="text" name="groupno" value="${vo.groupno }">
			<input type="text" name="step" value="${vo.step }">
			<input type="text" name="sortno" value="${vo.sortno }">
			<input type="text" name="no" value="${vo.reboardNo }">
				<fieldset>
					<div id="aa">
						<label for="title">제목</label>
						<input type="text" name="reboardTitle" id="title" class="form-control form-control-fw" value="${vo.reboardTitle }">
					</div>
					<div id="divdiv">
						<label for="bo_content">내용</label>
						<textarea id="bo_content" name="reboardContent"></textarea>
					</div>

					<div id="lastdiv">
						<button type="button" class="btn btn-gradient-danger btn-rounded btn-fw" id="bfsub">작성완료</button>
					</div>
				</fieldset>
			</form>

		
		</div>
	</div>
</div>
<div class="body-overlay" id="overray">
<div id="progressdiv">
	<!-- Ajax Progress Status -->
<div id="viewLoading">
${vo.reboardContent }
</div>
</div>
</div>
<%@ include file="../inc/mainBottom.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style type="text/css">
#overray{
position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        overflow: hidden;
        width: 100%;
        height: 0;
        color: #fff;
        background: rgba(0, 0, 0, 0.6);
        -webkit-transition: .6s ease;
        transition: .6s ease;
        z-index: 1000;
}
#progressdiv{
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        text-align: center;
        height: 100%;
}
#fileform{
	width: 100%;
	margin: 0 auto;
}
#fileform #aa,#divdiv,#lastdiv{
	clear: both;
		border-bottom: 1px solid #eee;
		padding: 5px 0;
		margin: 0;
		overflow: auto;
}
#fileform label{
		text-align: right;
		padding: 3px 15px 0 0;
		clear: left;		
		font-weight: bold;
}
#bo_content{
	overflow: hidden;
	width: 10%;
}
#fileform fieldset{
	width: 90%;
		padding: 0;
		margin: 0 auto;
		border: 0;
		border-bottom: 2px solid #e2e2e2;
		}
#title,#upfile{
	width: 90%;
}
#lastdiv{
	text-align: center;
}
#viewLoading{
	background-color: white;
	display: block;
	background-size: cover;
	margin-top: 50%;
	text-align: center;
	color: black;
}

</style>


<script type="text/javascript">
$(function() {
	$("#mainBoard").addClass("active");
	
	CKEDITOR.replace('bo_content',{height: '300', width: '99%',
		filebrowserUploadUrl: "<c:url value='/ckimageup'/>"
	});
	CKEDITOR.instances.bo_content.setData($("#viewLoading").html());

	$("#bfsub").click(function() {

		$("#fileform").submit();
	});

	
});


</script>