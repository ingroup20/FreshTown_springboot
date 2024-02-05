package com.cha104g1.freshtown_springboot.picking.controller;

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
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.picking.service.PickingService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@Validated
@RequestMapping("/sFunction/picking")
public class PickingNoController {
    
	@Autowired
	PickingService pickingSvc;
	
	@Autowired
	MaterialService materialSvc;
	
	@Autowired
	StoreEmpService storeEmpSvc;
	
	@Autowired
	StoresService storesSvc;
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
			/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
//			@NotEmpty(message="領料編號: 請勿空白")
			@RequestParam("pickingNo") String pickingNo,
			ModelMap model
			) {
	     
		/***************************2.開始查詢資料*********************************************/
		PickingVO pickingVO = pickingSvc.getOnePicking(Integer.valueOf(pickingNo)); 
		
		List<PickingVO> list = pickingSvc.getAll();
		model.addAttribute("pickingListData", list);// for select_page.html 第97 109行用
		model.addAttribute("materialVO", new MaterialVO());
		List<MaterialVO> list2 = materialSvc.getAll();
    	model.addAttribute("materialListData",list2);    // for select_page.html 第135行用
    	model.addAttribute("storeEmpVO", new StoresVO());  // for select_page.html 第133行用
    	List<StoreEmpVO> list3 = storeEmpSvc.getAll();
    	model.addAttribute("storeEmpListData2",list3);    // for select_page.html 第135行用
    	model.addAttribute("storesVO", new StoresVO());  // for select_page.html 第133行用
    	List<StoresVO> list4 = storesSvc.getAll();
    	model.addAttribute("storesListData2",list4);    // for select_page.html 第135行用
    	if (pickingVO == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "sFunction/picking/select_page";
		}
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("pickingVO", pickingVO);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->

		return "sFunction/picking/select_page";
	}
		//錯誤訊息
		@ExceptionHandler(value = { ConstraintViolationException.class })
		//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
		public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
		    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		    StringBuilder strBuilder = new StringBuilder();
		    for (ConstraintViolation<?> violation : violations ) {
		          strBuilder.append(violation.getMessage() + "<br>");
		    }
		    //==== 以下第92~96行是當前面第77行返回 /src/main/resources/templates/back-end/emp/select_page.html用的 ====   

			List<PickingVO> list = pickingSvc.getAll();
			model.addAttribute("pickingListData", list);     // for select_page.html 第97 109行用
			model.addAttribute("materialVO", new MaterialVO());  // for select_page.html 第133行用
			List<MaterialVO> list2 = materialSvc.getAll();
	    	model.addAttribute("materialListData",list2); 
	    	model.addAttribute("storeEmpVO", new StoreEmpVO());  // for select_page.html 第133行用
	    	List<StoreEmpVO> list3 = storeEmpSvc.getAll();
	    	model.addAttribute("storeEmpListData2",list3);    // for select_page.html 第135行用
	    	List<StoresVO> list4 = storesSvc.getAll();
	    	model.addAttribute("storesListData2",list4);    // for select_page.html 第135行用
	    	
			String message = strBuilder.toString();
		    return new ModelAndView("sFunction/picking/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	     }
	
	
        }
