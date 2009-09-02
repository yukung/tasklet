<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:xhtml/>
<div id="sidebar">

	<h3>ログイン</h3>
	<html:form action="/login" styleClass="searchform">
		<ul class="sidemenu">
			<li>ユーザーID</li>
			<li><html:text property="userId" styleClass="textbox"></html:text></li>
			<li>パスワード</li>
			<li><html:password property="password" styleClass="textbox"></html:password></li>
		</ul>
		<html:submit value="ログイン" styleClass="button" />
	</html:form>

	<h3>新規登録</h3>
	<p>新規登録はこちらから</p>

	<h3>Links</h3>
	<ul class="sidemenu">
		<li><html:link href="http://d.hatena.ne.jp/yukung/">開発者ブログ</html:link></li>
		<li><html:link href="http://twitter.com/yukung/">Twitter</html:link></li>
	</ul>

</div>