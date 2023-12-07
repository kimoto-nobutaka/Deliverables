<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>目標BMI設定</title>
<style>
  body {
  text-align:center;
   background-image:url("washi-pattern-01.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  padding-top:200px;
  }
  table {
  margin: 0 auto;
  background-color:white;
  }
</style>
</head>
<body>
<h1>目標BMI設定</h1>
  <form action="InsertServlet"method="post">
  <table border="1">
     <tr><th>ID:<input type="text"name="ID"></th></tr>
     <tr><th>目標BMI:<input type="text"name="target"></th></tr>
  </table>
  <br>
  <input type="submit"name="btn"value="設定">
  </form>

  <br>
<a href="InsertServlet">ユーザー情報登録画面へ</a>

</body>
</html>