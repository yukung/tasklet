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
							<th>見積時間（h）</th>
							<th>実績時間（h）</th>
							<th>メモ</th>
						</tr>
						<logic:iterate id="task" name="tasks" indexId="idx" scope="request">
						<nested:root name="task">
						<tr ${idx % 2 == 0 ? "class=\"row-a\"" : "class=\"row-b\""}>
							<td class="first"><input type="checkbox" name="check" /></td>
							<td><a href="#"><nested:write property="title" /></a></td>
							<td><nested:write property="priority" /></td>
							<nested:nest property="status">
							<td><nested:write property="statusName" /></td>
							</nested:nest>
							<td>
							<nested:empty property="finishedOn">
								<nested:write property="period" />
							</nested:empty>
							<nested:notEmpty property="finishedOn">
								<nested:write property="finishedOn" />
							</nested:notEmpty>
							</td>
							<td><nested:write property="estimatedTime" /></td>
							<td><nested:write property="actualTime" /></td>
							<td><a href="#">1</a></td>
						</tr>
						</nested:root>
						</logic:iterate>
					</table>
					<p><a href="#">タスクの追加</a></p>

				</form>

			</div>