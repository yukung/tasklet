<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>設定変更</h2>

				<html:form action="/config">
					<html:errors />
					<p>
						<label>ユーザー名（*）</label>
						<html:text property="userName" maxlength="30" disabled="true"/>
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
						<html:submit value="変更" styleClass="button" />
						<html:reset value="リセット" styleClass="button" />
					</p>
				</html:form>

				<h2>カテゴリ管理</h2>
				<p>ここになんか</p>

			</div>