package com.cha104g1.freshtown_springboot.service.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	  
	 @GetMapping("/sFunction/service/nickName")
		public String usChat(Model model) {
			return "sFunction/service/nickName";
		}
	    
	    @GetMapping("/pFunction/service/csChat")
		public String csChat(Model model) {
			return "pFunction/service/csChat";
		}
	    
		 @PostMapping("/sFunction/service/usChat")
			public String usChatxxx(@RequestParam("userName") String userName ,Model model){
			 System.out.println("前往xxx");
//		     String userName = req.getParameter("userName");
			 model.addAttribute("userName", userName);
			 System.out.println("取得名字" + userName);
				return "sFunction/service/usChat";
			}

}