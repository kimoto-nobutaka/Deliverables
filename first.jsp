<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.*" %>
<jsp:useBean id="hdto" scope="request" class="bean.HealthDTO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報登録</title>
<style>
  body {
  	text-align:center;
  	background-image:url("tesukiwashi-pattern-04.jpg");
  	background-size: cover;
  	background-repeat: no-repeat;
  }
  table {
  	margin: 0 auto;
  	background-color:white;
  }
  
  .btn {
  	margin:0 auto;
   
      
   }
   .btn form {
	display: inline-block;
 	margin:0;
  	padding-top:20px
       
   }
   .btn input[type="submit"] {
   	width:150px;
   	height:50px;
   	font-size:20px;
   	background-color:#87ceeb;
    border: none;
    border-radius: 5px;
    cursor: pointer;
   }
   h1 {
   	margin-top:100px;
   }

</style>
</head>
<body>
<h1>BMI管理</h1>
<br>
<div class="btn">
 <form action="entry.jsp" method="get">
	<input type="submit"name="btn"value="新規登録">
   </form>
<form action="update.jsp" method="get">
	<input type="submit" value="更新">

</form>
<form action="target.jsp" method="get">
	<input type="submit" value="目標BMI設定">
</form>
<form action="delete.jsp" method="get">
	<input type="submit" value="削除">
</form>
</div>


<h2>ユーザーリスト</h2>
<table border="1">
  <tr>
    <th width="50">ID</th>
    <th width="200">名前</th>
    <th width="50">身長</th>
    <th width="50">体重</th>
    <th width="50">BMI</th>
    <th width="50">目標BMI</th>
    <th width="100">目標体重まで</th>
  </tr>
  <%
    for(int i=0;i<hdto.size();i++){
    	HealthBean hb = hdto.get(i);
  %>
  <tr>
    <td align="center"><%=hb.getId() %></td>
    <td align="center"><%=hb.getName() %></td>
    <td align="center"><%=hb.getHeight() %>cm</td>
    <td align="center"><%=hb.getWeight() %>kg</td>
    <td align="center"><%=hb.getBmi() %></td>
    <td align="center"><%=hb.getTargetBmi() %></td>
    <td align="center"><%=hb.getTargetWeight() %>kg</td>
  </tr>
  <% } %>
</table>



</body>
</html>