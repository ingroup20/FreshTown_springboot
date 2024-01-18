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
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;


@Controller
@RequestMapping("/loginP")
public class PLoginController {
	
	private static final long serialVersionUID = 1L;

	@Autowired 
	PlatformEmpService platformEmpSvc;
	@Autowired 
	CustomerService customerSvc;
	@Autowired 
	StoreEmpService storeEmpSvc;
	
	//儲存登入帳號
	PlatformEmpVO platformEmpLogin;
	CustomerVO customerLogin;
	StoreEmpVO storeEmpLogin;
	
	//驗證身分，P平台
	  protected boolean allowUserP(String account, String password) {
		  if(account!=null && !account.equals("")) {
			  platformEmpLogin = platformEmpSvc.getByPEmpAccount(account);
			  if ( platformEmpLogin != null && account.equals(platformEmpLogin.getpEmpAccount()) && password.equals(platformEmpLogin.getpEmpPw())  )
				  return true;
		  }
		  return false;
		}
		
	//驗證身分，C顧客
	  protected boolean allowUserC(String account, String password) {
		  if(account!=null && !account.equals("")) {
			  customerLogin = customerSvc.getByCustomerAddress(account);
			  if ( customerLogin != null && account.equals(customerLogin.getCustomerAddress()) && password.equals(customerLogin.getCustomerPw())  )
				  return true;
		  }
		  return false;
		}
<<<<<<< HEAD
=======
		  
>>>>>>> refs/heads/master
	
	  
	  //登入畫面
	    @GetMapping
	    public String loginPageP( HttpServletRequest req,Model model) {
	        return "/loginP"; //view
	    }
	    
	    //接收帳密
	    @PostMapping("/loginhandlerP")  // Assuming your login form uses POST method
	    public String handleLoginP(String account, String password ,HttpServletRequest req, HttpServletResponse res) {
	    	
	    	String url = "/loginP";
	    	if (allowUserP(account, password)) {
	            HttpSession session = req.getSession();
	            
                String location = (String) session.getAttribute("location");//
                if (location != null) {
                    session.removeAttribute("location");
                    System.out.println(location+"帳密正確");
					session.setAttribute("platformEmpLogin", platformEmpLogin);//以此身分登入

                    url = location;
                } 
	        } 
	    	System.out.println("帳密錯誤");
		  	return "redirect:" + url;
	    }
}
