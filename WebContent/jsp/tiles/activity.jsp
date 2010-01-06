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
						<td class="first"><bean:write name="activity" property="title" /></td>
						<td><bean:write name="activity" property="category" /></td>
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