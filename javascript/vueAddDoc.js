window.onload = function() {
	var l = document.querySelector("#content div.list div.noScroll");
	if (l.offsetHeight > 200)
		l.style.height = "200px";
};