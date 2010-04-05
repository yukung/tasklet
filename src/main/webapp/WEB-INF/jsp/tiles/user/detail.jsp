<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2><c:out value="${task.title}" /></h2>
				<h3>優先度</h3>
				<p>
				<c:choose>
					<c:when test="${task.priority == 'HIGH'}">
						<span class="attention"><c:out value="${task.priority.priorityName}" /></span>
					</c:when>
					<c:otherwise>
						<c:out value="${task.priority.priorityName}" />
					</c:otherwise>
				</c:choose>
				</p>
				<h3>状態</h3>
				<p><c:out value="${task.status.statusName}" /></p>
				<h3>期限／完了日</h3>
				<p>
					<c:choose>
						<c:when test="${task.status == 'FINISH'}">
							<fmt:formatDate value="${task.finishedOn}" pattern="yyyy/MM/dd" />
						</c:when>
						<c:otherwise>
							<c:if test="${task.overdue}">
								<span class="attention"><fmt:formatDate value="${task.period}" pattern="yyyy/MM/dd" /></span>
								<strong class="attention">（期日を過ぎています！！）</strong>
							</c:if>
							<c:if test="${!task.overdue}">
								<fmt:formatDate value="${task.period}" pattern="yyyy/MM/dd" />まで（あと<c:out value="${task.daysRemaining}" />日）
							</c:if>
						</c:otherwise>
					</c:choose>
				</p>
				<h3>見積時間</h3>
				<p><c:out value="${task.estimatedTime}" />時間</p>
				<h3>現在までの実績</h3>
				<p><c:out value="${task.actualTime}" />時間</p>
				<h3>メモ</h3>
				<c:if test="${not empty task.memos}">
				<ul>
					<c:forEach var="memo" items="${task.memos}" varStatus="status">
					<li><c:out value="${memo.contents}" /></li>
					</c:forEach>
				</ul>
				</c:if>

				<html:form action="/update">
					<html:errors />
					<p>
						<label>今回の実績時間（現在までの実績に加算されます）</label>
						<html:text property="actualTime" size="5" />
						<label>メモの追加</label>
						<html:textarea property="contents" />
						<html:hidden property="taskId" />
					</p>
					<p>
						<html:submit value="更新" styleClass="button" />
						<html:cancel value="キャンセル" styleClass="button" />
					</p>
				</html:form>

			</div>