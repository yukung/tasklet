<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<html:errors />
				<h2>設定変更</h2>

				<html:form action="/updateAccount" styleClass="configform">
				<p>
					<label>ユーザー名（*）</label>
					<html:text property="userName" maxlength="30" readonly="true" styleClass="disabled" />
					<label>ニックネーム</label>
					<html:text property="displayName" maxlength="20" />
					<label>メールアドレス（*）</label>
					<html:text property="email" maxlength="255" />
					<label>古いパスワード（*）</label>
					<html:password property="origin" redisplay="false" maxlength="30" />
					<label>新パスワード（*）</label>
					<html:password property="password" redisplay="false" maxlength="30" />
					<label>新パスワード確認（*）</label>
					<html:password property="confirm" redisplay="false" maxlength="30" />
				</p>
				<p>
					<html:submit value="変更" styleClass="button" />
					<html:reset value="リセット" styleClass="button" />
				</p>
				</html:form>

				<h2>カテゴリ管理</h2>
				<html:form action="/addCategory">
					<p>
					<label>新規カテゴリ名</label>
					<html:text property="name" maxlength="255" />
					<html:submit value="カテゴリ追加" styleClass="button" />
					</p>
					<p><html:link href="#">既存カテゴリの編集</html:link></p>
				</html:form>

			</div>