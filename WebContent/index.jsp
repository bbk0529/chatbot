<%@page import="controllers.WatsonConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style>
@import url('<c:url value="/resources/css/bubbles.css" />');
</style>
<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"> </script>
<script type="text/javascript">

$(function(){
	
	
	$('#chatList').append('<p class="triangle-right left">' + '소피입니다. 팀과 선수와 관련된 질문을 해 주세요. <br/> 1. 선수  : [선수이름] 프로필, "선수 프로필" 입력시 전체 선수 명단 확인, 선수명은 한/영 가능  <br/>	2. 경기일정  : [year]년 [month]월 경기' + '</p>');
	$("#msg").keypress(function (e) {
	        if (e.which == 13){
	        	if ($("#msg").val()=='') {
	        		alert('값을 입력하셔야됩니다.');
	        		$('#chatList').scrollTop($('#chatList').prop('scrollHeight'));
	        	} else {
	        	   	$('#chatList').append('<p class="triangle-right right">' + $("#msg").val()  + '</p>');
	        	   	$('#chatList').scrollTop($('#chatList').prop('scrollHeight'));
	        		$.post("./controller/coordi.jsp",{msg:$('#msg').val()})
					.done(function(data) {
								$('#chatList').append('<p class="triangle-right left">' + data + '</p>' );
								$('#chatList').append('<p class="triangle-right left">' + '다른 궁금하신 내용이 있으시면 말씀해주세요' + '</p>');
								$('#chatList').scrollTop($('#chatList').prop('scrollHeight'));
					});
					$('#msg').val('');
					$('#chatList').scrollTop($('#chatList').prop('scrollHeight'));
	        	}
	        }
	    });
});

</script>
<meta charset="UTF-8">
<title> WATSON CONVERSATION </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
	<div class="jumbotron" style="padding:10px 50px 10px 50px;margin:0px;">
	    <h1>Sports Oriented Friendly Intelligence</h1>
	       <p>The Premier League is an English professional league for men's association football clubs. 
	    At the top of the English football league system, it is the country's primary football competition.</p>  
	</div>
	</div>
	<div class="row" >
		<div class="col-sm-12" >
			<p>
			
			</p>
		</div>
	</div>
	<div id="container" class="row" style="background-image:url('./resources/img/banner.jpg');background-size:cover;">
			<div class="col-sm-1" style="background-color:rgba(70,70,70,0.7);height:500px"></div>
			<div class="col-sm-1" style="background-color:rgba(80,80,80,0.7);height:500px"></div>
			<div id="chatList" class="col-sm-10" style="background-color:rgba(100,100,100,0.7);height:500px;overflow:auto;);"></div>
			<div class="col-sm-12" style="background-color:rgba(10,10,10,0.9);">
				<input type="text" id="msg" class="triangle-border center" autofocus> 
			</div>
	</div>
</div>
</body>
</html>