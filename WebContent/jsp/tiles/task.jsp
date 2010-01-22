<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2><bean:write name="activity" property="title" scope="request" /></h2>

				<form>

				<html:messages id="msg" message="true">
					<h3 class="message"><bean:write name="msg" /></h3>
				</html:messages>
					<p id="add-link"><html:link action="/entry" paramId="activityId" paramName="activity" paramProperty="id">タスクの追加</html:link></p>
					<logic:notEmpty name="tasks" scope="request">
					<p>
						<label>タスク操作</label>
						<input type="submit" value="完了" class="button" />
						<input type="submit" value="削除" class="button" />
					</p>

					<table>
						<tr>
							<th class="first"></th>
							<th class="task">タスク</th>
							<th>優先度</th>
							<th>状態</th>
							<th>期限/完了</th>
							<th>見積時間</th>
							<th>実績時間</th>
							<th>メモ</th>
						</tr>
						<logic:iterate id="task" name="tasks" indexId="idx" scope="request">
						<nested:root name="task">
						<tr ${idx % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
							<td class="first align-center"><input type="checkbox"></input></td>
							<td><html:link action="/showDetail" paramId="taskId" paramName="task" paramProperty="id"><nested:write property="title" /></html:link></td>
							<td class="align-center">
							<nested:equal property="priority" value="HIGH">
								<span class="attention"><nested:nest property="priority"><nested:write property="priorityName" /></nested:nest></span>
							</nested:equal>
							<nested:notEqual property="priority" value="HIGH">
								<nested:notEqual property="priority" value="NOTHING"><nested:nest property="priority"><nested:write property="priorityName" /></nested:nest></nested:notEqual>
							</nested:notEqual>
							</td>
							<td class="align-center">
								<nested:nest property="status"><nested:write property="statusName" /></nested:nest>
							</td>
							<td class="align-right">
							<nested:notEqual property="status" value="FINISH">
								<nested:match property="overdue" value="true">
								<span class="attention"><nested:write property="period" format="yyyy/MM/dd" /></span>
								</nested:match>
								<nested:match property="overdue" value="false">
								<nested:write property="period" format="yyyy/MM/dd" />
								</nested:match>
							</nested:notEqual>
							<nested:equal property="status" value="FINISH">
								<nested:write property="finishedOn" format="yyyy/MM/dd" />
							</nested:equal>
							</td>
							<td class="align-right"><nested:write property="estimatedTime" /></td>
							<td class="align-right"><nested:write property="actualTime" /></td>
							<td class="align-center">
							<nested:notEqual property="memoCount" value="0">
								<a href="#"><nested:write property="memoCount" /></a>
							</nested:notEqual>
							</td>
						</tr>
						</nested:root>
						</logic:iterate>
					</table>
					</logic:notEmpty>

				</form>

			</div>