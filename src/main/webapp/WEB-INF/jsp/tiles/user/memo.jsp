<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>

			<div id="main">

				<h2><c:out value="${task.title}"/>&nbsp;のメモ</h2>
				<c:if test="${not empty memos}">
				<ul>
				<c:forEach var="memo" items="${memos}" varStatus="status">
					<li><c:out value="${memo.contents}"/><span class="timestamp">（<fmt:formatDate value="${memo.createdOn}" pattern="yyyy/MM/dd HH:mm:ss" />）</span></li>
				</c:forEach>
				</ul>
				</c:if>

				<h3>メモの追加</h3>
				<html:form action="/addMemo">
					<html:errors/>
					<p>
						<html:textarea property="contents" />
						<html:hidden property="taskId" value="${task.id}"/>
					</p>
					<p>
						<html:submit value="追加" styleClass="button" />
						<html:cancel value="キャンセル" styleClass="button" />
					</p>
				</html:form>

			</div>