<%@ page contentType="text/html; charset=UTF-8" %>
		<link rev="stylesheet" href="/tasklet/css/redmond/jquery-ui-1.7.2.custom.css" type="text/css" />
		<script type="text/javascript" src="/tasklet/js/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="/tasklet/js/jquery-ui-1.7.2.custom.js"></script>
		<script type="text/javascript">
<!--
$(function() {
	// sortableのオプション設定
	var option = {
		// 並べ替え可能な要素
		items: 'li',
		// 動かせる方向
		axis: 'y',
		// カーソル指定
		cursor: 'move',
		// 並び替え終了後に位置が入れ替わっていたら実行される関数
		update: function() {
			var data =[];
			$("li","#sortList").each(function(i,v){
				data.push(v.id);
			});
			$("#sortId").val(data.toString());
			$("#submit").removeAttr("disabled");
		}
	};
	// sortable実行
	$('#sortList').sortable(option);
});

-->
		</script>