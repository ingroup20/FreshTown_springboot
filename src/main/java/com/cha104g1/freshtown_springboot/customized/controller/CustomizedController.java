package com.cha104g1.freshtown_springboot.customized.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;

@Controller
@RequestMapping("/sFunction/customized")
public class CustomizedController {
	
	@Autowired
	MealsService mealsSvc;
	
	@Autowired
	CustomizedItemsService customizedItemsSvc;
	
	@Autowired
	CustomizedService customizedSvc;

	@Autowired
	MealTypeService mealTypeSvc;
	
	@GetMapping("addCustomized")
	public String addCustomized(HttpServletRequest req , HttpSession session, ModelMap model) {
		CustomizedVO customizedVO = new CustomizedVO();
		model.addAttribute("customizedVO", customizedVO);		
		
		MealsVO mealsVO = (MealsVO) session.getAttribute("mealsVO");
		model.addAttribute("mealsVO", mealsVO);
	    System.out.println(mealsVO.getMealName());

	    Integer mealNo = (Integer) session.getAttribute("mealNo");
	    System.out.println(mealNo);
	    model.addAttribute("mealNo", mealNo); // 將 mealNo 添加到 ModelMap 中
	    
		List<CustomizedVO> customizedList = customizedSvc.getAll(mealNo);
		model.addAttribute("customizedList", customizedList);

		return "sFunction/customized/addCustomized";
	}
	
	@PostMapping("insert")
	public String insert(@Valid CustomizedVO customizedVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if(customizedVO.getMealNo()==null)
			System.out.println("No");
		if(customizedVO.getCustedItemsNo()==null)
			System.out.println("ItNo");
		if(customizedVO.getCustedStatus()==null)
			System.out.println("Status");
		
		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "sFunction/customized/addCustomized";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customizedSvc.addCustomizedVO(customizedVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomizedVO> list = customizedSvc.getAll();
		model.addAttribute("customizedListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/sFunction/customized/listAllCustomized"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}
	

//	@PostMapping("insert")
//	public String insert(@Valid @ModelAttribute("customizedList") List<CustomizedVO> customizedList, BindingResult result, ModelMap model, HttpSession session) throws IOException {
//	    if (result.hasErrors()) {
//	        System.out.println("資料有誤");
//	        return "sFunction/customized/addCustomized";
//	    }
//
//	    // 獲取 mealNo
//	    Integer mealNo = (Integer) session.getAttribute("mealNo");
//
//	    // 遍歷 CustomizedVO 列表，逐個插入資料庫
//	    for (CustomizedVO customizedVO : customizedList) {
//	        // 設定 mealNo
//	        customizedVO.setMealNo(mealNo);
//
//	        // 開始新增資料
//	        customizedSvc.addCustomizedVO(customizedVO);
//	    }
//
//	    // 其他處理邏輯...
//
//	    List<CustomizedVO> list = customizedSvc.getAll();
//	    model.addAttribute("customizedListData", list);
//	    model.addAttribute("success", "- (新增成功)");
//	    return "redirect:/sFunction/customized/listAllCustomized";
//	}



	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("mealNo") String mealNo, @RequestParam("custedItemsNo") String custedItemsNo, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		CustomizedVO customizedVO = customizedSvc.getCustomizedVOByCompositeKey(Integer.valueOf(mealNo), Integer.valueOf(custedItemsNo));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("customizedVO", customizedVO);
		return "sFunction/customized/update_customized_input"; // 查詢完成後轉交update_emp_input.html
	}
	
	@PostMapping("update")
	public String update(@Valid CustomizedVO customizedVO, BindingResult result, ModelMap model) throws IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/customized/update_customized_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		customizedSvc.updateCustomizedVO(customizedVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		customizedVO = customizedSvc.getCustomizedVOByCompositeKey(Integer.valueOf(customizedVO.getMealNo()), Integer.valueOf(customizedVO.getCustedItemsNo()));
		model.addAttribute("customizedVO", customizedVO);
		return "sFunction/customized/listOneCustomized"; // 修改成功後轉交listOneEmp.html
	}
	//複合查詢
	@PostMapping("listCustomized_ByCompositeQuery")
	public String listAllCustomized(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<CustomizedVO> list = customizedSvc.getCustomizedVOByCompositeQuery(map);
		model.addAttribute("customizedListData", list); // for listAllEmp.html 第85行用
		for(CustomizedVO rs: list) {
			System.out.println(rs.getCompositeKey());
		}//會報錯
		return "sFunction/customized/listAllCustomized";
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

	@ModelAttribute("mealTypeListData2") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MealTypeVO> referenceListData3(Model model) {

		List<MealTypeVO> list = mealTypeSvc.getAll();
		return list;
	}
	
}