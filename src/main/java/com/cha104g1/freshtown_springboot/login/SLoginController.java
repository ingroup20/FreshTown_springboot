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

import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;


@Controller
@RequestMapping("/loginS")
public class SLoginController {
	
	private static final long serialVersionUID = 1L;

	@Autowired 
	StoreEmpService storeEmpSvc;
	
	//儲存登入帳號
	StoreEmpVO storeEmpLogin;
	
		  
	//驗證身分，S店家
	  protected boolean allowUserS(String account, String password) {
		  if(account!=null && !account.equals("")) {
			  storeEmpLogin = storeEmpSvc.getBySEmpId(Integer.valueOf(account));
			  if ( storeEmpLogin != null && account.equals(String.valueOf( storeEmpLogin.getsEmpId()) ) && password.equals(String.valueOf( storeEmpLogin.getsEmpId()) ) )
				  return true;
		  }
		  return false;
		}
	  
	  //登入畫面
	    @GetMapping
	    public String loginPageS( HttpServletRequest req,Model model) {
	        return "/loginS"; //view
	    }
	    
	    //接收帳密
	    @PostMapping("/loginhandlerS")  // Assuming your login form uses POST method
	    public String handleLoginS(String account, String password ,HttpServletRequest req, HttpServletResponse res) {
	    	
	    	String url = "/loginS";
	    	if (allowUserS(account, password)) {
	            HttpSession session = req.getSession();
	            
                String location = (String) session.getAttribute("location");//
                if (location != null) {
                    session.removeAttribute("location");
                    System.out.println(location+"帳密正確");
					session.setAttribute("storeEmpLogin", storeEmpLogin);//以此身分登入

                    url = location;
                } 
	        } 
	    	System.out.println("帳密錯誤");
		  	return "redirect:" + url;
	    }
}
