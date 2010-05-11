<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>アクティビティの並び替え</h2>

				<html:messages id="msg" message="true">
				<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<c:if test="${not empty activities}">
					<html:form action="/sort">
					<ul id="sortList">
						<c:forEach var="activity" items="${activities}" varStatus="status">
						<li id="${activity.id}"><c:out value="${activity.title}"></c:out></li>
						</c:forEach>
					</ul>
					<p>
						<html:hidden property="sortId" value="" styleId="sortId"/>
						<html:submit value="並び順を確定" styleClass="button" />
					</p>
					</html:form>
				</c:if>


			</div>