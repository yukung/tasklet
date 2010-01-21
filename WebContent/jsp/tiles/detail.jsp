<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2><bean:write name="task" property="title" scope="request"/></h2>
				<nested:root name="task">
				<h3>優先度</h3>
				<p>
					<nested:equal property="priority" value="HIGH">
						<span class="attention"><nested:nest property="priority"><nested:write property="priorityName" /></nested:nest></span>
					</nested:equal>
					<nested:notEqual property="priority" value="HIGH">
						<nested:nest property="priority"><nested:write property="priorityName" /></nested:nest>
					</nested:notEqual>
				</p>
				<h3>状態</h3>
				<p><nested:nest property="status"><nested:write property="statusName" /></nested:nest></p>
				</nested:root>
				<h3>期限／完了日</h3>
				<p>
					<logic:notEqual name="task" property="status" value="FINISH">
					<logic:match name="task" property="overdue" value="true">
					<span class="attention"><bean:write name="task" property="period" format="yyyy/MM/dd" scope="request"/></span>まで<br>
					<strong class="attention">（期日を過ぎています！！）</strong>
					</logic:match>
					<logic:match name="task" property="overdue" value="false">
					<bean:write name="task" property="period" format="yyyy/MM/dd" scope="request" />まで（あと<bean:write name="task" property="daysRemaining" />日）
					</logic:match>
					</logic:notEqual>
					<logic:equal name="task" property="status" value="FINISH">
						<bean:write name="task" property="finishedOn" format="yyyy/MM/dd" />
					</logic:equal>
				</p>
				<h3>見積時間</h3>
				<p><bean:write name="task" property="estimatedTime" scope="request" />時間</p>
				<h3>現在までの実績</h3>
				<p><bean:write name="task" property="actualTime" scope="request" />時間</p>
				<h3>メモ</h3>
				<nested:root name="task">
				<nested:notEmpty property="allMemos">
				<ul>
					<nested:iterate id="memo" property="allMemos">
					<li><nested:write property="contents" /></li>
					</nested:iterate>
				</ul>
				</nested:notEmpty>
				</nested:root>

				<form action="#">
					<p>
						<label>今回の実績時間（現在までの実績に加算されます）</label>
						<input type="text" value="" />
						<label>メモの追加</label>
						<textarea></textarea>
					</p>
					<p>
						<input type="submit" value="更新" class="button" />
						<input type="submit" value="キャンセル" class="button" />
					</p>
				</form>

			</div>