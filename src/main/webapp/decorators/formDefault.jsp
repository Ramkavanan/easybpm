<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<link rel="icon" type="image/x-icon" href="/images/favicon.ico?v=2">
	<link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico?v=2">
	<title><decorator:title /> | <fmt:message key="webapp.name" /></title>

<decorator:head />
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<decorator:body />
	<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>
</body>
</html>