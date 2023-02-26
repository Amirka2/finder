<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.FileModel" %>
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

  <%}else{%>

  <tr>
    <td><img src="https://icon-library.com/images/png-file-icon/png-file-icon-6.jpg" style="width: 40px"/></td>
    <td><a style="text-decoration: none;" href="./download/?path=<%=request.getAttribute("path")%>/<%=fileModel.getName()%>"><%=fileModel.getName()%></a></td>
    <td class="size"><%=fileModel.getLength()%> B</td>
    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy, hh:mm:ss a").format(new Date(fileModel.getLastModified())) %></td>
  </tr>
  <%}
  }%>
</table>
</body>
</html>