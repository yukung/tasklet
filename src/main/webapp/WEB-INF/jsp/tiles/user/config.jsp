<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>設定変更</h2>

					<html:form action="/updateAccount">
					<html:messages id="msg" message="true">
						<h3 class="message"><c:out value="${msg}" /></h3>
					</html:messages>
					<html:errors />
					<p>
						<label>ユーザー名（*）</label>
						<html:text property="userName" maxlength="30" readonly="true" styleClass="disabled"/>
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
				<form>
					<p>
					<label>新規カテゴリ名</label>
					<input type="text">
					<input type="submit" class="button" value="カテゴリ追加">
					</p>
					<p><html:link href="#">既存カテゴリの編集</html:link></p>
				</form>


			</div>