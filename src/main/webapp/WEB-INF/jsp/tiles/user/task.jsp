<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2><c:out value="${activity.title}"></c:out></h2>
				<html:form action="/operate">
				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
					<p id="add-link"><html:link action="/entry" paramId="activityId" paramName="activity" paramProperty="id">タスクの追加</html:link></p>
					<c:if test="${not empty tasks}">
					<p>
						<label>タスク操作</label>
						<html:hidden property="activityId" value="${activity.id}" />
						<html:submit property="complete" value="完了" styleClass="button" />
						<html:submit property="delete" value="削除" styleClass="button" />
					</p>

					<table>
						<tr>
							<th class="first align-center"><input type="checkbox" onclick="selectAll(this.checked);" /></th>
							<th class="task">タスク</th>
							<th>優先度</th>
							<th>状態</th>
							<th>期限/完了</th>
							<th>見積時間</th>
							<th>実績時間</th>
							<th>メモ</th>
						</tr>
						<c:forEach var="task" items="${tasks}" varStatus="status">
						<c:choose>
						<c:when test="${onlyIncompleted}">
						<c:if test="${task.status != 'FINISH'}">
						<tr ${status.index % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
							<td class="first align-center">
								<c:if test="${task.status != 'FINISH'}"><html:multibox property="checked" value="${task.id}"/></c:if>
							</td>
							<td><html:link action="/detail" paramId="id" paramName="task" paramProperty="id"><c:out value="${task.title}"></c:out></html:link></td>
							<td class="align-center">
							<c:choose>
								<c:when test="${task.priority == 'HIGH'}">
									<span class="attention"><c:out value="${task.priority.priorityName}" /></span>
								</c:when>
								<c:when test="${task.priority == 'NOTHING'}"><%-- 何も出力しない --%></c:when>
								<c:otherwise>
									<c:out value="${task.priority.priorityName}" />
								</c:otherwise>
							</c:choose>
							</td>
							<td class="align-center"><c:out value="${task.status.statusName}" /></td>
							<td class="align-right">
							<c:choose>
								<c:when test="${task.status == 'FINISH'}">
									<fmt:formatDate value="${task.finishedOn}" />
								</c:when>
								<c:otherwise>
									<c:if test="${task.overdue}">
										<span class="attention"><fmt:formatDate value="${task.period}" /></span>
									</c:if>
									<c:if test="${!task.overdue}">
										<fmt:formatDate value="${task.period}" />
									</c:if>
								</c:otherwise>
							</c:choose>
							</td>
							<td class="align-right"><c:out value="${task.estimatedTime}" /></td>
							<td class="align-right"><c:out value="${task.actualTime}" /></td>
							<td class="align-center">
								<c:if test="${task.memoCount != 0}">
									<html:link action="/memos" paramId="taskId" paramName="task" paramProperty="id"><c:out value="${task.memoCount}" /></html:link>
								</c:if>
							</td>
						</tr>
						</c:if>
						</c:when>
						<c:otherwise>
						<tr ${status.index % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
							<td class="first align-center">
								<c:if test="${task.status != 'FINISH'}"><html:multibox property="checked" value="${task.id}"/></c:if>
							</td>
							<td><html:link action="/detail" paramId="id" paramName="task" paramProperty="id"><c:out value="${task.title}"></c:out></html:link></td>
							<td class="align-center">
							<c:choose>
								<c:when test="${task.priority == 'HIGH'}">
									<span class="attention"><c:out value="${task.priority.priorityName}" /></span>
								</c:when>
								<c:when test="${task.priority == 'NOTHING'}"><%-- 何も出力しない --%></c:when>
								<c:otherwise>
									<c:out value="${task.priority.priorityName}" />
								</c:otherwise>
							</c:choose>
							</td>
							<td class="align-center"><c:out value="${task.status.statusName}" /></td>
							<td class="align-right">
							<c:choose>
								<c:when test="${task.status == 'FINISH'}">
									<fmt:formatDate value="${task.finishedOn}" />
								</c:when>
								<c:otherwise>
									<c:if test="${task.overdue}">
										<span class="attention"><fmt:formatDate value="${task.period}" /></span>
									</c:if>
									<c:if test="${!task.overdue}">
										<fmt:formatDate value="${task.period}" />
									</c:if>
								</c:otherwise>
							</c:choose>
							</td>
							<td class="align-right"><c:out value="${task.estimatedTime}" /></td>
							<td class="align-right"><c:out value="${task.actualTime}" /></td>
							<td class="align-center">
								<c:if test="${task.memoCount != 0}">
									<html:link action="/memos" paramId="taskId" paramName="task" paramProperty="id"><c:out value="${task.memoCount}" /></html:link>
								</c:if>
							</td>
						</tr>
						</c:otherwise>
						</c:choose>
						</c:forEach>
					</table>
					</c:if>
				</html:form>

			</div>