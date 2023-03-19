<%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 16.03.2023
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <style>
        body {
            width: 100vw; /* Full width */
            height: 100vh; /* Full height */
            background-color: #AAA;
            box-sizing: border-box;
            font-family: sans-serif;
        }
        .formWrapper {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 40%;
        }
        .formWrapper > form {
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-direction: column;
        }
        .inputWrapper {
            display: flex;
            flex-direction: row;
            justify-content: space-evenly;
            align-items: start;
            height: 100px;
        }
        .left {
            text-align: left;
            display: flex;
            flex-direction: column;
            justify-content: start;
        }
        .left > span {
            display: block;
            width: 100px;
            height: 15px;
            background-color: #b4b4b4;
            padding: 5px;
            margin: 1px;
        }
        .right {
            display: flex;
            flex-direction: column;
            height: 75px;
        }
        .right > input {
            width: 150px;
            height: 25px;
            padding: 5px;
            margin: 1px;
        }
        #createBtn {
            padding: 5px;
            background-color: #c5c5c5;
            margin: 0 auto;
            cursor: pointer;
            text-align: center;
        }
        #createBtn:active {
            background-color: cornflowerblue;
        }
        #createWindow {
            display: none;
            z-index: 1;
            background-color: rgb(187, 187, 187);
            background-color: rgba(0,0,0,0.4);
        }
        .rememberWrapper {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: white;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<% if ((boolean)request.getAttribute("isLoggedIn")) { %>
<div id="logout">
    <button>LOGOUT</button>
</div>
<% } else { %>
<div id="login" class="formWrapper">
    <form action="authorization?action=submit" method='POST'>
        <div class="inputWrapper">
            <div class="left">
                <span>login:</span>
                <span>password:</span>
            </div>
            <div class="right">
                <input name='login'/>
                <input name='password' type='password'/>
                <input type='submit' value='Отправить'/>
            </div>
        </div>
    </form>
    <p id="createBtn">Create account</p>
</div>

<div id="createWindow" class="formWrapper">
    <span class="close">&times;</span>
    <form action="authorization?action=submit" method='POST'>
        <div class="inputWrapper">
            <div class="left">
                <span>login:</span>
                <span>password:</span>
                <span>E-mail:</span>
            </div>
            <div class="right">
                <input name='login'/>
                <input name='password' type='password'/>
                <input name='email'/>
                <input type='submit' value='Отправить'/>
            </div>
        </div>
    </form>
</div>
<% } %>
</body>
<script>
    let login = document.getElementById("login");
    let modal = document.getElementById("createWindow");
    let btn = document.getElementById("createBtn");
    let span = document.getElementsByClassName("close")[0];
    btn.onclick = function() {
        modal.style.display = "block";
        login.style.display = "none";
    }
    span.onclick = function() {
        modal.style.display = "none";
        login.style.display = "block";
    }
</script>
</html>
