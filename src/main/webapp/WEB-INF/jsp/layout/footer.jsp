<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
		<!-- footer starts here -->
		<div id="footer">
			<p>
			&copy; 2010 <strong>yukung</strong>
			Design by: <html:link href="http://www.styleshout.com/">styleshout</html:link> |
			Valid <html:link href="http://validator.w3.org/check?uri=referer">XHTML</html:link> |
			<html:link href="http://jigsaw.w3.org/css-validator/check/referer">CSS</html:link>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${empty sessionScope.user}"><html:link module="/" action="/index">ホーム</html:link>&nbsp;|&nbsp;</c:if>
			<c:if test="${not empty sessionScope.user}"><html:link action="/activities">ホーム</html:link>&nbsp;|&nbsp;</c:if>
			<html:link href="http://d.hatena.ne.jp/yukung/" target="_blank">開発者ブログ</html:link>&nbsp;|&nbsp;
			<html:link href="http://twitter.com/yukung/" target="_blank">Twitter</html:link>
			</p>
		</div>