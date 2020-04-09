// MAKE SURE I DO NOT ASSUME ANYWHERE THAT THERE ARE NO DUPLICATES

(function() {
    
    var origNextItemId = nextItemId; // used to determine whether elements were existing or not
    
    var undoStack = new Array();
    var redoStack = new Array();
    
    var viewListSection = document.getElementById("viewListSection");
    var editListSection = document.getElementById("editListSection");
    var sidebarSection = document.getElementById("sidebarSection");
    var submitSection = document.getElementById("submitSection");
    var submitButton = document.getElementById("submitbutton");
    
    function toggleMode(event) {
        if(event.ctrlKey && event.altKey) {
            if (event.key === "e") {
                viewListSection.classList.toggle("displayNone");
                editListSection.classList.toggle("displayNone");
                sidebarSection.classList.toggle("displayNone");
                submitSection.classList.toggle("displayNone");
            }
            else if (event.key === "u") {
                if (undoStack.length > 0) {
                    var undoStackElement = undoStack[undoStack.length - 1];
                
                    switch(undoStackElement[0]) {
                        case "additem":
                            var itemAdded = undoStackElement[1];
                            var parent = itemAdded.parentElement;
                            parent.removeChild(itemAdded);
                            redoStack.push(undoStack.pop());
                            
                            break;
                        case "addfield":
                            var fieldAdded = undoStackElement[1];
                            var parent = fieldAdded.parentElement;
                            parent.removeChild(fieldAdded);
                            redoStack.push(undoStack.pop());

                            break;
                        case "edititem":
                            var newItemName = undoStackElement[1][1].value;
                            undoStackElement[1][1].value = undoStackElement[1][0];  // revert name to old name
                            undoStackElement[1][0] = newItemName; // revert name for use in redo operation
                            redoStack.push(undoStack.pop());

                            break;
                        case "editfield":
                            var newFieldName = undoStackElement[1][1].value;
                            undoStackElement[1][1].value = undoStackElement[1][0];  // revert name to old name
                            undoStackElement[1][0] = newFieldName; // revert name for use in redo operation
                            redoStack.push(undoStack.pop());

                            break;
                        case "removeitem":
                            undoStackElement[1].insertAdjacentElement('afterend', undoStackElement[2]);
                            redoStack.push(undoStack.pop());

                            break;
                        case "removefield":
                            undoStackElement[1].insertAdjacentElement('afterend', undoStackElement[2]);
                            redoStack.push(undoStack.pop());
                            
                            break;

                        default:
                            alert("something bad happened to the UNDO code");
                            break;
                    }
                }
            }
            else if (event.key === "r" ) {
                if (redoStack.length > 0) {
                    var redoStackElement = redoStack[redoStack.length - 1];
                
                    switch(redoStackElement[0]) {
                        case "additem":
                            var itemUnAdded = redoStackElement[1];
                            addItemSection.insertAdjacentElement('beforebegin', itemUnAdded);
                            undoStack.push(redoStack.pop());
                            
                            break;
                        case "addfield":
                            var fieldUnAdded = redoStackElement[1];
                            addFieldSection.insertAdjacentElement('beforebegin', fieldUnAdded);
                            undoStack.push(redoStack.pop());

                            break;
                        case "edititem":
                            var oldItemName = redoStackElement[1][1].value;
                            redoStackElement[1][1].value = redoStackElement[1][0];
                            redoStackElement[1][0] = oldItemName;
                            undoStack.push(redoStack.pop());

                            break;
                        case "editfield":
                            var oldFieldName = redoStackElement[1][1].value;
                            redoStackElement[1][1].value = redoStackElement[1][0];
                            redoStackElement[1][0] = oldFieldName;
                            undoStack.push(redoStack.pop());

                            break;
                        case "removeitem":
                            redoStackElement[1].parentElement.removeChild(redoStackElement[2]);
                            undoStack.push(redoStack.pop());

                            break;
                        case "removefield":
                            redoStackElement[1].parentElement.removeChild(redoStackElement[2]);
                            undoStack.push(redoStack.pop());
                            
                            break;

                        default:
                            alert("something bad happened to the REDO code");
                            break;
                    }
                }
            }
        }
    }

    function remove(event) {
        var id = event.target.id;
        
        if (id.startsWith("delitm") || id.startsWith("delfld")) {
            if (event.ctrlKey && event.altKey) {
                var element = event.target.parentElement;
                var parent = element.parentElement;

                if (id.startsWith("delitm")) {
                    undoStack.push(["removeitem", element.previousElementSibling, parent.removeChild(element)]);
                    if (redoStack.length !== 0) { redoStack.length = 0; }
                }
                else {
                    undoStack.push(["removefield", element.previousElementSibling, parent.removeChild(element)]);
                    if (redoStack.length !== 0) { redoStack.length = 0; }
                }
            }
        }
    }

    var addItemElement = document.getElementById("addItm");
    var addItemSection = document.getElementById("addItemSection");

    function addItem(event) {
        if (addItemElement.value.trim()) {
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
            
            undoStack.push(["additem", newListItem]);
            if (redoStack.length !== 0) { redoStack.length = 0; }
            
        }
        addItemElement.value = "";
    }

    var addFieldElement = document.getElementById("addFld");
    var addFieldSection = document.getElementById("addFieldSection");
    var nextFieldId = 11; // limit on number of fields allowed for a single list

    function addField(event) {
        if (addFieldElement.value.trim() && (sidebarSection.children.length - 1) < 11) {
            var newDiv = document.createElement("div");
            var newInput = document.createElement("input");
            var newButton = document.createElement("input");

            newDiv.appendChild(newInput);
            newDiv.appendChild(newButton);

            newInput.setAttribute("id", "field" + nextFieldId);
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
            
            undoStack.push(["addfield", newDiv]);
            if (redoStack.length !== 0) { redoStack.length = 0; }
            
        }
        addFieldElement.value = "";
    }

    var oldItemName = undefined;
    var oldFieldName = undefined;

    function focusName(event) {
        if (!isNaN(parseInt(event.target.id)) && isFinite(event.target.id)) {
            oldItemName = event.target.value;
            oldFieldName = undefined;
        }
        else if (event.target.id.startsWith("field")) {
            oldFieldName = event.target.value;
            oldItemName = undefined;
        }
    }
    
    // possible issue: one time, it seemed like the field for adding a new field was caught up in the undo and redo of editing the name there
    
    function blurName(event) {
        var newName = event.target.value;
        
        if (oldItemName && newName !== oldItemName) {
            if (newName.trim() === oldItemName || newName.trim() === "") {
                event.target.value = oldItemName;
            }
            else {
                undoStack.push(["edititem", [oldItemName, event.target]]);
                if (redoStack.length !== 0) { redoStack.length = 0; }
                event.target.value = newName.trim();
            }
        }
        else if (oldFieldName && newName !== oldFieldName) {
            if (newName.trim() === oldFieldName || newName.trim() === "") {
                event.target.value = oldFieldName;
            }
            else {
                undoStack.push(["editfield", [oldFieldName, event.target]]);
                if (redoStack.length !== 0) { redoStack.length = 0; }
                event.target.value = newName.trim();
            }
        }
        oldItemName = undefined;
        oldFieldName = undefined;
    }
    
    function submitForm(event) {
        if (undoStack.length === 0 && redoStack.length === 0) {
            addItemElement.value = "";
            addFieldElement.value = "";
            
            viewListSection.classList.toggle("displayNone");
            editListSection.classList.toggle("displayNone");
            sidebarSection.classList.toggle("displayNone");
            submitSection.classList.toggle("displayNone");
        }
        else {
            var itemChildren = editListSection.children;
            var fieldChildren = sidebarSection.children;
            
//            removeDuplicates();
            
            var submitForm = document.getElementById("submitform");
            
            // append items
            for (var j = 1, len = itemChildren.length - 1; j < len; ++j) {
                itemChildren[j].firstElementChild.name = itemChildren[j].firstElementChild.id;
                submitForm.appendChild(itemChildren[j].firstElementChild);
            }
            
            var sepOne = document.createElement("input");
            sepOne.name = " ";
            submitForm.appendChild(sepOne);
            
            // append fields
            for (j = 1, len = fieldChildren.length - 1; j < len; ++j) {
                fieldChildren[j].firstElementChild.name = fieldChildren[j].firstElementChild.id;
                submitForm.appendChild(fieldChildren[j].firstElementChild);
            }
            
            var sepTwo = document.createElement("input");
            sepTwo.name = "  ";
            submitForm.appendChild(sepTwo);
            
            // append elements are to be deleted
            for (var k = 0, len = undoStack.length; k < len; ++k) {
                if (undoStack[k][0] === "removeitem" && undoStack[k][2].firstElementChild.id < origNextItemId) {
                    var itemToDelete = undoStack[k][2].firstElementChild;
                    itemToDelete.name = itemToDelete.id;
                    itemToDelete.value = "";
                    submitForm.appendChild(itemToDelete);
                }
            }
            
//            submitForm.enctype = "text/plain";
//            submitForm.enctype = "multipart/form-data";
            submitForm.submit(); // server will handle determining which ones are already present and 
        }
    }
    
    function removeDuplicates() {
        var itemChildren = editListSection.children;
        var fieldChildren = sidebarSection.children;

        var itemNames = Object.create(null);
        var fieldNames = Object.create(null);
        var name;

        for (var i = 1, len = itemChildren.length - 1; i < len; ++i) {
            name = itemChildren[i].firstElementChild.value;

            if (!(name in itemNames)) {
                itemNames[name] = { 0: 1 /* length of list */, 1: itemChildren[i].firstElementChild };
            }
            else {
                itemNames[name][itemNames[name][0] + 1] = itemChildren[i].firstElementChild; // append element to list for later reference
                itemNames[name][0] = itemNames[name][0] + 1; // increment length of list
            }
        }

        for (i = 1, len = fieldChildren.length - 1; i < len; ++i) {
            name = fieldChildren[i].firstElementChild.value;

            if (!(name in fieldNames)) {
                fieldNames[name] = { 0: 1 /* length of list */, 1: fieldChildren[i].firstElementChild };
            }
            else {
                fieldNames[name][fieldNames[name][0] + 1] = fieldChildren[i].firstElementChild; // append element to list
                fieldNames[name][0] = fieldNames[name][0] + 1; // increment length of list
            }
        }

        for (name in itemNames) {
            len = itemNames[name][0];
            if (len > 1) {
                for (i = 2; i <= len; ++i) {
                    itemNames[name][i].parentElement.parentElement.removeChild(itemNames[name][i].parentElement);
//                        itemNames[name][i].classList.toggle("duplicate");
                }
            }
        }

        for (name in fieldNames) {
            len = fieldNames[name][0];
            if (len > 1) {
                for (i = 2; i <= len; ++i) {
                    fieldNames[name][i].parentElement.parentElement.removeChild(fieldNames[name][i].parentElement);
//                        fieldNames[name][i].classList.toggle("duplicate");
                }
            }
        }
    }
    
    
    document.addEventListener("keydown", toggleMode, false);
    
    document.getElementById("addItmBtn").addEventListener("click", addItem, false);
    document.getElementById("addFldBtn").addEventListener("click", addField, false);
    
    editListSection.addEventListener("focusin", focusName, false);
    sidebarSection.addEventListener("focusin", focusName, false);
    editListSection.addEventListener("focusout", blurName, false);
    sidebarSection.addEventListener("focusout", blurName, false);
    
    editListSection.addEventListener("click", remove, false);
    sidebarSection.addEventListener("click", remove, false);
    
    submitButton.addEventListener("click", submitForm, false);
    
})();

