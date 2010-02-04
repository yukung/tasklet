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
				<html:form action="/addMemo">
					<p>
						<html:textarea property="contents"></html:textarea>
						<html:hidden property="taskId" />
					</p>
					<p>
						<html:submit value="追加" styleClass="button" />
						<html:cancel value="キャンセル" styleClass="button" />
					</p>
				</html:form>

			</div>