<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>

<html:xhtml/>
<div id="menu">
	<ul>
		<li id="current"><html:link action="/activity">ホーム</html:link></li>
		<li>Menu1</li>
		<li>Menu2</li>
		<li>Menu3</li>
		<li>Menu4</li>
		<li class="last">Menu5</li>
	</ul>
</div>