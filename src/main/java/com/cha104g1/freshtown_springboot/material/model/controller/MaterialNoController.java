package com.cha104g1.freshtown_springboot.material.model.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;

@Controller
@Validated
@RequestMapping("/sFunction/material")
public class MaterialNoController {
    
	@Autowired
	MaterialService materialSvc;
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
			/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
			@NotNull(message="分類編號: 請勿空白")
			@RequestParam("itemNumber") String itemNumber,
			ModelMap model
			) {
	     
		/***************************2.開始查詢資料*********************************************/
		MaterialVO materialVO = materialSvc.getOneMaterial(Integer.valueOf(itemNumber)); 
		
		List<MaterialVO> list = materialSvc.getAll();
		model.addAttribute("materialListData", list);// for select_page.html 第97 109行用
		
		if (materialVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "sFunction/material/select_page";
		}
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("materialVO", materialVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->

		return "sFunction/material/select_page";
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

			List<MaterialVO> list = materialSvc.getAll();
			model.addAttribute("materialListData", list);     // for select_page.html 第97 109行用
	    	
			String message = strBuilder.toString();
		    return new ModelAndView("sFunction/material/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	     }
	
	
        }
