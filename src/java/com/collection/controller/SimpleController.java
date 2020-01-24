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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.collection.validator.FormValidator;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/")
public class SimpleController {
    
    @Autowired
    private SimpleService simpleService;
    
    @Autowired
    private FormValidator formValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(formValidator);
    }
    
    
    /* Handlers for Lists Page */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView home() {
        
        SimpleList emptyList = new SimpleList(); // is this required? if so, why?
        
        List<SimpleList> listOfLists = simpleService.getAllLists();
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simpleLists");
        mv.addObject("listOfLists", listOfLists);
        mv.addObject("list", emptyList);
        return mv;
    }
    
    @RequestMapping(path = "/showlist/{id}", method = RequestMethod.GET)
    public ModelAndView showList(@PathVariable("id") int id) {
        
        SimpleItem newItem = new SimpleItem();
        
        SimpleList currentList = simpleService.getListById(id);
        List<SimpleItem> listOfItems = simpleService.getListItems(id);

        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simplelist");
        mv.addObject("currentList", currentList);
        mv.addObject("listOfItems", listOfItems);
        mv.addObject("newItem", newItem);
        return mv;
    }
    
    @RequestMapping(path = "/addlist", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") @Validated SimpleList newList, BindingResult result, final RedirectAttributes redirectAttributes) {
        
        if (!result.hasErrors())
            simpleService.addList(newList);
        else
            redirectAttributes.addFlashAttribute("emptyField", "name must be specified");
        
        return "redirect:/";
    }
    
    @RequestMapping(path = "/updateListNames", method = RequestMethod.POST)
    public String updateListName(@RequestBody String str) {
        //String regex = "([\\w]*)=";
        String[] inputs = str.split("&");
        
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        String[] lists;
        
        for (int i = 0; i < inputs.length; ++i) {
            lists = inputs[i].split("=");
            ids.add(Integer.valueOf(lists[0]));
            names.add(lists[1].replaceAll("[+]"," "));
            
            simpleService.updateListNames(ids, names);
        }
        
        return "redirect:/";        
    }
    
    @RequestMapping(path = "/dellist/{listId}", method = RequestMethod.POST)
    public String delList(@PathVariable("listId") int listId) {
        simpleService.delList(listId);
        return "redirect:/";
    }
    
    
    /* Handlers for Items Page */
    @RequestMapping(path = "/showitem/{itemId}", method = RequestMethod.GET)
    public ModelAndView showItem(@PathVariable("itemId") int itemId) {
        
        SimpleItem currentItem = simpleService.getItemById(itemId);
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("showitem");
        mv.addObject("currentItem", currentItem);
        return mv;
    }
    
    @RequestMapping(path = "/additem/{listId}", method = RequestMethod.POST)
    public String addItem(@PathVariable("listId") int listId, @ModelAttribute("newItem") @Validated SimpleItem newItem, BindingResult result, RedirectAttributes redirectAttributes) {
        
        if (!result.hasErrors())
            simpleService.addItem(newItem, listId);
        else
            redirectAttributes.addFlashAttribute("emptyField", "name must be specified");
        
        return "redirect:/showlist/" + listId;
    }
    
    @RequestMapping(path = "/updateItemNames/{listId}", method = RequestMethod.POST)
    public String updateItemName(@PathVariable("listId") int listId, @RequestBody String str) {
        //String regex = "([\\w]*)=";
        String[] inputs = str.split("&");
        
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        String[] items;
        
        for (int i = 0; i < inputs.length; ++i) {
            items = inputs[i].split("=");
            ids.add(Integer.valueOf(items[0]));
            names.add(items[1].replaceAll("[+]"," "));
            
            simpleService.updateItemNames(ids, names);
        }        
        
        return "redirect:/showlist/" + listId;
    }
    
    @RequestMapping(path = "/delitem/{listId}/{itemId}", method = RequestMethod.POST)
    public String delItem(@PathVariable("listId") int listId, @PathVariable("itemId") int itemId) {
        simpleService.delItem(itemId);
        return "redirect:/showlist/" + listId;
    }
}


    /*
    @RequestMapping(path = "/updateListNames", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateListNames(@RequestBody List list) { // should change to list of lists; check if existing can be used
        ModelAndView mv = new ModelAndView();
        mv.setViewName("updateListNamesSuccess");
        return mv;
    }
    */