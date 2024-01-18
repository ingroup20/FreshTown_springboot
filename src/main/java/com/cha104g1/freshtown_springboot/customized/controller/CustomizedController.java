package com.cha104g1.freshtown_springboot.customized.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;

@Controller
@RequestMapping("/pFunction/customized")
public class CustomizedController {
	
	@Autowired
	MealsService mealsSvc;
	
	@Autowired
	CustomizedItemsService customizedItemsSvc;
	
	@Autowired
	CustomizedService customizedSvc;
	
	@GetMapping("addCustomized")
	public String addCustomized(ModelMap model) {
		CustomizedVO customizedVO = new CustomizedVO();
		model.addAttribute("customizedVO", customizedVO);
		return "pFunction/customized/addCustomized";
	}
	
	@PostMapping("insert")
	public String insert(@Valid CustomizedVO customizedVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/customized/addCustomized";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customizedSvc.addCustomizedVO(customizedVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomizedVO> list = customizedSvc.getAll();
		model.addAttribute("customizedListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:pFunction/customized/listAllCustomized"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("mealNo") String mealNo, @RequestParam("custedItemsNo") String custedItemsNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		CustomizedVO customizedVO = customizedSvc.getCustomizedVOByCompositeKey(Integer.valueOf(mealNo), Integer.valueOf(custedItemsNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("customizedVO", customizedVO);
		return "pFunction/customized/update_customized_input"; // 查詢完成後轉交update_emp_input.html
	}
	
	@PostMapping("update")
	public String update(@Valid CustomizedVO customizedVO, BindingResult result, ModelMap model) throws IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/customized/update_customized_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		customizedSvc.updateCustomizedVO(customizedVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		customizedVO = customizedSvc.getCustomizedVOByCompositeKey(Integer.valueOf(customizedVO.getMealNo()), Integer.valueOf(customizedVO.getCustedItemsNo()));
		model.addAttribute("customizedVO", customizedVO);
		return "pFunction/customized/listOneCustomized"; // 修改成功後轉交listOneEmp.html
	}
	//複合查詢
		@PostMapping("listCustomized_ByCompositeQuery")
		public String listAllCustomized(HttpServletRequest req, Model model) {
			Map<String, String[]> map = req.getParameterMap();
			List<CustomizedVO> list = customizedSvc.getCustomizedVOByCompositeQuery(map);
			model.addAttribute("customizedListData", list); // for listAllEmp.html 第85行用
			for(CustomizedVO rs: list) {
				System.out.println(rs.getCompositeKey());
			}
			return "pFunction/customized/listAllCustomized";
		}
	// 全資料一覽
	@ModelAttribute("customizedListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomizedVO> referenceListData(Model model) {

		List<CustomizedVO> list = customizedSvc.getAll();
		return list;
	}
	
	@ModelAttribute("customizedItemsListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomizedItemsVO> referenceListData1(Model model) {
		
		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		return list;
	}
	
	@ModelAttribute("mealsListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MealsVO> referenceListData2(Model model) {
		
		List<MealsVO> list = mealsSvc.getAll();
		return list;
	}
}
