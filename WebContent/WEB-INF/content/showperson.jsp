<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>person list</title>
    </head>
    <body>
        <table>
            <tr>
                <th>姓名</th>
                <th>编码</th>
                <th>ID</th>
                <th>总数</th>
            </tr>
            <c:forEach items="${orders}" var="ord">
                <tr>
                    <td>${ord.articles }</td>
                    <td>${ord.code }</td>
                    <td>${ord.id }</td>
                    <td>${ord.total }</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>