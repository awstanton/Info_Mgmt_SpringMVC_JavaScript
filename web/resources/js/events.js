
var listElements = document.querySelectorAll('a');

//for (let j = 0; j < listElements.length; j++) {
//    listElements[j].onkeydown = changeEditability;
//}

var listElementsArray = [];

for (let k = 0; k < listElements.length; k++) {
    let element = {};
    element.id = listElements[k].id;
    element.name = listElements[k].textContent;
    listElementsArray[k] = element;
}

//var listElementsArray = Array.prototype.slice.call(listElements);

// ISSUE IS THAT THE OBJECTS ALL REFER TO SSAME THINGS, NEED TO FIGURE OUT WAY AROUND THIS!


//for (let j = 0; j < listElementsArray.length; j++) {
//    console.log(listElementsArray[j].innerHTML);
//}

document.addEventListener('keydown', changeEditability);

function changeEditability(e) {
    if (e.keyCode === 17) {
        if (listElements.length > 0) {
        
            if (listElements[0].contentEditable === "false") {
                for (let i = 0; i < listElements.length; i++) {
                    listElements[i].contentEditable = "true";
                    document.getElementById("delbtn" + listElements[i].id).type = "submit";
                }
            }
            else {
                const form = document.createElement('form');
                    form.method = 'post';
                    form.action = '/SimpleSpringMVC/updateListNames';
                for (let i = 0; i < listElements.length; i++) {
                    listElements[i].contentEditable = "false";
                    document.getElementById("delbtn" + listElements[i].id).type = "hidden";

                    if (listElements[i].textContent != listElementsArray[i].name) {

                        var listName = document.createElement('input');
                        listName.type = 'hidden';
                        listName.form = form;
                        listName.name = listElementsArray[i].id;
                        listName.value = listElements[i].innerHTML; // this is correct; this gets the updated name

                        form.appendChild(listName);
                    }
                }
                document.body.appendChild(form);
                console.log("childElementCount = " + form.childElementCount);

                if (form.childElementCount > 0)
                    form.submit();
            }
        }
        let addbtn = document.getElementById('addbtn');
        let addbox = document.getElementById('addbox');
        if (addbtn.type === "hidden")
            addbtn.type = "submit";
        else
            addbtn.type = "hidden";
        if (addbox.type === "hidden")
            addbox.type = "text";
        else
            addbox.type = "hidden";
    }
};

//e.ctrlKey apparently could also work instead of e.keyCode ===17

//
//
//window.onkeydown = function(e) {
//    if(event.keyCode === 17) {
//        e.target.style.backgroundColor = 'blue';
//    }
//};
//    
//listElement.onClick = function(e) {
//    if (event.ctrlKey) {
//        
//    }
//};
    
//console.log(listElements[i].innerHTML);
                //console.log(listElementsArray[i].innerHTML);
                //console.log(listElements[i].nodeType);
                //console.log(listElements[i].nodeValue);
                //console.log(listElements[i].textContent);
                //console.log(listElementsArray[i]);