//
//
listElements = document.querySelectorAll('a');

//for (let j = 0; j < listElements.length; j++) {
//    listElements[j].onkeydown = changeEditability;
//}

document.addEventListener('keydown', changeEditability);

function changeEditability(e) {
    if (e.keyCode === 17 && listElements.length > 0) {
        if (listElements[0].contentEditable === "false") {
            for (let i = 0; i < listElements.length; i++)
                listElements[i].contentEditable = "true";
        }
        else {
            const form = document.createElement('form');
                form.method = 'post';
                form.action = '/SimpleSpringMVC/updateListNames';
            for (let i = 0; i < listElements.length; i++) {
                listElements[i].contentEditable = "false";
                
                listName = document.createElement('input');
                listName.type = 'hidden';
                listName.form = form;
                listName.name = 'list' + i;
                listName.value = listElements[i].innerHTML;
                
                form.appendChild(listName);
            }
            document.body.appendChild(form);
            form.submit();
        }
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
    
