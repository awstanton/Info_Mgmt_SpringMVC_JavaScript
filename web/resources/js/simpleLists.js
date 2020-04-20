
(function() {
    // change the styles to the edit mode styles
    // change the input box and the buttons to showing
    // add edit mode header
    // listen for events on the elements
    // click events on delete buttons
    // keyboard events on the list names
    // undo event
    // redo event
    // submit entire form event

    var origNextListId = nextListId; // used to determine whether elements were existing or not
    
    var undoStack = new Array();
    var redoStack = new Array();
    
    var listOfLists = document.getElementById("lstlst");
    var listNames = listOfLists.querySelectorAll("a");
    var delButtons = listOfLists.querySelectorAll("input");
    
    var listAddBox = document.getElementById("listAddBox");
    var listAddButton = document.getElementById("listAddButton");
    
    console.log(listNames);
    console.log(listAddBox);
    console.log(listAddButton);
    
    function toggleMode(event) {
        if(event.ctrlKey && event.altKey) {
            if (event.key === "e" || event.key === "E") {
                for (const index in listNames) {
                    if (listNames.hasOwnProperty(index)) {
                        listNames[index].classList.toggle("textboxstyle");
                    }
                }
                for (const index in delButtons) {
                    if (delButtons.hasOwnProperty(index)) {
                        if (delButtons[index].classList.contains("displayNone")) {
                            delButtons[index].classList.remove("displayNone");
                            delButtons[index].classList.add("inlineBlock");
                        }
                        else {
                            delButtons[index].classList.add("displayNone");
                            delButtons[index].classList.remove("inlineBlock");
                        }
                    }
                }
//                listAddBox.classList.toggle("displayNone");
//                listAddButton.classList.toggle("displayNone");
            }
//            else if (event.key === "u") {
//                if (undoStack.length > 0) {
//                    var undoStackElement = undoStack[undoStack.length - 1];
//                
//                    switch(undoStackElement[0]) {
//                        case "additem":
//                            var itemAdded = undoStackElement[1];
//                            var parent = itemAdded.parentElement;
//                            parent.removeChild(itemAdded);
//                            redoStack.push(undoStack.pop());
//                            
//                            break;
//                        case "edititem":
//                            var newItemName = undoStackElement[1][1].value;
//                            undoStackElement[1][1].value = undoStackElement[1][0];  // revert name to old name
//                            undoStackElement[1][0] = newItemName; // revert name for use in redo operation
//                            redoStack.push(undoStack.pop());
//
//                            break;
//                        case "removeitem":
//                            undoStackElement[1].insertAdjacentElement('afterend', undoStackElement[2]);
//                            redoStack.push(undoStack.pop());
//
//                            break;
//                        default:
//                            alert("something bad happened to the UNDO code");
//                            break;
//                    }
//                }
//            }
//            else if (event.key === "r" ) {
//                if (redoStack.length > 0) {
//                    var redoStackElement = redoStack[redoStack.length - 1];
//                
//                    switch(redoStackElement[0]) {
//                        case "additem":
//                            var itemUnAdded = redoStackElement[1];
//                            addItemSection.insertAdjacentElement('beforebegin', itemUnAdded);
//                            undoStack.push(redoStack.pop());
//                            
//                            break;
//                        case "edititem":
//                            var oldItemName = redoStackElement[1][1].value;
//                            redoStackElement[1][1].value = redoStackElement[1][0];
//                            redoStackElement[1][0] = oldItemName;
//                            undoStack.push(redoStack.pop());
//
//                            break;
//                        case "removeitem":
//                            redoStackElement[1].parentElement.removeChild(redoStackElement[2]);
//                            undoStack.push(redoStack.pop());
//
//                            break;
//                        default:
//                            alert("something bad happened to the REDO code");
//                            break;
//                    }
//                }
//            }
        }
    }
    
    document.addEventListener("keydown", toggleMode, false);
    
})();