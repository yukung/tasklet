<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>新規登録</h2>
				<html:form action="/registerUser">
					<p>(*)は必須入力項目です。</p>
					<html:errors />
					<p>
						<label>ユーザー名(*)</label>
						<html:text property="userName" />
						<label>ニックネーム</label>
						<html:text property="displayName" />
						<label>メールアドレス</label>
						<html:text property="email" />
						<label>パスワード(*)</label>
						<html:password property="password" />
						<label>パスワード確認(*)</label>
						<html:password property="confirm" />
					</p>
					<p>
						<html:submit value="登録" styleClass="button" />
						<html:reset value="リセット" styleClass="button" />
					</p>
				</html:form>
			</div>