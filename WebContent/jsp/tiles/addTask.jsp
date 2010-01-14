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
							<html:optionsCollection name="showTaskForm" property="priorities" value="value" label="priorityName"/>
						</html:select>
						<label>期限</label>
						<html:text property="period" />
						<label>見積時間</label>
						<html:text property="estimatedTime" />
					</p>
					<p>
						<html:submit value="追加" styleClass="button" />
						<html:cancel value="キャンセル" styleClass="button" />
					</p>
				</html:form>

			</div>