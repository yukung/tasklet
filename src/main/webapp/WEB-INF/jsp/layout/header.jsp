<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
		<!-- header -->
		<div id="header">

			<h1 id="logo-text">
			<c:if test="${empty sessionScope.user}"><html:link action="/index">Tasklet</html:link></c:if>
			<c:if test="${not empty sessionScope.user}"><html:link action="/index">Tasklet</html:link></c:if>
			</h1>
			<p id="slogan">your ToDo, more easy!</p>

			<div id="header-links">
			<p>
				<c:if test="${empty sessionScope.user}"><html:link action="/index">ホーム</html:link></c:if>
				<c:if test="${not empty sessionScope.user}"><html:link action="/index">ホーム</html:link></c:if>
				 | Contact | Site Map
			</p>
			</div>

		</div>