<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<html:errors />
				<h2>カテゴリの編集</h2>

				<html:form action="/operateCategory">
				<p>
					<html:select property="id" styleId="category" onchange="enable()">
						<option selected>以下より選択してください</option>
						<html:optionsCollection property="categories" value="key" label="value" />
					</html:select>
					を<html:text property="name" styleId="name" onkeypress="enable()" />
					<html:submit property="rename" value="名前変更" styleId="rename" styleClass="button" disabled="true" />
					<html:submit property="delete" value="削除" styleId="delete" styleClass="button" disabled="true" />
				</p>
				</html:form>

			</div>