<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/mainTop.jsp" %>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=adb8a2445b89b68e86c305b800700af9"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.56575, 126.97261), // 지도의 중심좌표
		        level: 3, // 지도의 확대 레벨
		        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
		    }; 

		// 지도를 생성한다 
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 지도 타입 변경 컨트롤을 생성한다
		var mapTypeControl = new kakao.maps.MapTypeControl();

		// 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);	

		// 지도에 확대 축소 컨트롤을 생성한다
		var zoomControl = new kakao.maps.ZoomControl();

		// 지도의 우측에 확대 축소 컨트롤을 추가한다
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		// 지도에 마커를 생성하고 표시한다
		var marker = new kakao.maps.Marker({
		    position: new kakao.maps.LatLng(37.56575, 126.97261), // 마커의 좌표
		    draggable : true, // 마커를 드래그 가능하도록 설정한다
		    map: map // 마커를 표시할 지도 객체
		});

	</script>


<div class="content-wrapper">
	<div class="card">
		<div class="card-body" style="width: 100%; overflow: auto;">
		
		<div id="map" style="width:750px;height:350px;"></div>
		
		</div>
	</div>
</div>


<%@include file="../inc/mainBottom.jsp" %>

<script type="text/javascript">
	$(function() {
		$("#weather").addClass("active");
	});
	
	
</script>