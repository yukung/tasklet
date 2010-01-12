<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2><bean:write name="showTaskForm" property="title" scope="request" /></h2>

				<form>

					<p>
						<label>タスク操作</label>
						<input type="submit" value="完了" class="button" />
						<input type="submit" value="削除" class="button" />
					</p>

					<table>
						<tr>
							<th class="first"></th>
							<th>タスク</th>
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
							<td class="first align-center"><input type="checkbox" name="check" /></td>
							<td><a href="#"><nested:write property="title" /></a></td>
							<td class="align-center">
							<nested:nest property="priority">
								<nested:write property="priorityName" />
							</nested:nest>
							</td>
							<td class="align-center">
							<nested:nest property="status">
								<nested:write property="statusName" />
							</nested:nest>
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
							<td class="align-center"><a href="#">1</a></td>
						</tr>
						</nested:root>
						</logic:iterate>
					</table>
					<p><a href="#">タスクの追加</a></p>

				</form>

			</div>