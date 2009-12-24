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
						<th>達成率</th>
						<th>残数</th>
						<th>超過数</th>
						<th>予実比</th>
						<th>見積計</th>
						<th>実績計</th>
					</tr>
					<logic:iterate id="activity" name="activities" indexId="idx" scope="request">
					<tr ${idx % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
						<td class="first"><bean:write name="activity" property="title" /></td>
						<td>30%</td>
						<td>7</td>
						<td>0</td>
						<td>85%</td>
						<td>10</td>
						<td>8.5</td>
					</tr>
					</logic:iterate>
				</table>

				<h3>新規アクティビティ</h3>
				<%--
				<html:form action="/addActivity">
					<p>
						<label>アクティビティ</label>
						<html:text property="activity" />
						<html:submit value="追加" styleClass="button" />
					</p>
				</html:form>
				 --%>

			</div>