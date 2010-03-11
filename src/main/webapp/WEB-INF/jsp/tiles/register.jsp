<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>新規登録</h2>

				<html:form action="/register">
					<html:errors />
					<p>
						<label>ユーザー名（*）</label>
						<html:text property="userName" maxlength="30" />
						<label>ニックネーム</label>
						<html:text property="displayName" maxlength="20" />
						<label>メールアドレス（*）</label>
						<html:text property="email" maxlength="255" />
						<label>パスワード（*）</label>
						<html:password property="password" redisplay="false" maxlength="30" />
						<label>パスワード確認（*）</label>
						<html:password property="confirm" redisplay="false" maxlength="30" />
					</p>
					<p>
						<html:submit value="登録" styleClass="button" />
						<html:reset value="リセット" styleClass="button" />
					</p>
				</html:form>

			</div>