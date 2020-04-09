package com.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.collection.service.SimpleService;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import com.collection.util.Util;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.collection.validator.FormValidator;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;


@Controller
@RequestMapping(value = "/")
public class SimpleController {
    
    private static final Logger logger = Logger.getLogger(SimpleController.class);
    
    private static HashMap<String,Character> asciiCodes = new HashMap<>();
    
    @Autowired
    @Qualifier("util")
    private Util util;
    
    @Autowired
    private SimpleService simpleService;
    
    @Autowired
    private FormValidator formValidator;
    
    //@InitBinder("name")
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(formValidator);
    }
    
    @PostConstruct
    public void init() {
        List<SimpleList> listOfLists = simpleService.getAllLists();
        for (SimpleList list : listOfLists) {
            util.getLists().put(list.getListid(), list); // to be replaced
            List<SimpleItem> listOfItems = simpleService.getListItems(list.getListid());
            for (SimpleItem item : listOfItems) {
                util.getItems().put(item.getItemid(), item); // to be replaced
            }
        }
        util.setNextListId(simpleService.getNextListId()); // to be replaced
        util.setNextItemId(simpleService.getNextItemId()); // to be replaced
        
        asciiCodes.put("21", '!');
        asciiCodes.put("22", '"');
        asciiCodes.put("23", '#');
        asciiCodes.put("24", '$');
        asciiCodes.put("25", '%');
        asciiCodes.put("26", '&');
        asciiCodes.put("27", '\'');
        asciiCodes.put("28", '(');
        asciiCodes.put("29", ')');
        asciiCodes.put("2A", '*');
        asciiCodes.put("2B", '+');
        asciiCodes.put("2C", ',');
        asciiCodes.put("2D", '-');
        asciiCodes.put("2E", '.');
        asciiCodes.put("2F", '/');
        asciiCodes.put("3A", ':');
        asciiCodes.put("3B", ';');
        asciiCodes.put("3C", '<');
        asciiCodes.put("3D", '=');
        asciiCodes.put("3E", '>');
        asciiCodes.put("3F", '?');
        asciiCodes.put("40", '@');
        asciiCodes.put("5B", '[');
        asciiCodes.put("5C", '\\');
        asciiCodes.put("5D", ']');
        asciiCodes.put("5E", '^');
        asciiCodes.put("5F", '_');
        asciiCodes.put("60", '`');
        asciiCodes.put("7B", '{');
        asciiCodes.put("7C", '|');
        asciiCodes.put("7D", '}');
        asciiCodes.put("7E", '~');
    }
    
    /* Handlers for Lists Page */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView home() {
        SimpleList emptyList = new SimpleList(); // is this required? if so, why?
        
//      List<SimpleList> listOfLists = simpleService.getAllLists();
        List<SimpleList> listOfLists = new ArrayList<>();
//        System.out.println("lists size = " + util.getLists().size());
//        System.out.println("items size = " + util.getItems().size());
        for (SimpleList list : util.getLists().values()) { // to be replaced
            listOfLists.add(list);
        }
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simpleLists");
        mv.addObject("listOfLists", listOfLists);
        mv.addObject("list", emptyList);
        return mv;
    }
    
    @RequestMapping(path = "/showlist/{id}", method = RequestMethod.GET)
    public ModelAndView showList(@PathVariable("id") int id) {
        
        SimpleItem newItem = new SimpleItem();
        
        SimpleList currentList = util.getLists().get(id);  // to be replaced
        List<SimpleItem> listOfItems = new ArrayList<>();

        for (SimpleItem item : util.getItems().values()) { // to be replaced
//            System.out.println("item listid = " + item.getListid() + ", item name = " + item.getName());

//            Pattern p = Pattern.compile("\\\\\\\\");
//            Matcher m = p.matcher(item.getName());
//            item.setName(m.replaceAll("\\\\"));
//            p = Pattern.compile("&");
//            m = p.matcher(item.getName());
//            item.setName(m.replaceAll("&amp"));
//            p = Pattern.compile("\\\\\"");
//            m = p.matcher(item.getName());
//            item.setName(m.replaceAll("&quot"));
//            p = Pattern.compile("\\\\\'");
//            m = p.matcher(item.getName());
//            item.setName(m.replaceAll("&#39"));
            
            

            item.setName(item.getName().replaceAll("\\\\\\\\", "\\\\"));
            item.setName(item.getName().replaceAll("\\\\\"", "\""));
            item.setName(item.getName().replaceAll("\\\\\'", "\'"));
            
            if (item.getListid() == id) {
                listOfItems.add(item);
            }
        }

//        System.out.println("currentList.outline = " + currentList.getOutline());
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simplelist");
        mv.addObject("currentList", currentList);
        mv.addObject("listOfItems", listOfItems);
        mv.addObject("newItem", newItem);
        mv.addObject("nextItemId", util.getNextItemId());
        return mv;
    }
    
    @RequestMapping(path = "/addlist", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") @Validated SimpleList newList, BindingResult result, final RedirectAttributes redirectAttributes) {
        
        if (!result.hasErrors() && !(util.getLists().containsKey(newList.getListid()))) { // to be replaced
            simpleService.addList(newList.getName());
            newList.setListid(util.postIncrNextListId()); // to be replaced
            util.getLists().put(newList.getListid(), newList); // to be replaced
        }
        else
            formValidator.updateModel(redirectAttributes);
        
        return "redirect:/";
    }
    
    
    @RequestMapping(path = "/updateList/{listId}", method = RequestMethod.POST)
    public String updateList(@PathVariable("listId") int listId, @RequestBody String body) {
        ArrayList<Integer> addItemIds = new ArrayList<>();
        ArrayList<String> addItemVals = new ArrayList<>();
        ArrayList<Integer> editItemIds = new ArrayList<>();
        ArrayList<String> editItemVals = new ArrayList<>();
        ArrayList<String> fieldList = new ArrayList<>();
        ArrayList<Integer> delItemIds = new ArrayList<>();
        
        int bodySection = 1;
        int bodyLength = body.length();
        int index = 0;
        int tokenStartIndex = -1;
        boolean existing = false;
        Integer currItemId = -1;
        
        if (body.charAt(index) == '+') {
            bodySection = 2;
            index += 3; // skip "=&"
        }
        
        
        while (bodySection == 1) {
            
            if (body.charAt(index) == '+') {
                bodySection = 2;
                index += 3; // skip "=&"
                break;
            }
            
            tokenStartIndex = index;

            while (body.charAt(index) != '=') {
                ++index;
            }
            
            currItemId = Integer.parseInt(body.substring(tokenStartIndex, index));
            
            if (util.getItems().containsKey(currItemId)) {
                existing = true;
            }
            else {
                addItemIds.add(currItemId);
            }
            ++index; // advance past '='
            
            tokenStartIndex = index;
            
            StringBuffer buffer = new StringBuffer();
            
            while (body.charAt(index) != '&') {
                if (body.charAt(index) == '+') {
                    buffer.append(' ');
                    ++index;
                }
                else if (body.charAt(index) == '%') {
                    String specChar = body.substring(index + 1, index + 3);
                    if (asciiCodes.containsKey(specChar)) {
                        char code = asciiCodes.get(specChar);
                        if (code == '\'' || code == '\"' || code == '\\') {
                            buffer.append('\\');
                        }
                        buffer.append(code);
                        index += 3;
                    }
                }
                else {
                    buffer.append(body.charAt(index));
                    ++index;
                }
            }
            
            if (existing) {
                if (!util.getItems().get(currItemId).getName().equals(buffer.toString())) { // add to edit only if it was edited
                    editItemIds.add(currItemId);
                    editItemVals.add(buffer.toString());
                }
                existing = false;
            }
            else {
                addItemVals.add(buffer.toString());
            }
            
            ++index; // advance past '&'

        }
        
        
        if (body.charAt(index) == '+') {
            bodySection = 3;
            index += 4; // skip "+=&"
        }
        
        
        while (bodySection == 2) {
            if (body.charAt(index) == '+') {
                bodySection = 3;
                index += 4; // skip "+=&"
                break;
            }
            
            while (body.charAt(index) != '=') {
                ++index;
            }
            
            ++index; // advance past '='
            
            tokenStartIndex = index;
            
            StringBuffer buffer = new StringBuffer();
            
            while (body.charAt(index) != '&') {
                if (body.charAt(index) == '+') {
                    buffer.append(' ');
                    ++index;
                }
                else if (body.charAt(index) == '%') {
                    String specChar = body.substring(index + 1, index + 3);
                    if (asciiCodes.containsKey(specChar)) {
                        char code = asciiCodes.get(specChar);
                        if (code == '\'' || code == '\\' || code == '\"') {
                            buffer.append('\\');
                        }
                        buffer.append(code);
                        if (code == '%') { // %0 represents %
                            buffer.append('0');
                        }
                        else if (code == ':') { // %1 represents :
                            buffer.append('1');
                        }
                        index += 3;
                    }
                }
                else {
                    buffer.append(body.charAt(index));
                    ++index;
                }
            }
            
            fieldList.add(buffer.toString());
            
            ++index; // advance past '&'
            
        }
        
        
        while (bodySection == 3 && index < bodyLength) {
            
            tokenStartIndex = index;
            
            while (index < bodyLength && body.charAt(index) != '=') {
                ++index;
            }
            
            currItemId = Integer.parseInt(body.substring(tokenStartIndex, index));
            
            delItemIds.add(currItemId);
            
            index += 2; // skip past "=&"
            
        }

        simpleService.updateList(listId, addItemIds, addItemVals, editItemIds, editItemVals, fieldList, delItemIds);
        
        
        System.out.println("updateList body:\n" + body);
        // take one character at a time. construct an array of strings (even are the ids and odd are the values)
        // send array to service to insert values for specified ids into the database
        // once "edit" is found, items are done
        // for fields, take one character at a time (let 0 - 9 be ids reserved for fields)
        // when constructing ArrayLists, compare with util to ensure 
        // update database
        // update util
        return "redirect:/showlist/" + listId;
    }
    
    
    @RequestMapping(path = "/updateListNames", method = RequestMethod.POST)
    public String updateListNames(@RequestBody String str) {
        //String regex = "([\\w]*)=";
        String[] inputs = str.split("&");
        
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        String[] listNames;
        
        for (int i = 0; i < inputs.length; ++i) {
            listNames = inputs[i].split("=");
            ids.add(Integer.valueOf(listNames[0]));
            names.add(listNames[1].replaceAll("[+]"," "));
        }
        for (int i = 0; i < inputs.length; ++i) {
            util.getLists().get(ids.get(i)).setName(names.get(i)); // to be replaced
        }
        simpleService.updateListNames(ids, names);
        
        return "redirect:/";        
    }
    
    @RequestMapping(path = "/dellist/{listId}", method = RequestMethod.POST)
    public String delList(@PathVariable("listId") int listId) {
        simpleService.delList(listId);
        util.getLists().remove(listId); // to be replaced
        return "redirect:/";
    }
    
    
    /* Handlers for Items Page */
    @RequestMapping(path = "/showitem/{itemId}", method = RequestMethod.GET)
    public ModelAndView showItem(@PathVariable("itemId") int itemId) {
        
//        SimpleItem currentItem = simpleService.getItemById(itemId);
        SimpleItem currentItem = util.getItems().get(itemId); // to be replaced
        
        
        // GET LIST OUTLINE USING THE LISTID
        // GET ITEM ATTRIBUTES
        // COMBINE THE OUTLINE AND ATTRIBUTES AND ADD TO MODEL
        
        // parse currentItem's attribute/value list and add the array as string values to ModelAndView
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("showitem");
        mv.addObject("currentItem", currentItem);
        return mv;
    }
    
    @RequestMapping(path = "/additem/{listId}", method = RequestMethod.POST)
    public String addItem(@PathVariable("listId") int listId, @ModelAttribute("newItem") @Validated SimpleItem newItem, BindingResult result, final RedirectAttributes redirectAttributes) {
        
        if (!result.hasErrors()) {
            simpleService.addItem(newItem.getName(), listId);
            newItem.setListid(listId); // to be replaced
            newItem.setItemid(util.postIncrNextItemId()); // to be replaced
            util.getItems().put(newItem.getItemid(), newItem); // to be replaced
        }
        else
            formValidator.updateModel(redirectAttributes);
        
        return "redirect:/showlist/" + listId;
    }
    
    @RequestMapping(path = "/updateItemNames/{listId}", method = RequestMethod.POST)
    public String updateItemNames(@PathVariable("listId") int listId, @RequestBody String str) {
        //String regex = "([\\w]*)=";
        String[] inputs = str.split("&");
        
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        String[] itemNames;
        
        for (int i = 0; i < inputs.length; ++i) {
            itemNames = inputs[i].split("=");
            ids.add(Integer.valueOf(itemNames[0]));
            names.add(itemNames[1].replaceAll("[+]"," "));
        }
        simpleService.updateItemNames(ids, names);
        for (int i = 0; i < inputs.length; ++i) {
            util.getItems().get(ids.get(i)).setName(names.get(i)); // to be replaced
        }
        
        return "redirect:/showlist/" + listId;
    }
    
    @RequestMapping(path = "/delitem/{listId}/{itemId}", method = RequestMethod.POST)
    public String delItem(@PathVariable("listId") int listId, @PathVariable("itemId") int itemId) {
        simpleService.delItem(itemId);
        util.getItems().remove(itemId); // to be replaced
        return "redirect:/showlist/" + listId;
    }
    /*
    @RequestMapping(path = "/updateOutline/{listId}", method = RequestMethod.POST)
    public String updateOutline(@PathVariable("listId") int listId) {
        simpleService.updateOutline(listid, outline);
        util.getLists().get(listid).setOutline(outline);
        
    }
    
    @RequestMapping(path = "/updateItemInfo/{itemId}", method = RequestMethod.POST)
    public String updateItemInfo(@PathVariable("itemId") int itemId) {
        simpleService.updateItemInfo(itemid, info);
        util.getItems().get(itemid).setInfo(info);
        
    }
    */
    
    
    
    
    
    
}


    /*
    @RequestMapping(path = "/updateListNames", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateListNames(@RequestBody List list) { // should change to list of lists; check if existing can be used
        ModelAndView mv = new ModelAndView();
        mv.setViewName("updateListNamesSuccess");
        return mv;
    }
    */
