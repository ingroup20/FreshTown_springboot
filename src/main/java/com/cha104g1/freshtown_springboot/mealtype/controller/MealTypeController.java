package com.cha104g1.freshtown_springboot.mealtype.controller;

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

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

@Controller
@RequestMapping("/mealtype")
public class MealTypeController {

	@Autowired
	MealTypeService mealTypeSvc;
	
	
	@GetMapping("addMealType")
	public String addMealType(ModelMap model) {
		MealTypeVO mealTypeVO = new MealTypeVO();
		model.addAttribute("mealTypeVO", mealTypeVO);
		System.out.println("轉交請求");
		return "pFunction/mealtype/addMealType";
	}

	@PostMapping("insert")
	public String insert(@Valid MealTypeVO mealTypeVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/mealtype/addMealType";
		}
		/*************************** 2.開始新增資料 *****************************************/
		mealTypeSvc.addMealType(mealTypeVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<MealTypeVO> list = mealTypeSvc.getAll();
		model.addAttribute("mealTypeListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/mealtype/listAllMealType"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("mealTypeNo") String mealTypeNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		MealTypeVO mealTypeVO = mealTypeSvc.getOneMealType(Integer.valueOf(mealTypeNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("mealTypeVO", mealTypeVO);
		System.out.println("修改成功1");
		return "pFunction/mealtype/update_mealtype_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid MealTypeVO mealTypeVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/mealtype/update_mealtype_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		mealTypeSvc.updateMealType(mealTypeVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		mealTypeVO = mealTypeSvc.getOneMealType(Integer.valueOf(mealTypeVO.getMealTypeNo()));
		model.addAttribute("mealTypeVO", mealTypeVO);
		return "pFunction/mealtype/listOneMealType"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("mealTypeListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MealTypeVO> referenceListData(Model model) {

		List<MealTypeVO> list = mealTypeSvc.getAll();
		return list;
	}

}
