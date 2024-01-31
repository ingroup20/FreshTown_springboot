package com.cha104g1.freshtown_springboot.material.model.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.service.ItemsClassService;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.picking.service.PickingService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@RequestMapping("/sFunction/material")
public class MaterialController {

	@Autowired
	MaterialService materialSvc;

	@Autowired
	ItemsClassService itemsClassSvc;
	
	@Autowired
	PickingService pickingSvc;

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

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${deptListData}" itemValue="deptno"
	 * itemLabel="dname" />
	 */
	@ModelAttribute("itemsClassListData")
	protected List<ItemsClassVO> referenceListData() {
		// DeptService deptSvc = new DeptService();
		List<ItemsClassVO> list = itemsClassSvc.getAll();
		return list;
	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${depMapData}" />
	 */
	@ModelAttribute("itemStatusMapData") //
	protected Map<Integer, String> referenceMapData() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(0, "低於安全庫存");
		map.put(1, "數量足夠");
		map.put(2, "作廢");
		return map;
	}
	
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(MaterialVO materialVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(materialVO, "materialVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 *複合查詢
	 */
	@PostMapping("listMaterial_ByCompositeQuery")
	public String listAllMaterial(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<MaterialVO> list = materialSvc.getAll(map);
		model.addAttribute("materialListData", list); 
		return "sFunction/material/listAllMaterial";
	}

	// 全資料一覽
	@ModelAttribute("materialListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MaterialVO> referenceListData(Model model) {

		List<MaterialVO> list = materialSvc.getAll();
		return list;
	}
	
	
	
	
}
