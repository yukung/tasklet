function enable() {
	var index = document.getElementById("category").selectedIndex;
	if (index == 0) {
		document.getElementById("name").disabled = true;
		document.getElementById("rename").setAttribute("disabled", "disabled");
		document.getElementById("delete").setAttribute("disabled", "disabled");
	} else {
		document.getElementById("name").disabled = false;
		document.getElementById("rename").removeAttribute("disabled");
		document.getElementById("delete").removeAttribute("disabled");
	}
}