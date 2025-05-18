<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.user.panel.title"/></title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            margin: 0;
            padding: 40px;
            color: #2c3e50;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 40px;
        }

        .title {
            display: flex;
            align-items: center;
            font-size: 26px;
            font-weight: bold;
        }

        .title span {
            margin-left: 10px;
        }

        .lang-buttons {
            display: flex;
            gap: 10px;
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

        h2 {
            font-size: 24px;
            text-align: center;
            margin-bottom: 25px;
        }

        .search-area {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
            margin-bottom: 10px;
        }

        .search-area input[type="text"] {
            width: 60%;
            padding: 12px 16px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }

        .search-area button {
            padding: 12px 20px;
            background-color: #2c3e50;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
        }

        .search-area button:hover {
            background-color: #1a252f;
        }

        .clear-btn {
            background-color: transparent;
            border: none;
            font-size: 20px;
            color: #888;
            cursor: pointer;
            margin-left: -10px;
            margin-right: 10px;
        }

        .hint {
            margin-top: 10px;
            font-size: 13px;
            opacity: 0.65;
            text-align: center;
        }

        .result-count {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #555;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        th {
            background-color: #eeeeee;
        }

        .back-button-container {
            margin-top: 40px;
            display: flex;
            justify-content: center;
        }

        .back-button {
            background-color: #2c3e50;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 10px;
            font-size: 15px;
            cursor: pointer;
        }

        .back-button:hover {
            background-color: #1a252f;
        }
    </style>
</head>
<body>

<header>
    <div class="title">
        📞 <span><spring:message code="label.app.name"/></span>
    </div>
    <div class="lang-buttons">
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="tr"/>
            <button type="submit">🇹🇷 <spring:message code="label.language.tr"/></button>
        </form>
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="en"/>
            <button type="submit">🇬🇧 <spring:message code="label.language.en"/></button>
        </form>
    </div>
</header>

<h2><spring:message code="label.search.title"/></h2>

<form class="search-area" action="/kullanici/ara" method="get">
    <input type="text" name="arama" id="aramaInput"
           value="${fn:escapeXml(param.arama)}"
           placeholder="<spring:message code='label.search.placeholder'/>"/>

    <c:if test="${not empty param.arama}">
        <button type="button" class="clear-btn" onclick="document.getElementById('aramaInput').value=''; this.form.submit();">✖</button>
    </c:if>

    <button type="submit"><spring:message code="label.search.button"/></button>
</form>

<div class="hint"><spring:message code="label.search.hint"/></div>

<c:choose>
    <c:when test="${not empty sonucListesi}">
        <div class="result-count">
            <spring:message code="label.result.count" arguments="${fn:length(sonucListesi)}"/>
        </div>
        <table>
            <thead>
            <tr>
                <th><spring:message code="label.firstname"/></th>
                <th><spring:message code="label.lastname"/></th>
                <th><spring:message code="label.faculty.name"/></th>
                <th><spring:message code="label.department.name"/></th>
                <th><spring:message code="label.phone"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="personel" items="${sonucListesi}">
                <tr>
                    <td>${personel.ad}</td>
                    <td>${personel.soyad}</td>
                    <td>${personel.fakulteAd}</td>
                    <td>${personel.bolumAd}</td>
                    <td>${personel.telefon}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty param.arama}">
            <div class="hint"><spring:message code="label.no.results"/></div>
        </c:if>
    </c:otherwise>
</c:choose>

<div class="back-button-container">
    <form action="/admin/login" method="get">
    <button type="submit" class="back-button">
    ← <spring:message code="label.back.to.login"/>
</button>

    </form>
</div>

</body>
</html>
