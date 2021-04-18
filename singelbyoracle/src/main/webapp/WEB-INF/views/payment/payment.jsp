<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/mainTop.jsp" %>
<div class="content-wrapper">
	<div class="card">
		<div class="card-body">
			<div>
				<input type="number" placeholder="결제 금액을 적어주세요" id="price" class="form-control form-control-sm" />
				<input type="button" value="결제하기" class="form-control form-control-sm" />
			</div>
		</div>
	</div>
</div>

<%@include file="../inc/mainBottom.jsp" %>

<script type="text/javascript">


	
	$(function() {
		
		$("#paymentSystem").addClass("active");
    	

	});
	


</script>