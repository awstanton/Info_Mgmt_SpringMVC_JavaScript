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
import javax.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;


@Controller
@RequestMapping(value = "/")
public class SimpleController {
    
    private static final Logger logger = Logger.getLogger(SimpleController.class);
       
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
            if (item.getListid() == id) {
                listOfItems.add(item);
            }
        }

        System.out.println("currentList.outline = " + currentList.getOutline());
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simplelist");
        mv.addObject("currentList", currentList);
        mv.addObject("listOfItems", listOfItems);
        mv.addObject("newItem", newItem);
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
