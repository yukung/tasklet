<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html:html xhtml="true">
	<head>
		<meta name="Description" content="あなたのタスク管理をもっと簡単にします。シンプルなToDo管理サービスです。" />
		<meta name="Keywords" content="task, ToDo, GTD, simple" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="./css/style.css" type="text/css" />

		<title><tiles:getAsString name="title" /></title>
	</head>

	<body>
	<!-- wrap starts here -->
	<div id="wrap">

		<!-- header -->
		<tiles:insert attribute="header" />

		<!-- menu -->
		<tiles:insert attribute="menu" />

		<!-- content-wrap starts here -->
		<div id="content-wrap">

			<tiles:insert attribute="sidebar" />

			<tiles:insert attribute="main" />

		<!-- content-wrap ends here -->
		</div>

		<!-- footer starts here -->
		<tiles:insert attribute="footer" />

	<!-- wrap ends here -->
	</div>

	</body>
</html:html>