<%@ page contentType="text/html;charset=Shift_JIS"
	import="javax.sql.*,javax.naming.*,java.sql.*" %>
<html>
<head>
<title>�f�[�^�\�[�X�e�X�g</title>
</head>
<body>


�f�[�^�\�[�X���g�p�����ڑ��e�X�g<br>

<%
// JNDI���\�[�X��������l�[�~���O����̂��߂�
// InitalContext�I�u�W�F�N�ginitCon�𐶐����܂�
Context ctx=new InitialContext();
// lookup���\�b�h���g�p���AJNDI���\�[�X�������܂��B
// �����ɂ�java:comp/env/�𑊑΃p�X�Ƃ���JNDI���\�[�X�����w�肵�܂��B
DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/tasklet");
// JNDI���\�[�X�փR�l�N�g���s���܂�
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
