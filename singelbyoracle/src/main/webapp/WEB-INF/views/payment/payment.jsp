<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/mainTop.jsp" %>
<div class="content-wrapper">
	<div class="card">
		<div class="card-body">
			<div>
				우선 결제창 어찌 할지 정리 여기다<br><br>
				- 결제 시스템<br>
				금액 적어 놓고 결제하기 해서 결제창 넘어가서 결제 되게
				(금액은 1원으로 되게)<br>
				결제 후 받는 정보 중에서 필요한 것 db에 저장<br><br>
				
				- 결제 내역<br>
				결제 내역(아임포트에서 받아올 수 있을듯?) 뿌려주고 환불신청 가능하게 만들기<br><br>
				
				-환불 시스템<br>
				결제 시스템에서 db저장한 것들 불러다가 테이블로 쭉 뿌려주고
				db에 환불 여부 N 신청은 I 완료는 Y로 표시해서 버튼 출력
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