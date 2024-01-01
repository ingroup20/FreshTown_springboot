package com.cha104g1.freshtown_springboot.stores.controller;

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
@RequestMapping("/stores")
public class StoresController {

	@Autowired
	StoresService storesSvc;

	/*
	 * This method will serve as addStores.html handler.
	 */
	@GetMapping("addNewStoresVO")
	public String addNewStoresVO(ModelMap model) {
		StoresVO storesVO = new StoresVO();
		model.addAttribute("StoresVO", storesVO);
		return "會員申請成功待審核通知頁面";
	}

	/*
	 * This method will be called on addStores.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid StoresVO StoresVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(StoresVO, result, "upFiles");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "店家照片: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				StoresVO.setPhoto(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			return "會員申請頁面";
		}
		/*************************** 2.開始新增資料 *****************************************/
		storesSvc.addStoresVO(StoresVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/Stores/listAllStores"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/Stores/listAllStores")
	}

	/*
	 * This method will be called on listAllStores.html form submission, handling POST request
	 */
	@PostMapping("updatePartStoresVO")
	public String updatePartStoresVO(@RequestParam("storeId") String storeId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		StoresVO StoresVO = storesSvc.getStoresVOByStoreId(Integer.valueOf(storeId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("StoresVO", StoresVO);
		return "會員空間頁面"; // 查詢完成後轉交update_Stores_input.html
	}

	/*
	 * This method will be called on update_Stores_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid StoresVO storesVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(storesVO, result, "upFiles");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			byte[] upFiles = storesSvc.getStoresVOByStoreId(storesVO.getStoreId()).getPhoto();
			storesVO.setPhoto(upFiles);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] upFiles = multipartFile.getBytes();
				storesVO.setPhoto(upFiles);
			}
		}
		if (result.hasErrors()) {
			return "back-end/Stores/update_Stores_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// StoresService StoresSvc = new StoresService();
		storesSvc.updateStoresVO(storesVO);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		storesVO = storesSvc.getStoresVOByStoreId(Integer.valueOf(storesVO.getStoreId()));
		model.addAttribute("storesVO", storesVO);
		return "back-end/Stores/listOneStores"; // 修改成功後轉交listOneStores.html
	}


	@ModelAttribute("storeStateMapData") //
	protected Map<Integer, String> referenceMapData() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(0, "待審核");
		map.put(1, "審核未通過");
		map.put(2, "已開通");
		map.put(3, "停權 ");
		map.put(4, "作廢帳號");
		return map;
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(StoresVO StoresVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(StoresVO, "StoresVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 *複合查詢
	 */
	@PostMapping("listStores_ByCompositeQuery")
	public String listAllStores(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<StoresVO> list = storesSvc.getStoresVOByCompositeQuery(map);
		model.addAttribute("storesListData", list); // for listAllStores.html 第85行用
		return "back-end/Stores/listAllStores";
	}
	
	
	

}