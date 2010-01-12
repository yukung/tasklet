<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>
				<logic:empty name="user" property="displayName" scope="session">
				<bean:write name="user" property="userName" scope="session" />さんのアクティビティ
				</logic:empty>
				<logic:notEmpty name="user" property="displayName" scope="session">
				<bean:write name="user" property="displayName" scope="session" />さんのアクティビティ
				</logic:notEmpty>
				</h2>

				<table>
					<tr>
						<th class="first">アクティビティ</th>
						<th>カテゴリ</th>
						<th>達成率</th>
						<th>残数</th>
						<th>超過数</th>
						<th>予実比</th>
						<th>見積時間計</th>
						<th>実績時間計</th>
					</tr>
					<logic:iterate id="activity" name="activities" indexId="idx" scope="request">
					<tr ${idx % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
						<td class="first"><html:link action="/showTasks.do" paramId="activityId" paramName="activity" paramProperty="id"><bean:write name="activity" property="title" /></html:link></td>
						<td class="align-center"><bean:write name="activity" property="category" /></td>
						<td class="align-right"><bean:write name="activity" property="achievementRatio" /></td>
						<td class="align-right"><bean:write name="activity" property="remaining" /></td>
						<logic:greaterThan name="activity" property="remaining" value="0">
						<td class="align-right attention"><bean:write name="activity" property="overdue" /></td>
						</logic:greaterThan>
						<logic:equal name="activity" property="remaining" value="0">
						<td class="align-right"><bean:write name="activity" property="overdue" /></td>
						</logic:equal>
						<td class="align-right"><bean:write name="activity" property="ratioOfEstimateAndActual" /></td>
						<td class="align-right"><bean:write name="activity" property="estimatedTimeTotal"/></td>
						<td class="align-right"><bean:write name="activity" property="actualTimeTotal"/></td>
					</tr>
					</logic:iterate>
				</table>

				<logic:notEmpty name="pager" scope="request">
				<div class="pager align-right">
					<logic:equal name="pager" property="prev" value="true" scope="request">
						<html:link action="/activitiesPagination" paramId="pageNo" paramName="pager" paramProperty="prevPageNo" paramScope="request" styleClass="page">&lt;&lt;前</html:link>
					</logic:equal>
					<logic:equal name="pager" property="next" value="true" scope="request">
						<html:link action="/activitiesPagination" paramId="pageNo" paramName="pager" paramProperty="nextPageNo" paramScope="request" styleClass="page">次&gt;&gt;</html:link>
					</logic:equal>
				</div>
				</logic:notEmpty>

				<h3>新規追加</h3>

				<html:form action="/addActivity">
					<p>
						<label>アクティビティ名</label>
						<html:text property="activityName" />
						<html:submit value="追加" styleClass="button" />
					</p>
					<html:errors />
				</html:form>

			</div>