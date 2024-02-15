package com.cha104g1.freshtown_springboot.storeemp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;



@Controller
@RequestMapping("/sFunction/storeEmp")
public class StoreEmpController {
	
	@Autowired
	StoreEmpService storeEmpSvc;

	
	
	@GetMapping("addStoreEmp")
	public String addStoreEmp(ModelMap model) {
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
		storeEmpSvc.addStoreEmp(storeEmpVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StoreEmpVO> list = storeEmpSvc.getAll();
		model.addAttribute("storeEmpListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "sFunction/storeEmp/listAllStoreEmp"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("sEmpId") String sEmpId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		StoreEmpVO storeEmpVO = storeEmpSvc.getOneStoreEmp(Integer.valueOf(sEmpId));

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
	
	
	// 去除BindingResult中某個欄位的FieldError紀錄
			public BindingResult removeFieldError(StoreEmpVO storeEmpVO, BindingResult result, String removedFieldname) {
				List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
						.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
						.collect(Collectors.toList());
				result = new BeanPropertyBindingResult(storeEmpVO, "storeEmpVO");
				for (FieldError fieldError : errorsListToKeep) {
					result.addError(fieldError);
				}
				return result;
			}
			
			//複合查詢
			@PostMapping("listStoreEmp_ByCompositeQuery")
			public String listAllStoreEmp(HttpServletRequest req, Model model) {
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

				List<StoreEmpVO> list = storeEmpSvc.getAll(map);
				model.addAttribute("storeEmpListData", list); // for listAllEmp.html 第85行用
				for(StoreEmpVO rs: list) {
					System.out.println(rs.getsEmpId());
				}
				return "sFunction/storeEmp/listAllStoreEmp";
			}

	
	

	// 全資料一覽
	@ModelAttribute("storeEmpListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<StoreEmpVO> referenceListData(Model model) {

		List<StoreEmpVO> list = storeEmpSvc.getAll();
		return list;
	}


}
