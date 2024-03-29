
// list of elements to modify
var listElements = document.querySelectorAll('a');

// remembers original element names
var listElementsArray = [];

for (let k = 0; k < listElements.length; k++) {
    let element = {};
    element.id = listElements[k].id;
    element.name = listElements[k].textContent;
    listElementsArray[k] = element;
}

document.addEventListener('keydown', changeEditability);


function changeEditability(e) {
    if (e.keyCode === 17) {
        if (listElements.length > 0) {
            
            // turn edit mode on
            if (listElements[0].contentEditable === "false") {
                for (let i = 0; i < listElements.length; i++) {
                    listElements[i].contentEditable = "true";
                    document.getElementById("delbtn" + listElements[i].id).type = "submit";
                }
            }
            // turn edit mode off; evaluate changes to be made
            else {
                const form = document.getElementById('updateform');
                
                for (let i = 0; i < listElements.length; i++) {
                    // turn edit mode off
                    listElements[i].contentEditable = "false";
                    document.getElementById("delbtn" + listElements[i].id).type = "hidden";

                    // prepare input elements for any modified names
                    if (listElements[i].textContent === "") {
                        listElements[i].textContent = listElementsArray[i].name;
                    }
                    else {
                        if (listElements[i].textContent !== listElementsArray[i].name) {
                            var listName = document.createElement('input');

                            listName.type = 'hidden';
                            listName.form = form;
                            listName.name = listElementsArray[i].id;
                            listName.value = listElements[i].innerHTML; // this is correct; this gets the updated name

                            form.appendChild(listName);
                        }
                    }
                }

                if (form.childElementCount > 0)
                    form.submit();
                while (form.lastChild)
                    form.removeChild(form.lastChild);
            }
        }
        
        // toggle add button and add text box status
        let addbtn = document.getElementById('addbtn');
        let addbox = document.getElementById('addbox');
        if (addbtn.type === "hidden")
            addbtn.type = "submit";
        else
            addbtn.type = "hidden";
        if (addbox.type === "hidden")
            addbox.type = "text";
        else {
            addbox.type = "hidden";
            addbox.value = "";
        }
        
        let fields = document.querySelectorAll('input[id^="field"]');
        console.log(fields);
        for (let i = 0; i < fields.length; i++) {
            if (fields[i].style.display === "none") {
                console.log("hello1");
                fields[i].style.display = "block";
                fields[i].contentEditable = "true";
            }
            else {
                console.log("hello2");
                fields[i].style.display = "none";
                fields[i].contentEditable = "false";
            }
        }
    }
};

function createOutline() {
    let outlineList = document.getElementById("outlineList");
    
    
};

function showItem() {
    
}

//var deleteButtons = document.querySelectorAll('input[id^="delbtn"]');
//
//for (const deleteButton of deleteButtons) {
//    deleteButton.addEventListener('click', checkDelete);
//}
//
//
//function checkDelete(e) {
//    var confirmation;
//    if (window.location.pathname == "/SimpleSpringMVC/")
//        if ((confirmation = window.confirm("are you sure you want to delete this list?"))) {
//            document.getElementById("delform").reset();
//            console.log(document.getElementById("delform").target);
//            console.log("confirmation=" + confirmation);
//            alert();
//        }
//            
////    console.log(window.location.href);
////    console.log(window.document.title);
////    console.log(window.document.head);
////    console.log(window.document.URL);
////    console.log(window.name);
////    alert();
//}



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
