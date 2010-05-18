<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="sidebar">

				<h3>タスク情報の更新</h3>
				<p>各項目を入力してタスクの情報を更新してください。</p>
				<ul class="sidemenu">
					<li>実績時間
						<ul>
							<li>0～24で入力</li>
							<li>-の値を入力すると訂正できます。（現在までの実績以上に減らすと0になります。）</li>
						</ul>
					</li>
					<li>完了チェック
						<ul>
							<li>チェックを入れると状態を完了にできます。</li>
							<li>チェックを入れないと着手状態に戻ります。</li>
						</ul>
					</li>
				</ul>
				<h3>タスクの操作</h3>
				<ul class="sidemenu">
					<li><html:link action="/modify" paramId="id" paramName="task" paramProperty="id">タスク情報の修正</html:link></li>
				</ul>

			</div>
