<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html:html xhtml="true">
	<head>
		<meta name="Description" content="あなたのタスク管理をもっと簡単にします。シンプルなToDo管理ツールです。" />
		<meta name="Keywords" content="task, ToDo, GTD, simple" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="../../css/style.css" type="text/css" />
		<title><tiles:getAsString name="title" /></title>
	</head>

	<body>
	<!-- wrap starts here -->
	<div id="wrap">
		<tiles:insert attribute="header" />
		<tiles:insert attribute="menu" />

		<!-- content-wrap starts here -->
		<div id="content-wrap">
			<tiles:insert attribute="sidebar" />
			<tiles:insert attribute="main" />

		<!--  content-wrap ends here -->
		</div>

		<tiles:insert attribute="footer" />

	<!-- wrap ends here -->
	</div>
	</body>
</html:html>