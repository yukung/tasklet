<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<html:messages id="msg" message="true">
					<h3 class="message"><c:out value="${msg}" /></h3>
				</html:messages>
				<html:errors />
				<h2>カテゴリの編集</h2>

				<form>
				<p>
					<select>
						<option>aaaa</option>
						<option>bbbb</option>
					</select>
					<input type="hidden">
					<input type="text">
					<input type="submit" value="名前変更">
					<input type="submit" value="削除">
				</p>
				</form>

			</div>