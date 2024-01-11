package com.cha104g1.freshtown_springboot.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class PLogin {

	private static final long serialVersionUID = 1L;


	  protected boolean allowUser(String account, String password) {
	    if ("tomcat".equals(account) && "tomcat".equals(password))
	      return true;
	    else
	      return false;
	  }
	  
	    // http://......../hello?name=peter1
	    @GetMapping
	    public String loginPage( HttpServletRequest req,Model model) {

	        return "/login"; //view
	    }
	    
	    
	  @PostMapping("loginhandler")  // Assuming your login form uses POST method
	    public String handleLogin(String account, String password ,HttpServletRequest req, HttpServletResponse res) {

		  if (allowUser(account, password)) {

	            HttpSession session = req.getSession();
	            try {
//	                String location = (String) session.getAttribute("location");//
	                String location = (String) session.getAttribute("location");//
	                if (location != null) {
	                    session.removeAttribute("location");
	                    System.out.println(location+"帳密正確");
	                    return  "redirect:"+ location ;
	                }
	            } catch (Exception ignored) {

	            }
	            System.out.println("帳密正確無導向");
	            return "redirect:/login_success";
	        } else {
	        	System.out.println("帳密正確無導向");
	            return "redirect:/login";
	        }
	    
	    }
}
