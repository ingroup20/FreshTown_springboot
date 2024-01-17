package com.cha104g1.freshtown_springboot.customizeditems.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;

@Controller
@RequestMapping("/pFunction/customizeditems")
public class CustomizedItemsController {

	@Autowired
	CustomizedItemsService customizedItemsSvc;
	
	@GetMapping("addCustomizedItems")
	public String addCustomizedItems(ModelMap model) {
		CustomizedItemsVO customizedItemsVO = new CustomizedItemsVO();
		model.addAttribute("customizedItemsVO", customizedItemsVO);
		return "pFunction/customizeditems/addCustomizedItems";
	}
	
	@PostMapping("insert")
	public String insert(@Valid CustomizedItemsVO customizedItemsVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/customizeditems/addCustomizedItems";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customizedItemsSvc.addCustomizedItemsVO(customizedItemsVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		model.addAttribute("customizedItemsListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:pFunction/customizeditems/listAllCustomizedItems"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("custedItemsNo") String custedItemsNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		CustomizedItemsVO customizedItemsVO = customizedItemsSvc.getCustomizedItemsVOByCustedItemsNo(Integer.valueOf(custedItemsNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("customizedItemsVO", customizedItemsVO);
		return "pFunction/customizeditems/update_customizeditems_input"; // 查詢完成後轉交update_emp_input.html
	}
	
	@PostMapping("update")
	public String update(@Valid CustomizedItemsVO customizedItemsVO, BindingResult result, ModelMap model) throws IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/customizeditems/update_customizeditems_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		customizedItemsSvc.updateCustomizedItemsVO(customizedItemsVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		customizedItemsVO = customizedItemsSvc.getCustomizedItemsVOByCustedItemsNo(Integer.valueOf(customizedItemsVO.getCustedItemsNo()));
		model.addAttribute("customizedItemsVO", customizedItemsVO);
		return "pFunction/customizeditems/listOneCustomizedItems"; // 修改成功後轉交listOneEmp.html
	}
	
	// 全資料一覽
	@ModelAttribute("customizedItemsListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomizedItemsVO> referenceListData(Model model) {

		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		return list;
	}
}
