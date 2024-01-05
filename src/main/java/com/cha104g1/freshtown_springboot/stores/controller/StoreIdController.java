package com.cha104g1.freshtown_springboot.stores.controller;

import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import java.util.*;


@Controller
@Validated
@RequestMapping("/stores")
public class StoreIdController {
	
	@Autowired
	StoresService storesSvc;
	
	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOneDisplay")
	public String getOneDisplay(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/

		@RequestParam("StoreId") String storeId, ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
//		StoresService StoresSvc = new StoresService();
		StoresVO storesVO = storesSvc.getStoresVOByStoreId(Integer.valueOf(storeId));
		
		List<StoresVO> list = storesSvc.getAll();
		model.addAttribute("storesListData", list);     // for select_page.html 第97 109行用
		
		if (storesVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end/Stores/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("storesVO", storesVO);
		model.addAttribute("getOneDisplay", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
		return "back-end/Stores/select_page"; // 查詢完成後轉交select_page.html由其第158行insert listOneStores.html內的th:fragment="listOneStores-div
	}

	//錯誤訊息顯示
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/tStoreslates/back-end/Stores/select_page.html用的 ====   
		List<StoresVO> list = storesSvc.getAll();
		model.addAttribute("StoresListData", list);     // for select_page.html 第97 109行用
		
		String message = strBuilder.toString();
	    return new ModelAndView("back-end/Stores/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
	//全資料一覽
    @ModelAttribute("storesListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<StoresVO> referenceListData(Model model) {
		
    	List<StoresVO> list = storesSvc.getAll();
		return list;
	}
	
	
}