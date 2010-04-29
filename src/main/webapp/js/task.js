/**
 * 全てのチェックボックスの選択状態をトグルします。
 * @param toggle
 * @return
 */
function selectAll(toggle) {
	var array = document.getElementsByName("checked");
	if (toggle) {
		for ( var i = 0; i < array.length; i++) {
			array[i].checked = true;
		}
	} else {
		for ( var i = 0; i < array.length; i++) {
			array[i].checked = false;
		}
	}
}

/**
 * 他のリストへジャンプします。
 * @param target ウィンドウ
 * @param selectObj 選択したリスト
 * @param restore 元の位置
 * @return
 */
function jumpMenu(target, selectObj, restore) {
	eval(target + ".location='" + selectObj.options[selectObj.selectedIndex].value + "'");
	if (restore) {
		selectObj.selectedIndex = 0;
	}
}