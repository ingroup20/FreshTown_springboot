package com.cha104g1.freshtown_springboot.meals.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
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

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@RequestMapping("/pFunction/meals")
public class MealsController {

	@Autowired
	MealsService mealsSvc;
	
	@Autowired
	MealTypeService mealTypeSvc;

	@Autowired
	StoresService storesSvc;
	

	@GetMapping("addMeals")
	public String addMeals(ModelMap model) {
		MealsVO mealsVO = new MealsVO();
		model.addAttribute("mealsVO", mealsVO);
		return "pFunction/meals/addMeals";
	}
	
	@PostMapping("insert")
	public String insert(@Valid MealsVO mealsVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/meals/addMeals";
		}
		/*************************** 2.開始新增資料 *****************************************/
		mealsSvc.addMealsVO(mealsVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<MealsVO> list = mealsSvc.getAll();
		model.addAttribute("mealsListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:pFunction/meals/listAllMeals"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("mealNo") String mealNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("mealsVO", mealsVO);
		System.out.println("修改成功1");
		return "pFunction/meals/update_meals_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid MealsVO mealsVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/meals/update_meals_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		mealsSvc.updateMealsVO(mealsVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealsVO.getMealNo()));
		model.addAttribute("mealsVO", mealsVO);
		return "pFunction/meals/listOneMeals"; // 修改成功後轉交listOneEmp.html
	}
	
	
	//複合查詢
	@PostMapping("listMeals_ByCompositeQuery")
	public String listAllMeals(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<MealsVO> list = mealsSvc.getMealsVOByCompositeQuery(map);
		model.addAttribute("mealsListData", list); // for listAllEmp.html 第85行用
		for(MealsVO rs: list) {
			System.out.println(rs.getMealNo());
		}
		return "pFunction/meals/listAllMeals";
	}
	// 全資料一覽
		@ModelAttribute("mealTypeListData2") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
		protected List<MealTypeVO> referenceListData(Model model) {

			List<MealTypeVO> list = mealTypeSvc.getAll();
			return list;
		}
		
		@ModelAttribute("storesListData2") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
		protected List<StoresVO> referenceListData1(Model model) {
			
			List<StoresVO> list = storesSvc.getAll();
			return list;
		}
}
