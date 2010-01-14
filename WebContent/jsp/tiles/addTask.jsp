<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>新規タスクの追加</h2>
				<html:form action="/addTask">
					<p>
						<label>タスク名</label>
						<html:text property="title" />
						<label>優先度</label>
						<html:select name="showTaskForm" property="priority">
							<html:optionsCollection name="showTaskForm" property="priorityList" value="priority" label="priorityName"/>
						</html:select>
						<label>期限</label>
						<input type="text" value="" />
						<label>見積時間</label>
						<input type="text" value="" />
					</p>
					<p>
						<input type="submit" value="追加" class="button" />
						<input type="submit" value="キャンセル" class="button" />
					</p>
				</html:form>

			</div>