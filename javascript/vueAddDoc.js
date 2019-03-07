window.onload = function() {
	var l = document.querySelector("#content div.list div.noScroll");
	var h = l.offsetHeight-1;
	if (h > 300)
		l.style.height = "301px";
	else if (h > 150)
		l.style.height = "151px";
	else if (h > 50)
		l.style.height = "101px";
	else
		l.style.height = "51px";
};