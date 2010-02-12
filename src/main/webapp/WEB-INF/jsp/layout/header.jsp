<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
		<!-- header -->
		<div id="header">

			<h1 id="logo-text">
			<logic:empty name="user" scope="session"><html:link action="/index">Tasklet</html:link></logic:empty>
			<logic:notEmpty name="user" scope="session"><html:link action="/showActivities.do">Tasklet</html:link></logic:notEmpty>
			</h1>
			<p id="slogan">your ToDo, more easy!</p>

			<div id="header-links">
			<p>
				<logic:empty name="user" scope="session"><html:link action="/index">ホーム</html:link></logic:empty>
				<logic:notEmpty name="user" scope="session"><html:link action="/showActivities.do">ホーム</html:link></logic:notEmpty>
				 |
				Contact |
				Site Map
			</p>
			</div>

		</div>