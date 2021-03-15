<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Title : 파일 업로드 프로그레스 상태 호출  
	Description : 기능 호출후 callback 처리시 컨트롤러에서 받은 파일
					   업로드 진행상태를 JSON String으로 돌려줌					  
	Author : 박인성		
 --%>   
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	response.setHeader("Cache-Control", "no-cache");
	request.setCharacterEncoding("UTF-8");
	
	int percent = 0;	//업로드 percent
	long bytesRead = 0;	//읽어들인 바이트
	long contentLength = 0;	//전체 바이트
	double kbps = 0;
	if(session==null){
		percent = 0;
		bytesRead = 0;
		contentLength = 0;
		kbps = 0;
	}else{
		percent = (Integer)session.getAttribute("percent");
		bytesRead = (Long)session.getAttribute("bytesread");
		contentLength = (Long)session.getAttribute("contentlength");
		kbps = (Double)session.getAttribute("kbps");
	}
	
	StringBuffer retJsonStr = new StringBuffer();
	retJsonStr.append("{percent:"+percent+",bytesread:"+bytesRead+",contentlength:"+contentLength+",kbps:"+kbps+"}");
	System.out.println("upload progress status JSON String : " + retJsonStr.toString());
	out.println(retJsonStr.toString());
%>