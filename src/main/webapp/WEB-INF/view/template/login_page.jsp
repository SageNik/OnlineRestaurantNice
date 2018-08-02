<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${not empty title}">
            ${title}
        </c:if>
    </title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/static/css/style.css"/>">
</head>
<body>
<div id="header">
    <tail:insertAttribute name="header"/>
</div>
<div>

    <div id="content" >
        <tail:insertAttribute name="body"/>
    </div>
</div>
<div id="footer">
    <tail:insertAttribute name="footer"/>
</div>
</body>
</html>
