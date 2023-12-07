<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新・削除画面</title>
<style>
  body {
  text-align:center;
   background-image:url("washi-pattern-03.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  padding-top:100px;
  }
  table {
  margin: 0 auto;
  background-color:white;
  }
</style>
</head>
<body>
<h1>ユーザー情報更新</h1>
  <form action="InsertServlet"method="post">
  <table border="1">
     <tr><th>ID:<input type="text"name="ID"></th></tr>
     <tr><th>名前:<input type="text"name="name"></th></tr>
     <tr><th>身長:<input type="text"name="height">ｃｍ</th></tr>
     <tr><th>体重:<input type="text"name="weight">ｋｇ</th></tr>
  </table>
  <br>
  <input type="submit"name="btn"value="更新">
  </form>
<h2>ユーザー情報削除</h2>
  <form action="InsertServlet"method="post">
  <table border="1">
     <tr><th>ID:<input type="text"name="ID"></th></tr>
  </table>
  <br>
  <input type="submit"name="btn"value="削除">
  </form>
  <br>
<a href="InsertServlet">ユーザー情報登録画面へ</a>

</body>
</html>