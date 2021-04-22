<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/mainTop.jsp" %>


<div class="content-wrapper">
	<div class="card">
		<div class="card-body">
			<div id="alldiv">
				<div id="chating" ></div>
				<input type="text" placeholder="채팅 글 입력창" id="chatting"  class="form-control form-control-sm" />
				<input type="button" value="입력" id="sand" onclick="send()"  class="form-control form-control-sm" />
			</div>
		</div>
	</div>
</div>

<%@include file="../inc/mainBottom.jsp" %>
<style type="text/css">
.card{
	height: 100%;
}
#alldiv{
	height: 100%;
	width: 100%;
}
#chating{
	width: 100%;
	height: 100%;
}
#chatting{
	width: 90%;
	float: left;
}
#sand{
	width: 10%;
	float: left;
}
</style>
<script type="text/javascript">

var userid='${sessionScope.userid}';
var realTime = new Date();
//var realTime=da.format('yyyy-MM-dd(KS) HH:mm:ss');
var ws;
	$(function() {
		
		$("#realTimeChat").addClass("active");
    	
		wsOpen();
	});
	
	

	function wsOpen(){
		ws = new WebSocket("ws://" + location.host + "/chatting");
		wsEvt();
	}
		
	function wsEvt() {
		ws.onopen = function(data){
			//소켓이 열리면 초기화 세팅하기
		}
		
		ws.onmessage = function(data) {
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				$("#chating").append("<p>" + msg + "</p>");
			}
		}

		document.addEventListener("keypress", function(e){
			if(e.keyCode == 13){ //enter press
				send();
			}
		});
	}


	function send() {
		var uN = userid;
		var msg = $("#chatting").val();
		ws.send("["+realTime+"]"+uN+" : "+msg);
		$('#chatting').val("");
	}
	

</script>