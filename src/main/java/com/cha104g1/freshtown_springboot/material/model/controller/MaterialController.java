package com.cha104g1.freshtown_springboot.material.model.controller;

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

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;

@Controller
@RequestMapping("sFunction/material")
public class MaterialController {
	
	@Autowired
	MaterialService materialSvc;
	
	@GetMapping("addMaterial")
	public String addMaterial(ModelMap model) {
		MaterialVO materialVO = new MaterialVO();
		model.addAttribute("materialVO", materialVO);
		System.out.println("轉交請求");
		return "sFunction/material/addMaterial";
	}
	
	@PostMapping("insert")
	public String insert(@Valid MaterialVO materialVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/material/addMaterial";
		}
		/*************************** 2.開始新增資料 *****************************************/
		materialSvc.addMaterial(materialVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<MaterialVO> list = materialSvc.getAll();
		model.addAttribute("materialListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:sFunction/material/listAllMaterial"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("itemNumber") String itemNumber, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		MaterialVO materialVO = materialSvc.getOneMaterial(Integer.valueOf(itemNumber));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("materialVO", materialVO);
		System.out.println("修改成功1");
		return "sFunction/material/update_material_input"; // 查詢完成後轉交update_emp_input.html
	}
	@PostMapping("update")
	public String update(@Valid MaterialVO materialVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/material/update_material_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		materialSvc.updateMaterial(materialVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		materialVO = materialSvc.getOneMaterial(Integer.valueOf(materialVO.getItemNumber()));
		model.addAttribute("materialVO", materialVO);
		return "sFunction/material/listOneMaterial"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("materialListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MaterialVO> referenceListData(Model model) {

		List<MaterialVO> list = materialSvc.getAll();
		return list;
	}
}
