package com.cha104g1.freshtown_springboot.login;

import java.util.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.login.model.IdentityVO;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpRepository;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@RequestMapping("/loginP")
public class PLogin {

	private static final long serialVersionUID = 1L;

	@Autowired 
	PlatformEmpService platformEmpSvc;
	
	Map<String,String> pEmpAccountMap = new HashMap<>();
	
	
	
	//sql取出帳密集合
	public Map<String,String> getAccountMap(){
		System.out.println(platformEmpSvc==null);		
		List<PlatformEmpVO> list = platformEmpSvc.getAll();
		
		for (PlatformEmpVO platformEmpVO: list) {
			pEmpAccountMap.put(platformEmpVO.getpEmpAccount(),platformEmpVO.getpEmpPw());
        }
		return pEmpAccountMap;
	}
	


	  protected boolean allowUser(String account, String password) {
		  this.getAccountMap();
		  for(String pEmpAccount : pEmpAccountMap.keySet())
			  if (pEmpAccount.equals(account) && pEmpAccountMap.get(pEmpAccount).equals(password))
				      return true;

		  return false;
		}
		
		  

	  
	    @GetMapping
	    public String loginPage( HttpServletRequest req,Model model) {

	        return "/loginP"; //view
	    }
	    
	    
	    @PostMapping("/loginhandler")  // Assuming your login form uses POST method
	    public String handleLogin(String account, String password ,HttpServletRequest req, HttpServletResponse res) {
	    	System.out.println(platformEmpSvc==null);		
	    	
	    	String url = "/loginP";
	    	if (allowUser(account, password)) {
	            HttpSession session = req.getSession();
	            
                String location = (String) session.getAttribute("location");//
                if (location != null) {
                    session.removeAttribute("location");
                    System.out.println(location+"帳密正確");
                    session.setAttribute("account", account);//以此身分登入
                    url = location;
                } 
	        } 
		  	return "redirect:" + url;
	    }
}
