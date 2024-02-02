package com.cha104g1.freshtown_springboot.meals.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;
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
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@RequestMapping("/sFunction/meals")
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
		return "sFunction/meals/addMeals";
	}
	
	@PostMapping("insert")
	public String insert(@Valid MealsVO mealsVO, BindingResult result, HttpServletRequest req , ModelMap model,
			@RequestParam("mealPicture") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(mealsVO, result, "mealPicture");
	
		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "商品照片: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				mealsVO.setMealPicture(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			System.out.println("資料有誤");
			return "sFunction/meals/addMeals";
		}
		/*************************** 2.開始新增資料 *****************************************/
		mealsSvc.addMealsVO(mealsVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
//		List<MealsVO> list = mealsSvc.getAll();
//		model.addAttribute("mealsListData", list);
//		model.addAttribute("success", "- (新增成功)");
		Integer mealNo = mealsVO.getMealNo();
		model.addAttribute("mealNo", mealNo);
		model.addAttribute("mealsVO", mealsVO);

		
		HttpSession session =req.getSession();
		session.setAttribute("mealNo", mealNo);
		session.setAttribute("mealsVO", mealsVO);
//		return "sFunction/customized/addCustomized";
		return "redirect:/sFunction/customized/addCustomized"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
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
		return "sFunction/meals/update_meals_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid MealsVO mealsVO, BindingResult result, ModelMap model,
			@RequestParam("mealPicture") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(mealsVO, result, "mealPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			// EmpService empSvc = new EmpService();
			byte[] mealPicture = mealsSvc.getMealsVOByMealNo(mealsVO.getMealNo()).getMealPicture();
			mealsVO.setMealPicture(mealPicture);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] mealPicture = multipartFile.getBytes();
				mealsVO.setMealPicture(mealPicture);
			}
		}
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/meals/update_meals_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		mealsSvc.updateMealsVO(mealsVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealsVO.getMealNo()));
		model.addAttribute("mealsVO", mealsVO);
		return "sFunction/meals/listOneMeals"; // 修改成功後轉交listOneEmp.html
	}
	
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(MealsVO mealsVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(mealsVO, "mealsVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	//複合查詢
	@PostMapping("listMeals_ByCompositeQuery")
	public String listAllMeals(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String[] values = entry.getValue();

		    System.out.print("Key: " + key + ", Values: ");
		    
		    if (values != null) {
		        for (String value : values) {
		            System.out.print(value + " ");
		        }
		    }
		    
		    System.out.println(); // 换行
		}

		List<MealsVO> list = mealsSvc.getAll(map);
		model.addAttribute("mealsListData", list); // for listAllEmp.html 第85行用
		for(MealsVO rs: list) {
			System.out.println(rs.getMealNo());
		}
		return "sFunction/meals/listAllMeals";
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
		
		@ModelAttribute("storesVO") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
		protected StoresVO storesVO(HttpServletRequest req , Model model) {
			HttpSession session = req.getSession(false);
			Object idVO =session.getAttribute("storeEmpLogin");
			StoreEmpVO storeEmpVO= (StoreEmpVO)idVO;
			if (storeEmpVO == null) {
				System.out.println("出現null啦");
			}
			StoresVO storesVO = storeEmpVO.getStoresVO();
			return storesVO;
		}
		
		@ModelAttribute("storeId") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
		protected Integer storeId(HttpServletRequest req , Model model) {
			HttpSession session = req.getSession(false);
			Object idVO =session.getAttribute("storeEmpLogin");
			StoreEmpVO storeEmpVO= (StoreEmpVO)idVO;
			if (storeEmpVO == null) {
				System.out.println("出現null啦");
			}
			Integer storeId = storeEmpVO.getStoresVO().getStoreId();
			System.out.println(storeId);
			return storeId;
		}
		
		
}
