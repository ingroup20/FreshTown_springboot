package com.cha104g1.freshtown_springboot.storeemp.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;



@Controller
@Validated
@RequestMapping("/sFunction/storeEmp")
public class StoreEmpNoController {
	
	@Autowired
	StoreEmpService storeEmpSvc;
	

	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="諮詢紀錄編號: 請勿空白")
		@RequestParam("sEmpId") String sEmpId,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
		StoreEmpVO storeEmpVO = storeEmpSvc.getOneStoreEmp(Integer.valueOf(sEmpId));
		
		List<StoreEmpVO> list = storeEmpSvc.getAll();
		model.addAttribute("storeEmpListData", list);     // for select_page.html 第97 109行用

		if (storeEmpVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "sFunction/storeEmp/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("storeEmpVO", storeEmpVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->

		return "sFunction/storeEmp/select_page"; 
	}


	
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   

		List<StoreEmpVO> list = storeEmpSvc.getAll();
		model.addAttribute("storeEmpListData", list);     // for select_page.html 第97 109行用
    	
		String message = strBuilder.toString();
	    return new ModelAndView("sFunction/storeEmp/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}

}
