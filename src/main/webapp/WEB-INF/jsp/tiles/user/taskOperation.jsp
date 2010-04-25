<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="sidebar">

				<h3>タスクの操作</h3>
				<ul class="sidemenu">
					<li>タスクを並び替える</li>
					<li><html:link action="/toggle" paramId="activityId" paramName="activity" paramProperty="id"><c:choose><c:when test="${showsCompleted}">完了タスクを表示する</c:when><c:otherwise>完了タスクを表示しない</c:otherwise></c:choose></html:link></li>
					<li>他のアクティビティへ</li>
				</ul>

			</div>