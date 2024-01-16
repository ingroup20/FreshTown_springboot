package com.cha104g1.freshtown_springboot.itemsclass.model.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.service.ItemsClassService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

@Controller
@RequestMapping("/sFunction/itemsclass")
public class ItemsClassController {

	@Autowired
	ItemsClassService itemsClassSvc;
	
	
	@GetMapping("addItemsClass")
	public String addItemsClass(ModelMap model) {
		ItemsClassVO itemsClassVO = new ItemsClassVO();
		model.addAttribute("itemsClassVO", itemsClassVO);
		System.out.println("轉交請求");
		return "sFunction/itemsclass/addItemsClass";
	}

	@PostMapping("insert")
	public String insert(@Valid ItemsClassVO itemsClassVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/itemsclass/addItemsClass";
		}
		/*************************** 2.開始新增資料 *****************************************/
		itemsClassSvc.addItemsClass(itemsClassVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<ItemsClassVO> list = itemsClassSvc.getAll();
		model.addAttribute("itemsClassListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:sFunction/itemsclass/listAllItemsClass"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("itemClassId") String itemClassId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		ItemsClassVO itemsClassVO = itemsClassSvc.getOneItemsClass(Integer.valueOf(itemClassId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("itemsClassVO", itemsClassVO);
		System.out.println("修改成功1");
		return "sFunction/itemsclass/update_itemsclass_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid ItemsClassVO itemsClassVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/itemsclass/update_itemsclass_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		itemsClassSvc.updateItemsClass(itemsClassVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		itemsClassVO = itemsClassSvc.getOneItemsClass(Integer.valueOf(itemsClassVO.getItemClassId()));
		model.addAttribute("itemsClassVO", itemsClassVO);
		return "sFunction/itemsclass/listOneItemsClass"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("itemsClassListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<ItemsClassVO> referenceListData(Model model) {

		List<ItemsClassVO> list = itemsClassSvc.getAll();
		return list;
	}

}
