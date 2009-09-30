<%@ page contentType="text/html;charset=Shift_JIS"
	import="javax.sql.*,javax.naming.*,java.sql.*" %>
<html>
<head>
<title>データソーステスト</title>
</head>
<body>


データソースを使用した接続テスト<br>

<%
// JNDIリソースを見つけるネーミング操作のための
// InitalContextオブジェクトinitConを生成します
Context ctx=new InitialContext();
// lookupメソッドを使用し、JNDIリソースを見つけます。
// 引数にはjava:comp/env/を相対パスとしたJNDIリソース名を指定します。
DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/tasklet");
// JNDIリソースへコネクトを行います
Connection dsCon=ds.getConnection();
Statement stmt=dsCon.createStatement();
ResultSet rs = stmt.executeQuery("select * from tl_users;");
String str = null;
while(rs.next()) {
	str = rs.getString("user_id");
}
stmt.close();
dsCon.close();
%>
<%= str %>
</body>
</html>
