package com.cha104g1.freshtown_springboot.customizeddetail.controller;

import java.util.*;

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

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;

@Controller
@Validated
@RequestMapping("/sFunction/customizeddetail")
public class CustomizedDetailNoController {

	@Autowired
	CustomizedDetailService customizedDetailSvc;

	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="項目選項流水號: 請勿空白")
		@RequestParam("custedDtlNo") String custedDtlNo,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
		CustomizedDetailVO customizedDetailVO = customizedDetailSvc.getCustomizedDetailVOByCustedDtlNo(Integer.valueOf(custedDtlNo));
		
		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		model.addAttribute("customizedDetailListData", list);     // for select_page.html 第97 109行用
//		model.addAttribute("customizedItemsVO", new CustomizedItemsVO());  // for select_page.html 第133行用
//		List<CustomizedItemsVO> list2 = customizedItemsSvc.getAll();
//    	model.addAttribute("customizedItemsListData",list2);    // for select_page.html 第135行用
		
		if (customizedDetailVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "sFunction/customizeddetail/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("customizedDetailVO", customizedDetailVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "back-end/emp/listOneEmp";  // 查詢完成後轉交listOneEmp.html
		return "sFunction/customizeddetail/select_page"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
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
//	    model.addAttribute("empVO", new EmpVO());
//    	EmpService empSvc = new EmpService();
		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		model.addAttribute("empListData", list);     // for select_page.html 第97 109行用
//		model.addAttribute("customizedItemsVO", new CustomizedItemsVO());  // for select_page.html 第133行用
//		List<CustomizedItemsVO> list2 = customizedItemsSvc.getAll();
//    	model.addAttribute("customizedItemsListData",list2);    // for select_page.html 第135行用
		String message = strBuilder.toString();
	    return new ModelAndView("back-end/emp/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}	
}
