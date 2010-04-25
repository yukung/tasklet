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