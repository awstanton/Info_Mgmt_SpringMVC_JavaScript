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

/*
See link to get started
https://www.baeldung.com/spring-controllers
*/

@Controller
@RequestMapping(value = "/")
public class SimpleController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homePage() {
        
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> list3 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list2.add("d");
        list2.add("e");
        list2.add("f");
        list3.add("g");
        list3.add("h");
        list3.add("i");
        
        listOfLists.add(list1);
        listOfLists.add(list2);
        listOfLists.add(list3);
        
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
}

//    @GetMapping(value = "/simplelist/{simplelistID}")
//    public @ResponseBody List getList(@PathVariable Integer listID) {
//    List list = new List();
//    return list;
//}

//    @GetMapping(value = "/simplelist/{simplelistID}")
//    public ModelAndView getLists(@PathVariable Integer listId) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName();
//        mv.getModel().put();
//        
//        return mv;
//    }