<%@ page contentType="text/html; charset=UTF-8" %>
<html:xhtml/>
			<div id="main">

				<h2>${userInfo.userId}さんのアクティビティ</h2>

				<table>
					<tr>
						<th class="first">アクティビティ</th>
						<th>達成率</th>
						<th>残数</th>
						<th>超過数</th>
						<th>予実比</th>
						<th>見積計</th>
						<th>実績計</th>
					</tr>
					<tr class="row-a">
						<td class="first"><a href="#">HTMLの作成</a></td>
						<td>30%</td>
						<td>7</td>
						<td>0</td>
						<td>85%</td>
						<td>10</td>
						<td>8.5</td>
					</tr>
					<tr class="row-b">
						<td class="first"><a href="#">プロジェクト管理ツールの導入</a></td>
						<td>30%</td>
						<td>7</td>
						<td>0</td>
						<td>85%</td>
						<td>10</td>
						<td>8.5</td>
					</tr>
					<tr class="row-a">
						<td class="first"><a href="#">ソースコード管理システムの導入</a></td>
						<td>30%</td>
						<td>7</td>
						<td>0</td>
						<td>85%</td>
						<td>10</td>
						<td>8.5</td>
					</tr>
					<tr class="row-b">
						<td class="first"><a href="#">開発環境の構築</a></td>
						<td>30%</td>
						<td>7</td>
						<td>0</td>
						<td>85%</td>
						<td>10</td>
						<td>8.5</td>
					</tr>
				</table>

				<h3>新規アクティビティ</h3>
				<%--
				<html:form action="/addActivity">
					<p>
						<label>アクティビティ</label>
						<html:text property="activity" />
						<html:submit value="追加" styleClass="button" />
					</p>
				</html:form>
				 --%>

			</div>