<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>

			<div id="main">

				<h2>タスク情報の修正</h2>
				<html:form action="/modifyTask">
					<html:errors />
					<p>
						<label>タスク名</label>
						<html:text property="title" size="50" />
						<label>優先度</label>
						<html:select property="priority">
							<html:optionsCollection property="priorities" value="value" label="priorityName" />
						</html:select>
						<label>期限</label>
						<html:text property="period" size="10" styleId="period" readonly="true" />
						<label>見積時間</label>
						<html:text property="estimatedTime" size="5" />
						<html:hidden property="id" /><%-- タスクID --%>
					</p>
					<p>
						<html:submit value="修正" styleClass="button" />
						<html:cancel value="キャンセル" styleClass="button" />
					</p>
				</html:form>

			</div>