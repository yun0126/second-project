// When the user scrolls the page, execute myFunction
window.onscroll = function() {
	myFunction()
};

// Get the header
var header = document.getElementById("navbar");
var classification = document.getElementById("classification");

// Add the sticky class to the header when you reach its scroll position. Remove "sticky" when you leave the scroll position
function myFunction() {
	if (header) {
		if (window.scrollY > 150) {
			header.classList.add("sticky");
			if (classification) {
				classification.classList.add("stickyCategory");
			}


		} else {
			header.classList.remove("sticky");
			if (classification) {
				classification.classList.remove("stickyCategory");
			}


		}
	}
}