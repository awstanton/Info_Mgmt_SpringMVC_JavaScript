
/* Notes:
    NEED TO MAKE SURE THESE OPERATIONS WORK WHEN THERE ARE NO ITEMS IN THE LIST
    MAYBE green background or border or outline for newly inserted items
*/

(function() {
    
    var editMode = false;
    
    var stack = new Array();
    var currentIndex = -1;
    
//    var stackEnum = {
//      "newfield": 1,
//      "newitem": 2,
//      "editfield": 3,
//      "edititem": 4,
//      "deletefield": 5,
//      "deleteitem": 6,
//      "crossfield": 7,
//      "crossitem": 8,
//      "uncrossfield": 9,
//      "uncrossitem": 10
//    };
    
    
    
    var viewListSection = document.getElementById("viewListSection");
    var editListSection = document.getElementById("editListSection");
    var sidebarSection = document.getElementById("sidebarSection");
    var submitSection = document.getElementById("submitSection");
    
    function toggleMode(event) {
//        console.log(editMode + " in toggleMode");
        if(event.ctrlKey && event.altKey) {
            if (event.key === "e") {
                viewListSection.classList.toggle("displayNone");
                editListSection.classList.toggle("displayNone");
                sidebarSection.classList.toggle("displayNone");
                submitSection.classList.toggle("displayNone");
                
                (editMode) ? editMode = false : editMode = true;
            }
        }
    }

    function addOrRemove(event) {
        var id = event.target.id;
        var element = document.getElementById(id); // just use event.target instead?
        
        if (id.startsWith("delitm") || id.startsWith("delfld")) {
            if (event.ctrlKey && event.altKey) {
                if (element.value === "O") {
                    var parent = element.parentElement;
                    var grandParent = parent.parentElement;
                    
                    if (id.startsWith("delitm")) {
                        stack.push(["deleteitem", grandParent.removeChild(parent)]); ////
                        ++currentIndex; //
                    }
                    else {
                        stack.push(["deletefield", grandParent.removeChild(parent)]); ////
                        ++currentIndex; //
                    }
//                    console.log(currentIndex);
//                    console.log(stack);
//                    console.log(stack[currentIndex][1].toString());
                }
            }
            else {
                (element.value === "X") ? element.value = "O" : element.value = "X";
                element.previousElementSibling.classList.toggle("linethrough");
                element.classList.toggle("green");
                if (element.previousElementSibling.readOnly) {
                    element.previousElementSibling.removeAttribute("readOnly");
                    
                    stack.push(["uncross", element.previousElementSibling]); //
                    ++currentIndex; //
                }
                else {
                    element.previousElementSibling.setAttribute("readOnly", "");
                    
                    stack.push(["cross", element.previousElementSibling]); //
                    ++currentIndex; //
                }
            }
        }
    }

    var addItemElement = document.getElementById("addItem");
    var addItemSection = document.getElementById("addItemSection");

    function addItem(event) {
        console.log(editMode + " in addItem");
        if (addItemElement.value.trim()) { // add check for duplicates
            var newListItem = document.createElement("li");
            var newInput = document.createElement("input");
            var newButton = document.createElement("input");

            newListItem.appendChild(newInput);
            newListItem.appendChild(newButton);

            newInput.setAttribute("id", nextItemId);
            newInput.setAttribute("value", addItemElement.value.trim());
            newInput.setAttribute("type", "text");
            newInput.setAttribute("contentEditable", "true");
            newInput.setAttribute("maxLength", "30");
            newInput.setAttribute("spellCheck", "false");
            newInput.setAttribute("class", "item");
            newInput.setAttribute("href", "");

            newButton.setAttribute("id", "delitm" + nextItemId++);
            newButton.setAttribute("value", "X");
            newButton.setAttribute("type", "button");
            newButton.setAttribute("class", "itmbtn red");

            addItemSection.insertAdjacentElement('beforebegin', newListItem);

            addItemElement.value = "";
            
            stack.push(["additem", newListItem]); //
            ++currentIndex; //
        }
    }

    var addFieldElement = document.getElementById("addField");
    var addFieldSection = document.getElementById("addFieldSection");
    var nextFieldId = 11;

    function addField(event) {
        console.log(editMode + " in addField");
        var fieldNum = document.getElementById("sidebarSection").children.length - 1;

        if (addFieldElement.value.trim() && fieldNum < 11) { // add check for duplicates
            var newDiv = document.createElement("div");
            var newInput = document.createElement("input");
            var newButton = document.createElement("input");

            newDiv.appendChild(newInput);
            newDiv.appendChild(newButton);

            newInput.setAttribute("id", "editField" + nextFieldId);
            newInput.setAttribute("value", addFieldElement.value.trim());
            newInput.setAttribute("type", "text");
            newInput.setAttribute("contentEditable", "true");
            newInput.setAttribute("maxLength", "30");
            newInput.setAttribute("spellCheck", "false");
            newInput.setAttribute("class", "fld");

            newButton.setAttribute("id", "delfld" + nextFieldId++);
            newButton.setAttribute("value", "X");
            newButton.setAttribute("type", "button");
            newButton.setAttribute("class", "fldbtn red");

            addFieldSection.insertAdjacentElement('beforebegin', newDiv);

            addFieldElement.value = "";
            
            stack.push(["addfield", newDiv]); //
            ++currentIndex; //
        }
    }

    var oldItemName = undefined;
    var oldFieldName = undefined;

    // IS FOCUS OCCURRING WHEN I PRESS THE BUTTON BECAUSE IT IS MODIFYING PROPERTIES OF THE ELEMENT IN QUESTION?

    function focusName(event) {
//        console.log("focusName");
        if (!event.target.readOnly) {
            if (!isNaN(parseInt(event.target.id)) && isFinite(event.target.id)) {
                console.log("focus on edit item");
                oldItemName = event.target.value;
                oldFieldName = undefined;
            }
            else if (event.target.id.startsWith("editField")) {
                console.log("focus on edit field");
                oldFieldName = event.target.value;
                oldItemName = undefined;
            }
        }
    }
    
    function blurName(event) {
//        console.log("blurName");
//        console.log("oldItemName = " + oldItemName);
//        console.log("oldFieldName = " + oldFieldName);
        
        if (!event.target.readOnly) {
            if (oldItemName && event.target.value !== oldItemName) {
                if (event.target.value.trim() === oldItemName || event.target.value.trim() === "") {
                    event.target.value = oldItemName;
                }
                else {
                    stack.push(["edititem", [oldItemName, event.target]]);
                    ++currentIndex;
                    event.target.value = event.target.value.trim();

                    console.log(stack);
                }
            }
            else if (oldFieldName && event.target.value !== oldFieldName) {
                if (event.target.value.trim() === oldFieldName || event.target.value.trim() === "") {
                    event.target.value = oldFieldName;
                }
                else {
                    stack.push(["editfield", [oldFieldName, event.target]]);
                    ++currentIndex;
                    event.target.value = event.target.value.trim();

                    console.log(stack);
                }
            }
        }
        oldItemName = undefined;
        oldFieldName = undefined;
    }
    
    function undo() {
        // if the stack is not empty and currentIndex is not less than 0
        // read element on top of the stack
        // based on action, carry out appropriate action using the provided object reference
        // decrement the index
    }

    function redo() {
       // if stack is not empty and currentIndex is not equal to length - 1
       // read element at currentIndex + 1
       // based on action, carry out appropriate action using provided object reference
       // increment the index
    }
    
    document.addEventListener("keydown", toggleMode, false);
    document.getElementById("editListSection").addEventListener("click", addOrRemove, false);
    document.getElementById("sidebarSection").addEventListener("click", addOrRemove, false);
    document.getElementById("addItmBtn").addEventListener("click", addItem, false);
    document.getElementById("addFldBtn").addEventListener("click", addField, false);
    
    document.getElementById("editListSection").addEventListener("focusin", focusName, false);
    document.getElementById("sidebarSection").addEventListener("focusin", focusName, false);
    document.getElementById("editListSection").addEventListener("focusout", blurName, false);
    document.getElementById("sidebarSection").addEventListener("focusout", blurName, false);
    
})();




