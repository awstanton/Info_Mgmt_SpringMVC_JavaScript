package com.collection.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/*
See link to get started
https://www.baeldung.com/spring-controllers
*/

@Controller
@RequestMapping(value = "/")
public class SimpleController {
    @RequestMapping(method = RequestMethod.GET)
    public String homePage() {
        return "index";
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