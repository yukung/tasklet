<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="sidebar">

				<h3>タスクの操作</h3>
				<ul class="sidemenu">
					<li>タスクを並び替える</li>
					<li><html:link action="/toggle" paramId="activityId" paramName="activity" paramProperty="id"><c:choose><c:when test="${onlyIncompleted}">完了タスクを表示する</c:when><c:otherwise>完了タスクを表示しない</c:otherwise></c:choose></html:link></li>
					<li>他のリストへ
						<ul>
							<li>
					<select onChange="jumpMenu('parent',this,0)">
						<option selected="">下記より選択してください</option>
						<c:forEach var="activityId" items="${activity.moreActivities}" varStatus="status">
						<option value="/tasklet/user/tasks.do?activityId=${activityId['activity_id']}">${activityId['title']}</option>
						</c:forEach>
					</select>
							</li>
						</ul>
					</li>
				</ul>

			</div>