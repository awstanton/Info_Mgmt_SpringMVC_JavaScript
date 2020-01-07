package com.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import com.collection.service.SimpleService;
import com.collection.model.SimpleItem;
import com.collection.model.SimpleList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(value = "/")
public class SimpleController {
    
    @Autowired
    private SimpleService simpleService;
    
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ModelAndView home() {
        
        SimpleList emptyList = new SimpleList();
        
        List<SimpleList> listOfLists = simpleService.getAllLists();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("simpleLists");
        mv.addObject("listOfLists", listOfLists);
        mv.addObject("list", emptyList);
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/simpleLists")
    public ModelAndView simpleLists() {
        
        SimpleList emptyList = new SimpleList();
        
        List<SimpleList> listOfLists = simpleService.getAllLists();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("simpleLists");
        mv.addObject("listOfLists", listOfLists);
        mv.addObject("list", emptyList);
        return mv;
    }
    
    @RequestMapping(path = "/addlist", method = RequestMethod.POST)
    public String addList(@ModelAttribute("list") SimpleList newList, BindingResult result) {
        simpleService.addList(newList);
        
        return "redirect:/simpleLists";
    }
    
    @RequestMapping(path = "/showlist/{id}", method = RequestMethod.GET)
    public ModelAndView showList(@PathVariable("id") int id) {
        
        SimpleList currentList = simpleService.getListById(id);
        List<SimpleItem> listOfItems = simpleService.getListItems(id);
        //System.out.println("first list id is: " + listOfItems.get(0).getItemid());
        SimpleItem newItem = new SimpleItem();
        System.out.println("curr id = " + currentList.getListid());
        System.out.println("curr list name = " + currentList.getName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("simplelist");
        mv.addObject("currentList", currentList);
        mv.addObject("listOfItems", listOfItems);
        mv.addObject("newItem", newItem);
        
        return mv;
    }
    
    @RequestMapping(path = "/additem/{listId}", method = RequestMethod.POST)
    public String addItem(@PathVariable("listId") int listId, @ModelAttribute("newItem") SimpleItem newItem, BindingResult result) {
        simpleService.addItem(newItem, listId);
        return "redirect:/showlist/" + listId;
    }
    
    @RequestMapping(path = "/showitem/{itemId}", method = RequestMethod.GET)
    public ModelAndView showItem(@PathVariable("itemId") int itemId) {
        SimpleItem currentItem = simpleService.getItemById(itemId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showitem");
        mv.addObject("currentItem", currentItem);
        return mv;
    }
    
}
