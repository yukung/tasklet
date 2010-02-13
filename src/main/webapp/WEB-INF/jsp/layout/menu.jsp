<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
		<!-- menu -->
		<div id="menu">
			<ul>
				<c:if test="${empty sessionScope.user}">
				<li id="current"><html:link action="/index">ホーム</html:link></li>
				</c:if>
				<c:if test="${not empty sessionScope.user}">
				<li id="current"><html:link action="/index">ホーム</html:link></li>
				</c:if>
				<li><html:link href="#">設定</html:link></li>
				<li><html:link href="#">ヘルプ</html:link></li>
				<c:if test="${not empty sessionScope.user}">
				<li class="last"><html:link action="/index">ログアウト</html:link></li>
				</c:if>
			</ul>
		</div>