package com.cha104g1.freshtown_springboot.picking.controller;

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

import com.cha104g1.freshtown_springboot.picking.service.PickingService;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;

@Controller
@RequestMapping("sFunction/picking")
public class PickingController {
	
	@Autowired
	PickingService pickingSvc;
	
	@GetMapping("addPicking")
	public String addPicking(ModelMap model) {
		PickingVO pickingVO = new PickingVO();
		model.addAttribute("pickingVO", pickingVO);
		System.out.println("轉交請求");
		return "sFunction/picking/addPicking";
	}
	
	@PostMapping("insert")
	public String insert(@Valid PickingVO pickingVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/picking/addPicking";
		}
		/*************************** 2.開始新增資料 *****************************************/
		pickingSvc.addPicking(pickingVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<PickingVO> list = pickingSvc.getAll();
		model.addAttribute("pickinglListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:sFunction/picking/listAllPicking"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("PickingNo") String pickingNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		PickingVO pickingVO = pickingSvc.getOnePicking(Integer.valueOf(pickingNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("pickingVO", pickingVO);
		System.out.println("修改成功1");
		return "sFunction/picking/update_picking_input"; // 查詢完成後轉交update_emp_input.html
	}
	@PostMapping("update")
	public String update(@Valid PickingVO pickingVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/picking/update_picking_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		pickingSvc.updatePicking(pickingVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		pickingVO = pickingSvc.getOnePicking(Integer.valueOf(pickingVO.getPickingNo()));
		model.addAttribute("pickingVO", pickingVO);
		return "sFunction/picking/listOnePicking"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("pickingListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<PickingVO> referenceListData(Model model) {

		List<PickingVO> list = pickingSvc.getAll();
		return list;
	}
}