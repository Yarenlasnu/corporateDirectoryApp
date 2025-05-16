<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="label.admin.panel.title"/></title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .lang-buttons {
            display: flex;
            gap: 10px;
        }

        .lang-buttons form {
            display: inline;
        }

        .form-row {
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            margin-bottom: 30px;
        }

        .section {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 6px rgba(0,0,0,0.1);
            padding: 20px;
            flex: 1;
            min-width: 320px;
        }

        h2 {
            color: #2c3e50;
            font-size: 22px;
            margin-bottom: 15px;
        }

        input, select {
            padding: 10px;
            border-radius: 5px;
            width: 100%;
            margin-top: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
        }

        button {
            background-color: #2c3e50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1a252f;
        }

        .icon-button {
            background: none;
            border: none;
            cursor: pointer;
            color: #c0392b;
            font-size: 18px;
        }

        .icon-button:hover {
            color: #e74c3c;
        }

        table {
            width: 100%;
            margin-top: 15px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

<header>
    <h1><spring:message code="label.admin.panel.title"/></h1>
    <div class="lang-buttons">
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="tr"/>
            <button type="submit">🇹🇷 TR</button>
        </form>
        <form action="/changeLanguage" method="get">
            <input type="hidden" name="lang" value="en"/>
            <button type="submit">🇬🇧 EN</button>
        </form>
        <form action="/logout" method="post">
            <button type="submit"><spring:message code="label.logout"/></button>
        </form>
    </div>
</header>

<div class="form-row">
    <div class="section">
        <h2><spring:message code="label.add.faculty"/></h2>
        <form action="/admin/fakulte/ekle" method="post">
            <input type="text" name="ad" placeholder="<spring:message code='label.faculty.name'/>" required/>
            <button type="submit"><spring:message code="label.add.button"/></button>
        </form>
        <table>
            <tr><th>Ad</th><th>İşlem</th></tr>
            <c:forEach var="f" items="${fakulteListesi}">
                <tr>
                    <td>${f.ad}</td>
                    <td>
                        <form method="post" action="/admin/fakulte/sil">
                            <input type="hidden" name="id" value="${f.id}" />
                            <button class="icon-button">❌</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="section">
        <h2><spring:message code="label.add.department"/></h2>
        <form action="/admin/bolum/ekle" method="post">
            <label><spring:message code="label.select.faculty"/></label>
            <select name="fakulteId" required>
                <c:forEach var="fakulte" items="${fakulteListesi}">
                    <option value="${fakulte.id}">${fakulte.ad}</option>
                </c:forEach>
            </select>
            <input type="text" name="ad" placeholder="<spring:message code='label.department.name'/>" required/>
            <button type="submit"><spring:message code="label.add.button"/></button>
        </form>
        <table>
            <tr><th>Ad</th><th>İşlem</th></tr>
            <c:forEach var="b" items="${bolumListesi}">
                <tr>
                    <td>${b.ad}</td>
                    <td>
                        <form method="post" action="/admin/bolum/sil">
                            <input type="hidden" name="id" value="${b.id}" />
                            <button class="icon-button">❌</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="section">
    <h2><spring:message code="label.add.personnel"/></h2>
    <form action="/admin/personel/ekle" method="post">
        <label><spring:message code="label.select.faculty"/></label>
        <select name="fakulteId" id="fakulteDropdown">
            <c:forEach var="fakulte" items="${fakulteListesi}">
                <option value="${fakulte.id}">${fakulte.ad}</option>
            </c:forEach>
        </select>

        <label><spring:message code="label.select.department"/></label>
        <select name="bolumId" id="bolumDropdown">
            <c:forEach var="bolum" items="${bolumListesi}">
                <option value="${bolum.id}">${bolum.ad}</option>
            </c:forEach>
        </select>

        <input type="text" name="ad" placeholder="<spring:message code='label.firstname'/>" required/>
        <input type="text" name="soyad" placeholder="<spring:message code='label.lastname'/>" required/>
        <input type="text" name="telefon" placeholder="<spring:message code='label.phone.placeholder'/>" required/>
        <button type="submit"><spring:message code="label.add.button"/></button>
    </form>

    <table>
        <tr>
            <th>Ad</th><th>Soyad</th><th>Telefon</th><th>İşlem</th>
        </tr>
        <c:forEach var="p" items="${personelListesi}">
            <tr>
                <td>${p.ad}</td>
                <td>${p.soyad}</td>
                <td>${p.telefon}</td>
                <td>
                    <form method="post" action="/admin/personel/sil">
                        <input type="hidden" name="id" value="${p.id}" />
                        <button class="icon-button">❌</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
