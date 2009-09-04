<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:directive.include file="/jsp/includes/taglibs.jsp" />
<html:xhtml/>
			<div id="sidebar">

				<h3>ユーザー登録について</h3>
				<ul class="sidemenu">
					<li>姓</li>
					<li>名</li>
					<li>ユーザー名（必須）
						<ul>
							<li>2文字以上</li>
						</ul>
					</li>
					<li>携帯メールアドレス
						<ul>
							<li>携帯から利用する場合は入力</li>
						</ul>
					</li>
					<li>パスワード（必須）
						<ul>
							<li>6文字以上</li>
						</ul>
					</li>
					<li>パスワードの確認
						<ul>
							<li>同じパスワードをもう一度入力</li>
						</ul>
					</li>
				</ul>
				<p>ユーザー情報は、登録後も設定画面で変更できます。</p>

			</div>