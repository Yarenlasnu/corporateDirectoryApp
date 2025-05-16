<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.login.title"/></title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        header {
            position: absolute;
            top: 20px;
            right: 30px;
        }

        .lang-buttons {
            display: flex;
            gap: 10px;
        }

        .lang-buttons form {
            display: inline;
        }

        .lang-buttons button {
            background-color: #2c3e50;
            color: #ffffff;
            padding: 8px 14px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
        }

        .lang-buttons button:hover {
            background-color: #1a252f;
        }

        .login-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 360px;
            text-align: center;
        }

        h2 {
            margin-bottom: 30px;
            color: #2c3e50;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #2c3e50;
            font-weight: 500;
            text-align: left;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        button:hover {
            background-color: #1a252f;
        }

        .alt-buton {
            background-color: #ccc;
            color: #2c3e50;
        }

        .alt-buton:hover {
            background-color: #bbb;
        }

        .error-box {
            background-color: #f8d7da;
            color: #842029;
            border: 1px solid #f5c2c7;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 14px;
        }

        .error-box span {
            margin-left: 8px;
        }
    </style>
</head>
<body>

<header>
    <div class="lang-buttons">
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="tr"/>
            <button type="submit">🇹🇷 TR</button>
        </form>
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="en"/>
            <button type="submit">🇬🇧 EN</button>
        </form>
    </div>
</header>

<div class="login-container">
    <h2><spring:message code="label.login.title"/></h2>

    <c:if test="${param.error == 'true'}">
        <div class="error-box">
            ⚠️ <span><spring:message code="label.login.error"/></span>
        </div>
    </c:if>

    <form action="/admin/login" method="post">
        <label for="username"><spring:message code="label.username"/></label>
        <input type="text" id="username" name="username" required />

        <label for="password"><spring:message code="label.password"/></label>
        <input type="password" id="password" name="password" required />

        <button type="submit"><spring:message code="label.login.button"/></button>
    </form>

    <form action="/kullanici/panel" method="get">
        <button type="submit" class="alt-buton">
            <spring:message code="label.login.continue"/>
        </button>
    </form>
</div>

</body>
</html>
