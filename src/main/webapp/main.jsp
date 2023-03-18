<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.example.model.FileModel" %>
<%@ page import="static java.util.Arrays.stream" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Space+Mono:wght@700&display=swap" rel="stylesheet">
  <title>Document</title>
  <style>
    * {
      font-family: 'Space Mono', monospace;
    }
    a {
      text-decoration: none;
      color: black;
      padding: 5px;
    }
    a:hover {
      background-color: #009eff;
      color: white;
    }
    .path {
      border: 1px solid black;
      margin-bottom: 10px;
    }
    .size {
      color: #009eff;
    }
  </style>
</head>
<body>
<%= new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()) %>
<div class="path">
  <h1> Директория: ${path} </h1>
  <p><%
//    stream(request.getCookies()).filter((c) -> c.getName() == "login").limit(1);
  %></p>
  <a id="logoutBtn" href="/authorization">Log out</a>
</div>
<a style="display: inline-block; height: 30px; border: 1px solid black" href="./?path=${requestScope.path.substring(0, requestScope.path.lastIndexOf('/'))}"><img src="https://w7.pngwing.com/pngs/138/63/png-transparent-computer-icons-arrow-back-icon-angle-logo-symbol-thumbnail.png" style="height: 30px"/> Назад</a>
<br>
<br>
<table>
  <tr>
    <th></th>
    <th>Файл</th>
    <th>Размер</th>
    <th>Дата</th>
  </tr>
  <%
    ArrayList<FileModel> list = (ArrayList<FileModel>)request.getAttribute("content");
    for(FileModel fileModel: list)
    {
      if(fileModel.isDirectory()){
  %>
  <tr>
    <td><img src="https://icons-for-free.com/download-icon-folder+icon-1320191242863903371_512.png" style="width: 40px"/></td>
    <td><a style="text-decoration: none;" href="./?path=<%=request.getAttribute("path")%>/<%=fileModel.getName()%>"><%=fileModel.getName()%>/</a></td>
    <td></td>
    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a").format(new Date(fileModel.getLastModified())) %></td>
  </tr>

  <%} else {%>

  <tr>
    <td><img src="https://icon-library.com/images/png-file-icon/png-file-icon-6.jpg" style="width: 40px"/></td>
    <td><a style="text-decoration: none;" href="./download/?path=<%=request.getAttribute("path")%>/<%=fileModel.getName()%>"><%=fileModel.getName()%></a></td>
    <td class="size"><%=fileModel.getLength()%> B</td>
    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a").format(new Date(fileModel.getLastModified())) %></td>
  </tr>
  <%}
  }%>
</table>
<script>
  function cookiesDelete() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
      var cookie = cookies[i];
      var eqPos = cookie.indexOf("=");
      var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;";
      document.cookie = name + '=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }
  }
  let btn = document.getElementById('logoutBtn');
  btn.onclick = () => {
    cookiesDelete();
    console.log(document.cookie);
    document.location.href = "http://127.0.0.1:8080/authorization"
  }
</script>
</body>
</html>