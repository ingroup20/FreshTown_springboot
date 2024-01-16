package com.cha104g1.freshtown_springboot.service.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;
import com.cha104g1.freshtown_springboot.service.model.service.SvcService;

@Controller
@RequestMapping("/sFunction/service")
public class ServiceController {

	@Autowired
	SvcService svcSvc;
	
	@GetMapping("addService")
	   public String addService(ModelMap model) {
		ServiceVO serviceVO = new ServiceVO();
		model.addAttribute("serviceVO", serviceVO);
		System.out.println("轉交請求");
		return "sFunction/service/addService";
	}

	@PostMapping("insert")
	public String insert(@Valid ServiceVO serviceVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/service/addService";
		}
		/*************************** 2.開始新增資料 *****************************************/
		svcSvc.addService(serviceVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<ServiceVO> list = svcSvc.getAll();
		model.addAttribute("serviceListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:sFunction/service/listAllService"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("custSerNo") String custSerNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		ServiceVO serviceVO = svcSvc.getOneService(Integer.valueOf(custSerNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("serviceVO", serviceVO);
		System.out.println("修改成功1");
		return "sFunction/service/update_service_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid ServiceVO serviceVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/service/update_service_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		svcSvc.updateService(serviceVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		serviceVO = svcSvc.getOneService(Integer.valueOf(serviceVO.getCustSerNo()));
		model.addAttribute("serviceVO", serviceVO);
		return "sFunction/service/listOneService"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("serviceListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<ServiceVO> referenceListData(Model model) {

		List<ServiceVO> list = svcSvc.getAll();
		return list;
	}

}
