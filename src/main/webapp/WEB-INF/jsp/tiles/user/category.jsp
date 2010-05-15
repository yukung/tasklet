<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<html:errors />
				<h2>カテゴリの編集</h2>

				<html:form action="/modifyCategory">
				<p>
					<html:select property="id">
						<option selected>以下より選択してください</option>
						<html:optionsCollection property="categories" value="key" label="value" />
					</html:select>
					を<html:text property="name" />
					<html:submit value="名前変更" styleClass="button" />
					<html:submit value="削除" styleClass="button" />
				</p>
				</html:form>

			</div>