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

@Controller
@RequestMapping(value = "/")
public class SimpleController {
    
    @Autowired
    private SimpleService simpleService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        
        List<SimpleList> listOfLists = simpleService.getAllLists();
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("index");
        mv.addObject("listOfLists", listOfLists);
        
        return mv;
    }
    @RequestMapping(path = "/list")
    public String getList() {
        return "simplelist";
    }
    
    @RequestMapping(path = "/item")
    public String getItem() {
        return "simpleitem";
    }
    
    @RequestMapping(path = "/list/get")
    public ModelAndView getAllLists() {
        
        List<SimpleList> simpleLists = simpleService.getAllLists();
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simpleLists");
        mv.addObject("simpleLists", simpleLists);
        
        return mv;
    }
    
    @RequestMapping(path = "/item/get")
    public ModelAndView getAllItems() {
        
        List<SimpleItem> simpleItems = simpleService.getAllItems();
        
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("simpleItems");
        mv.addObject("simpleItems", simpleItems);
        
        return mv;
    }
    
}