/*

if (!isNaN(parseInt(event.target.id)) && isFinite(event.target.id))

function addItem(event) {
    var addItem = document.getElementById("addItem");
    
    if (addItem.value.trim()) {
        var addItemSection = document.getElementById("addItemSection");
        
        var refListItem = addItemSection.previousElementSibling;
        
        var newListItem = refListItem.cloneNode(true);

        newListItem.firstElementChild.setAttribute("id", "SAMPLE");
        newListItem.firstElementChild.setAttribute("value", addItem.value.trim());
        newListItem.firstElementChild.setAttribute("type", "text");
        newListItem.firstElementChild.setAttribute("contentEditable", "true");
        newListItem.firstElementChild.setAttribute("maxLength", refListItem.firstElementChild.maxLength);
        newListItem.firstElementChild.setAttribute("spellCheck", "false");
        newListItem.firstElementChild.setAttribute("class", refListItem.firstElementChild.className);

        var newHref = refListItem.firstElementChild.href;
        // manipulate newHref by replacing the item id
        newListItem.setAttribute("href", newHref);

        newListItem.lastElementChild.setAttribute("id", "delitmSAMPLE");
        newListItem.lastElementChild.setAttribute("value", "X");
        newListItem.lastElementChild.setAttribute("type", "button");
        newListItem.lastElementChild.setAttribute("class", "itmbtn red");

        addItemSection.insertAdjacentElement('beforebegin', newListItem);

        addItem.value = "";
    }
}

function addField(event) {
    var addField = document.getElementById("addField");
    
    console.log("children length = " + document.getElementById("section3").children.length);
    
    if (addField.value.trim() && document.getElementById("section3").children.length < 12) {
        var addFieldSection = document.getElementById("addFieldSection");
        var refDiv = addFieldSection.previousElementSibling;
        var newDiv = refDiv.cloneNode(true);

        newDiv.firstElementChild.setAttribute("id", "SAMPLE");
        newDiv.firstElementChild.setAttribute("value", addField.value.trim());
        newDiv.firstElementChild.setAttribute("type", "text");
        newDiv.firstElementChild.setAttribute("contentEditable", "true");
        newDiv.firstElementChild.setAttribute("maxLength", refDiv.firstElementChild.maxLength);
        newDiv.firstElementChild.setAttribute("spellCheck", "false");
        newDiv.firstElementChild.setAttribute("class", refDiv.firstElementChild.className);

        var newHref = refDiv.firstElementChild.href;
        // manipulate newHref by replacing the item id
        newDiv.setAttribute("href", newHref);

        newDiv.lastElementChild.setAttribute("id", "delfldSAMPLE");
        newDiv.lastElementChild.setAttribute("value", "X");
        newDiv.lastElementChild.setAttribute("type", "button");
        newDiv.lastElementChild.setAttribute("class", "fldbtn red");

        addFieldSection.insertAdjacentElement('beforebegin', newDiv);

        addField.value = "";
    }
}

 */