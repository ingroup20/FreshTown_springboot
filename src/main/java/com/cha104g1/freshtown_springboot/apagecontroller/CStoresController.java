package com.cha104g1.freshtown_springboot.apagecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/addStoresC")
public class CStoresController {
	//申請新店家
	
	@Autowired
	StoresService storesSvc;

	@PostMapping("insertC")
	public String insertC(BindingResult result, ModelMap model,
			@RequestParam("photo") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		/*************************** 2.開始新增資料 *****************************************/
		StoresVO storesVO = new StoresVO();
		model.addAttribute("storesVO", storesVO);
		storesSvc.addStores(storesVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StoresVO> list = storesSvc.getAll();
		model.addAttribute("storesListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/successPage"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}//不用錢依資歷夾?斜線


	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldErrorC(StoresVO storesVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(storesVO, "storesVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 *複合查詢
	 */
	@PostMapping("listStores_ByCompositeQueryC")
	public String listAllStoresC(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<StoresVO> list = storesSvc.getAll(map);
		model.addAttribute("storesListData", list); 
		return "cFunction/stores/listAllStores";
	}
	
	

}