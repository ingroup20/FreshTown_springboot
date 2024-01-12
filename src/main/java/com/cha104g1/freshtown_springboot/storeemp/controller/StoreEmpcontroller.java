package com.cha104g1.freshtown_springboot.storeemp.controller;

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

import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;



@Controller
@RequestMapping("/sFunction/storeEmp")
public class StoreEmpcontroller {
	
	@Autowired
	StoreEmpService storeEmpSvc;
	private String storeEmpId;
	
	
	@GetMapping("addStoreEmp")
	public String addCStoreEmp(ModelMap model) {
		StoreEmpVO storeEmpVO = new StoreEmpVO();
		model.addAttribute("storeEmpVO", storeEmpVO);
		System.out.println("轉交請求");
		return "sFunction/storeEmp/addStoreEmp";
	}

	@PostMapping("insert")
	public String insert(@Valid StoreEmpVO storeEmpVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/storeEmp/addStoreEmp";
		}
		/*************************** 2.開始新增資料 *****************************************/
		storeEmpSvc.addStoreEmpVO(storeEmpVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StoreEmpVO> list = storeEmpSvc.getAll();
		model.addAttribute("storeEmpListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:sFunction/storeEmp/listAllStoreEmp"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("storeEmpId") String storeEmpId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		StoreEmpVO storeEmpVO = storeEmpSvc.getOneStoreEmp(Integer.valueOf(storeEmpId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("storeEmpVO", storeEmpVO);
		System.out.println("修改成功1");
		return "sFunction/storeEmp/update_storeEmp_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid StoreEmpVO storeEmpVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/storeEmp/update_storeEmp_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		storeEmpSvc.updateStoreEmpVO(storeEmpVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		storeEmpVO = storeEmpSvc.getOneStoreEmp(Integer.valueOf(storeEmpVO.getsEmpId()));
		model.addAttribute("storeEmpVO", storeEmpVO);
		return "sFunction/storeEmp/listOneStoreEmp"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("storeEmpListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<StoreEmpVO> referenceListData(Model model) {

		List<StoreEmpVO> list = storeEmpSvc.getAll();
		return list;
	}


}
