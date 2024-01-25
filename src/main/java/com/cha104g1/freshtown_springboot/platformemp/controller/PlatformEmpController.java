package com.cha104g1.freshtown_springboot.platformemp.controller;

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

import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;

@Controller
@RequestMapping("/pFunction/platformEmp")
public class PlatformEmpController {
	
	@Autowired
	PlatformEmpService platformEmpSvc;
//	private String platformEmpId;
	
	
	@GetMapping("addPlatformEmp")
	public String addPlatformEmp(ModelMap model) {
		PlatformEmpVO platformEmpVO = new PlatformEmpVO();
		model.addAttribute("platformEmpVO", platformEmpVO);
		System.out.println("轉交請求");
		return "pFunction/platformEmp/addPlatformEmp";
	}

	@PostMapping("/insert")
	public String insert(@Valid PlatformEmpVO platformEmpVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/platformEmp/addPlatformEmp";
		}
		/*************************** 2.開始新增資料 *****************************************/
		platformEmpSvc.addPlatformEmp(platformEmpVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<PlatformEmpVO> list = platformEmpSvc.getAll();
		model.addAttribute("platformEmpListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "pFunction/platformEmp/listAllPlatformEmp"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("pEmpId") String pEmpId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		PlatformEmpVO platformEmpVO = platformEmpSvc.getOnePlatformEmp(Integer.valueOf(pEmpId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("platformEmpVO", platformEmpVO);
		System.out.println("修改成功1");
		return "pFunction/platformEmp/update_platformEmp_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid PlatformEmpVO platformEmpVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/platformEmp/update_platformEmp_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		platformEmpSvc.updatePlatformEmpVO(platformEmpVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		platformEmpVO = platformEmpSvc.getOnePlatformEmp(Integer.valueOf(platformEmpVO.getpEmpId()));
		model.addAttribute("platformEmpVO", platformEmpVO);
		return "pFunction/platformEmp/listOnePlatformEmp"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("platformEmpListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<PlatformEmpVO> referenceListData(Model model) {

		List<PlatformEmpVO> list = platformEmpSvc.getAll();
		return list;
	}


}
