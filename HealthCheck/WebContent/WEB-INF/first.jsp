<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

</style>
</head>
<body>
<h1>BMI管理</h1>
<br>

<h2>ユーザー情報登録</h2>
  <form action="InsertServlet"method="post">  
  <table border="1">
  
    <tr><th>名前:<input type="text"name="name"></th></tr>
    <tr><th>身長:<input type="text"name="height">ｃｍ</th></tr>
    <tr><th>体重:<input type="text"name="weight">ｋｇ</th></tr>
  
  </table>
  <br>
<input type="submit"name="btn"value="登録">
<input type="submit"name="btn"value="一覧">
   </form>
<br>
<a href="update.jsp">ユーザー情報更新・削除画面へ</a>
<br>
<a href="target.jsp">目標BMI設定画面へ</a>
</body>
</html>