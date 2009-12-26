<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<html:xhtml/>
		<!-- menu -->
		<div id="menu">
			<ul>
				<logic:empty name="user" scope="session">
				<li id="current"><html:link action="/index.do">ホーム</html:link></li>
				</logic:empty>
				<logic:notEmpty name="user" scope="session">
				<li id="current"><html:link action="/showActivities.do">ホーム</html:link></li>
				</logic:notEmpty>
				<li><html:link href="#">設定</html:link></li>
				<li><html:link href="#">ヘルプ</html:link></li>
				<logic:notEmpty name="user" scope="session">
				<li class="last"><html:link action="/logout">ログアウト</html:link></li>
				</logic:notEmpty>

			</ul>
		</div>