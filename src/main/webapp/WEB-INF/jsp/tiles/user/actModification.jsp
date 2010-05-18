<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<html:errors />
				<h2>アクティビティの修正</h2>

				<html:form action="/modifyActivity.do">
				<p>
					<label>アクティビティ名</label>
					<html:text property="title" />
					<html:hidden property="activityId" />
					<label>カテゴリ名</label>
					<html:select property="categoryId">
						<html:optionsCollection property="categories" value="key" label="value" />
					</html:select>
				</p>
				<p>
					<html:submit value="変更" styleClass="button" />
					<html:cancel value="キャンセル" styleClass="button" />
				</p>
				</html:form>

			</div>