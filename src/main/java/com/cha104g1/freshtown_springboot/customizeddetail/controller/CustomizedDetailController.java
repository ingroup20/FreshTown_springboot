package com.cha104g1.freshtown_springboot.customizeddetail.controller;

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

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;

@Controller
@RequestMapping("/pFunction/customizeddetail")
public class CustomizedDetailController {
	
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	
	@Autowired
	CustomizedItemsService customizedItemsSvc;

	@GetMapping("addCustomizedDetail")
	public String addCustomizedDetail(ModelMap model) {
		CustomizedDetailVO customizedDetailVO = new CustomizedDetailVO();
		model.addAttribute("customizedDetailVO", customizedDetailVO);
		return "pFunction/customizeddetail/addCustomizedDetail";
	}
	
	@PostMapping("insert")
	public String insert(@Valid CustomizedDetailVO customizedDetailVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/customizeddetail/addCustomizedDetail";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customizedDetailSvc.addCustomizedDetailVO(customizedDetailVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		model.addAttribute("customizedDetailListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:pFunction/customizeddetail/listAllCustomizedDetail"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("custedDtlNo") String custedDtlNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		CustomizedDetailVO customizedDetailVO = customizedDetailSvc.getCustomizedDetailVOByCustedDtlNo(Integer.valueOf(custedDtlNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("customizedDetailVO", customizedDetailVO);
		return "pFunction/customizeddetail/update_customizeddetail_input"; // 查詢完成後轉交update_emp_input.html
	}
	
	@PostMapping("update")
	public String update(@Valid CustomizedDetailVO customizedDetailVO, BindingResult result, ModelMap model) throws IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/customizeddetail/update_customizeddetail_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		customizedDetailSvc.updateCustomizedDetailVO(customizedDetailVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		customizedDetailVO = customizedDetailSvc.getCustomizedDetailVOByCustedDtlNo(Integer.valueOf(customizedDetailVO.getCustedDtlNo()));
		model.addAttribute("customizedDetailVO", customizedDetailVO);
		return "pFunction/customizeddetail/listOneCustomizedDetail"; // 修改成功後轉交listOneEmp.html
	}
	
	// 全資料一覽
	@ModelAttribute("customizedDetailListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomizedDetailVO> referenceListData(Model model) {

		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		return list;
	}
	
	@ModelAttribute("customizedItemsListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomizedItemsVO> referenceListData1(Model model) {
		
		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		return list;
	}
}
