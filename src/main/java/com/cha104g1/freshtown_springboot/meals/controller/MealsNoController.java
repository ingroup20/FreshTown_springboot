package com.cha104g1.freshtown_springboot.meals.controller;

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

import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@Validated
@RequestMapping("/pFunction/meals")
public class MealsNoController {

	@Autowired
	MealsService mealsSvc;
	
	@Autowired
	MealTypeService mealTypeSvc;

	@Autowired
	StoresService storesSvc;
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="商品編號: 請勿空白")
		@RequestParam("mealNo") String mealNo,
		ModelMap model) {
		
		/***************************2.開始查詢資料*********************************************/
//		EmpService empSvc = new EmpService();
		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo));
		
		List<MealsVO> list = mealsSvc.getAll();
		model.addAttribute("mealsListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("mealTypeVO", new MealTypeVO());  // for select_page.html 第133行用
		List<MealTypeVO> list2 = mealTypeSvc.getAll();
    	model.addAttribute("mealTypeListData",list2);    // for select_page.html 第135行用
		model.addAttribute("storesVO", new StoresVO());  // for select_page.html 第133行用
    	List<StoresVO> list3 = storesSvc.getAll();
    	model.addAttribute("storesListData",list3);    // for select_page.html 第135行用
    	
		if (mealsVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "/pFunction/meals/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("mealsVO", mealsVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "back-end/emp/listOneEmp";  // 查詢完成後轉交listOneEmp.html
		return "/pFunction/meals/select_page"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
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
		List<MealsVO> list = mealsSvc.getAll();
		model.addAttribute("mealsListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("mealTypeVO", new MealTypeVO());  // for select_page.html 第133行用
		List<MealTypeVO> list2 = mealTypeSvc.getAll();
    	model.addAttribute("mealTypeListData2",list2);    // for select_page.html 第135行用
		model.addAttribute("storesVO", new StoresVO());  // for select_page.html 第133行用
    	List<StoresVO> list3 = storesSvc.getAll();
    	model.addAttribute("storesListData2",list3);    // for select_page.html 第135行用
		String message = strBuilder.toString();
	    return new ModelAndView("/pFunction/meals/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
}
