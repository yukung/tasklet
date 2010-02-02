<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>

			<div id="main">

				<h2><bean:write name="title" />のメモ</h2>
				<logic:notEmpty name="memos">
				<ul>
				<logic:iterate id="memo" name="memos" indexId="idx" scope="request">
					<li><bean:write name="memo" property="contents" /></li>
				</logic:iterate>
				</ul>
				</logic:notEmpty>

				<h3>メモの追加</h3>
				<form action="#">
					<p>
						<textarea></textarea>
					</p>
					<p>
						<input type="submit" value="追加" class="button" />
						<input type="submit" value="キャンセル" class="button" />
					</p>
				</form>

			</div>