<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>
				<c:if test="${empty sessionScope.user.displayName}">
					<c:out value="${sessionScope.user.userName}" />さんのアクティビティ
				</c:if>
				<c:if test="${not empty sessionScope.user.displayName}">
					<c:out value="${sessionScope.user.displayName}" />さんのアクティビティ
				</c:if>
				</h2>

				<html:messages id="msg" message="true">
				<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<c:if test="${not empty activities}">
				<table>
					<tr>
						<th class="first">アクティビティ</th>
						<th>カテゴリ</th>
						<th>達成率</th>
						<th>残数</th>
						<th>超過数</th>
						<th>予実比</th>
						<th>見積時関計</th>
						<th>実績時関計</th>
					</tr>
					<c:forEach var="activity" items="${activities}" varStatus="status">
					<tr ${status.index % 2 == 0 ? "class\"row-a\"" : "class=\"row-b\""}>
						<td class="first">
							<html:link action="/tasks" paramId="activityId" paramName="activity" paramProperty="id"><c:out value="${activity.title}"></c:out></html:link>
						</td>
						<td class="align-center"><c:out value="${activity.categoryName}" /></td>
						<td class="align-right"><c:out value="${activity.achievementRatio}" /></td>
						<td class="align-right"><c:out value="${activity.remainingAmount}" /></td>
						<td class="align-right">
						<c:if test="${activity.overdue > 0}">
							<span class="attention"><c:out value="${activity.overdue}" /></span>
						</c:if>
						<c:if test="${activity.overdue == 0}">
							<c:out value="${activity.overdue}" />
						</c:if>
						</td>
						<td class="align-right"><c:out value="${activity.ratioOfEstimateAndActual}" /></td>
						<td class="align-right"><c:out value="${activity.estimatedTimeTotal}" /></td>
						<td class="align-right"><c:out value="${activity.actualTimeTotal}" /></td>
					</tr>
					</c:forEach>
				</table>
				</c:if>

				<c:if test="${not empty pager}">
					<c:if test="${pager.count > 10}">
					<div class="pager align-right">
						<c:if test="${pager.prev}">
							<html:link action="/pagination" paramId="pageNo" paramName="pager" paramProperty="prevPageNo" styleClass="page">&lt;&lt;前</html:link>
						</c:if>
						<c:if test="${pager.next}">
							<html:link action="/pagination" paramId="pageNo" paramName="pager" paramProperty="nextPageNo" styleClass="page">次&gt;&gt;</html:link>
						</c:if>
					</div>
					</c:if>
				</c:if>

				<h3>新規追加</h3>

				<html:form action="/addActivity">
					<p>
						<label>アクティビティ名</label>
						<html:text property="title" value="" />
						<html:submit value="追加" styleClass="button" />
					</p>
					<html:errors />
				</html:form>

			</div>