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
import org.springframework.web.bind.annotation.ResponseBody;

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
	@GetMapping("update_customized_input")
	public String updateCustomizedInput(HttpServletRequest req , HttpSession session, ModelMap model) {
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
		
		return "sFunction/customized/update_customized_input";
	}

	// 在Controller中维护一个状态，表示当前表单的索引
	private int currentIndex = 0;
	
	@PostMapping("insert")
//	@ResponseBody
	public String insert(@Valid CustomizedVO customizedVO, BindingResult result, HttpServletRequest req , ModelMap model) throws IOException {

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
		
		Integer mealNo = customizedVO.getMealNo();
		model.addAttribute("mealNo", mealNo);
		HttpSession session =req.getSession();
		session.setAttribute("mealNo", mealNo);
		
//		return "sFunction/customized/addCustomized"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
		currentIndex++; // 递增索引
		
		List<CustomizedItemsVO> list2 = customizedItemsSvc.getAll();
		int totalNumberOfForms = list2.size();
		System.out.println(totalNumberOfForms);

	    if (currentIndex < totalNumberOfForms) {
	        // 还有下一个表单需要提交
	    	System.out.println(currentIndex);
	        return "sFunction/customized/addCustomized";
	    } else {
	        // 所有表单已提交完成，重置索引，并跳转到指定页面
	        currentIndex = 0;
	        return "redirect:/sFunction/customized/listAllCustomized";
	    }
	}
	

	@PostMapping("batchInsert")
	public String batchInsert(@RequestParam Map<String, String> requestParams, HttpSession session, ModelMap model) throws IOException {

		Integer mealNo = (Integer) session.getAttribute("mealNo");
		// 日誌輸出
	    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
	        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
	    }
	    
	    List<CustomizedVO> customizedVOList = new ArrayList<>();

	    // 根据表单数量循环获取数据
	    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
//	    	System.out.println("111");
	        
	        if (entry.getKey().startsWith("custedStatus_")) {
	        	
	            CustomizedVO customizedVO = new CustomizedVO();
	            
	            String custedItemsNo = entry.getKey().replace("custedStatus_", "");
	            String custedStatus = entry.getValue();
								
	            customizedVO.setMealNo(mealNo);
	            customizedVO.setCustedItemsNo(Integer.parseInt(custedItemsNo));
	            customizedVO.setCustedStatus(Integer.parseInt(custedStatus));

	            // 添加其他需要设置的属性

	            customizedVOList.add(customizedVO);
	        }
	    }
	    for (CustomizedVO customizedVO : customizedVOList) {
	        System.out.println("MealNo: " + customizedVO.getMealNo() + ", CustedItemsNo: " + customizedVO.getCustedItemsNo() + ", CustedStatus: " + customizedVO.getCustedStatus());
	    }

	    // 批量新增
	    for (CustomizedVO customizedVO : customizedVOList) {
	        // 执行新增操作，你的新增逻辑
	        System.out.println(customizedVO.getCustedItemsNo());
	        customizedSvc.addCustomizedVO(customizedVO);
	    }

	    // 重定向到指定页面
//	    return "redirect:/sFunction/customized/listAllCustomized";
	    return "redirect:/sFunction/meals/listAllMeals";
	}




	
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
	
	@PostMapping("batchUpdate")
	public String batchUpdate(@RequestParam Map<String, String> requestParams, HttpSession session, ModelMap model) throws IOException {

		Integer mealNo = (Integer) session.getAttribute("mealNo");
		// 日誌輸出
	    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
	        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
	    }
	    
	    List<CustomizedVO> customizedVOList = new ArrayList<>();

	    // 根据表单数量循环获取数据
	    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
//	    	System.out.println("111");
	        
	        if (entry.getKey().startsWith("custedStatus_")) {
	        	
	            CustomizedVO customizedVO = new CustomizedVO();
	            
	            String custedItemsNo = entry.getKey().replace("custedStatus_", "");
	            String custedStatus = entry.getValue();
								
	            customizedVO.setMealNo(mealNo);
	            customizedVO.setCustedItemsNo(Integer.parseInt(custedItemsNo));
	            customizedVO.setCustedStatus(Integer.parseInt(custedStatus));

	            // 添加其他需要设置的属性

	            customizedVOList.add(customizedVO);
	        }
	    }
	    for (CustomizedVO customizedVO : customizedVOList) {
	        System.out.println("MealNo: " + customizedVO.getMealNo() + ", CustedItemsNo: " + customizedVO.getCustedItemsNo() + ", CustedStatus: " + customizedVO.getCustedStatus());
	    }

	    // 批量新增
	    for (CustomizedVO customizedVO : customizedVOList) {
	        // 执行新增操作，你的新增逻辑
	        System.out.println(customizedVO.getCustedItemsNo());
	        customizedSvc.updateCustomizedVO(customizedVO);
	    }

	    // 重定向到指定页面
//	    return "redirect:/sFunction/customized/listAllCustomized";
	    return "redirect:/sFunction/meals/listAllMeals";
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