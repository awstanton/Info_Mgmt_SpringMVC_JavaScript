
document.addEventListener("keydown", handler, false);

function handler(event) {
    if(event.ctrlKey && event.altKey) {
        if (event.key === "e") {
//            alert("toggle mode");
            var element1 = document.getElementById("section1");
            var element2 = document.getElementById("section2");
            var element3 = document.getElementById("section3");
            var element4 = document.getElementById("section4");
            element1.classList.toggle("displayNone");
            element2.classList.toggle("displayNone");
            element3.classList.toggle("displayNone");
            element4.classList.toggle("displayNone");
        }
        else if (event.key === "r") {
            alert("redo");
        }
        else if (event.key === "u") {
            alert("undo");
        }
    }
}
