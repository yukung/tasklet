<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
		<!-- menu -->
		<div id="menu">
			<ul>
				<c:if test="${empty sessionScope.user}">
				<li id="current"><html:link module="/" action="/index">ホーム</html:link></li>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
				<li id="current"><html:link action="/activities">ホーム</html:link></li>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
				<li><html:link action="/config">設定</html:link></li>
				</c:if>
				<li><html:link action="/help">ヘルプ</html:link></li>
				<c:if test="${not empty sessionScope.user}">
				<li class="last"><html:link action="/logout">ログアウト</html:link></li>
				</c:if>
			</ul>
		</div>