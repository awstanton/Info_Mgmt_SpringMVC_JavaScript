
/* Notes:
    NEED TO MAKE SURE THESE OPERATIONS WORK WHEN THERE ARE NO ITEMS IN THE LIST
    MAYBE green background or border or outline for newly inserted items
    ISSUE: the IDs are not correct on new elements
    ISSUE: fields are not restricted from being more than 10
*/

(function() {
    var viewListSection = document.getElementById("viewListSection");
    var editListSection = document.getElementById("editListSection");
    var sidebarSection = document.getElementById("sidebarSection");
    var submitSection = document.getElementById("submitSection");
    
    function toggleMode(event) {
        if(event.ctrlKey && event.altKey) {
            if (event.key === "e") {
                viewListSection.classList.toggle("displayNone");
                editListSection.classList.toggle("displayNone");
                sidebarSection.classList.toggle("displayNone");
                submitSection.classList.toggle("displayNone");
            }
            else if (event.key === "r") {
                alert("redo");
            }
            else if (event.key === "u") {
                alert("undo");
            }
        }
    }

    function addOrRemove(event) {
        var id = event.target.id;
        var element = document.getElementById(id);
        
        if (id.startsWith("delitm") || id.startsWith("delfld")) {
            if (event.ctrlKey && event.altKey) {
                if (element.value === "O") {
                    var parent = element.parentElement;
                    var grandParent = parent.parentElement;
                    grandParent.removeChild(parent);
                }
            }
            else {
                (element.value === "X") ? element.value = "O" : element.value = "X";
                element.previousElementSibling.classList.toggle("linethrough");
                element.classList.toggle("green");
                if (element.previousElementSibling.readOnly) { 
                    element.previousElementSibling.removeAttribute("readOnly");
                }
                else {
                    element.previousElementSibling.setAttribute("readOnly", "");
                }
            }
        }
    }

    var addItemElement = document.getElementById("addItem");
    var addItemSection = document.getElementById("addItemSection");

    function addItem(event) {
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
        }
    }

    var addFieldElement = document.getElementById("addField");
    var addFieldSection = document.getElementById("addFieldSection");
    var nextFieldId = 11;

    function addField(event) {
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
        }
    }

    function editName() {

    }

    function undo() {

    }

    function redo() {

    }
    
    document.addEventListener("keydown", toggleMode, false);
    document.getElementById("editListSection").addEventListener("click", addOrRemove, false);
    document.getElementById("sidebarSection").addEventListener("click", addOrRemove, false);
    document.getElementById("addItmBtn").addEventListener("click", addItem, false);
    document.getElementById("addFldBtn").addEventListener("click", addField, false);
    
})();




/*

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