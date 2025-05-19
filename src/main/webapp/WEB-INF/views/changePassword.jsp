<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.change.password.title"/></title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
            width: 360px;
        }

        h2 {
            margin-bottom: 20px;
            color: #2c3e50;
        }

        input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        .msg {
            margin-bottom: 15px;
            font-size: 14px;
        }

        .msg.error {
            color: red;
        }

        .msg.success {
            color: green;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 5px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1a252f;
        }

        .alt-button {
            background-color: #ccc;
            color: #2c3e50;
            font-weight: bold;
        }

        .alt-button:hover {
            background-color: #bbb;
        }

        header {
            position: absolute;
            top: 20px;
            right: 30px;
        }
    </style>
</head>
<body>

<header>
    <div style="display: flex; gap: 10px;">
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

<div class="container">
    <h2><spring:message code="label.change.password.title"/></h2>

    <c:if test="${not empty error}">
        <div class="msg error">${error}</div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="msg success">${success}</div>
    </c:if>

    <form action="/admin/change-password" method="post">
        <input type="password" name="currentPassword" placeholder="<spring:message code='label.current.password'/>" required />
        <input type="password" name="newPassword" placeholder="<spring:message code='label.new.password'/>" required />
        <input type="password" name="confirmPassword" placeholder="<spring:message code='label.new.password.repeat'/>" required />
        <button type="submit"><spring:message code="label.update.password"/></button>
    </form>

    <form action="/admin/panel" method="get">
        <button type="submit" class="alt-button">🔙 <spring:message code="label.back"/></button>
    </form>
</div>

</body>
</html>
