package com.newlecture.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

//    @RequestMapping("index")
//    public void index(HttpServletResponse response) {
//        
//        PrintWriter out;
//        try {
//            out = response.getWriter();
//            out.println("Hello Index");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // return "root.index";
//    }

    @RequestMapping("index")
    @ResponseBody
    public String index(HttpServletResponse response) {

        return "Hello Index1";
    }

//    @Override
//    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        ModelAndView mv = new ModelAndView("root.index");
//        mv.addObject("data", "Spring MVC~~!");
////        mv.setViewName("/WEB-INF/view/index.jsp");
//        return mv;
//    }

}
