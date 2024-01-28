package com.cha104g1.freshtown_springboot.login;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;


@Controller
@RequestMapping("/loginC")
public class CLoginController {
	
	private static final long serialVersionUID = 1L;

	@Autowired 
	CustomerService customerSvc;

	//儲存登入帳號
	CustomerVO customerLogin;

		
	//驗證身分，C顧客
	  protected boolean allowUserC(String account, String password) {
		  if(account!=null && !account.equals("")) {
			  customerLogin = customerSvc.getByCustomerAddress(account);
			  if ( customerLogin != null && account.equals(customerLogin.getCustomerAddress()) && password.equals(customerLogin.getCustomerPw())  )
				  return true;
		  }
		  System.out.println("帳密錯誤，或不存在");
		  return false;
		}
	  
	  //登入畫面
	    @GetMapping
	    public String loginPageC( HttpServletRequest req,Model model) {
	        return "/loginC"; //view
	    }
	    
	    //接收帳密
	    @PostMapping("/loginhandlerC")  // Assuming your login form uses POST method
	    public String handleLoginP(String account, String password ,HttpServletRequest req, HttpServletResponse res) {
	    	
	    	String url = "/loginC";
	    	if (allowUserC(account, password)) {
	            HttpSession session = req.getSession();
	            
                String location = (String) session.getAttribute("location");//
                if (location != null) {
                    session.removeAttribute("location");
                    System.out.println(location+"帳密正確");
					session.setAttribute("customerLogin", customerLogin);//以此身分登入
					session.setAttribute("customerVO", customerLogin);
                    url = location;
                } 
	        } 

		  	return "redirect:" + url;
	    }
}
