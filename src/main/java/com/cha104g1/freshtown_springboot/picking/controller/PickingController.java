package com.cha104g1.freshtown_springboot.picking.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.picking.service.PickingService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;

@Controller
@RequestMapping("/sFunction/picking")
public class PickingController {

	@Autowired
	PickingService pickingSvc;

	@Autowired
	MaterialService materialSvc;
	
	@Autowired
	StoresService storesSvc;
	
	@Autowired
	StoreEmpService storeEmpSvc;

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
		model.addAttribute("pickingListData", list);
		model.addAttribute("success", "- (新增成功)");
		System.out.println("有執行");
		return "redirect:sFunction/picking/listAllPicking"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("pickingNo") String pickingNo, ModelMap model) {
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

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/picking/update_picking_input";
		}
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 取sql原始資料
		PickingVO originalPickingVO = pickingSvc.getOnePicking(pickingVO.getPickingNo());

		/*************************** 2.開始修改資料 *****************************************/

		if (originalPickingVO.getPickingStatus().equals(0)) {
			
			if (pickingVO.getPickingStatus().equals(1)) {
				pickingSvc.updatePicking(pickingVO);
				System.out.println("修改成功1");

				// 狀態改成1，審核通過
				System.out.println("原始數量=" + originalPickingVO.getMaterialVO().getStockQuantity());
				Integer quality = originalPickingVO.getMaterialVO().getStockQuantity()
						- (pickingVO.getPickingQuantity());
				System.out.println("減後數量=" + quality);

				MaterialVO materialVO = materialSvc.getOneMaterial(pickingVO.getMaterialVO().getItemNumber());
				materialVO.setStockQuantity(quality);
				
				materialSvc.updateMaterial(materialVO);
				System.out.println("庫存數量=" + materialVO.getStockQuantity());
				System.out.println("庫存數量=" + pickingVO.getPickingUnit());
			} else {
				pickingSvc.updatePicking(pickingVO);
				System.out.println("修改成功2");
		    }
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
	    }
		model.addAttribute("success", "- (修改成功)");
		pickingVO = pickingSvc.getOnePicking(Integer.valueOf(pickingVO.getPickingNo()));
		model.addAttribute("pickingVO", pickingVO);
		return "sFunction/picking/listOnePicking"; // 修改成功後轉交listOneEmp.html
	    }

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${deptListData}" itemValue="deptno"
	 * itemLabel="dname" />
	 */
	@ModelAttribute("materialListData")
	protected List<MaterialVO> referenceListData() {
		// DeptService deptSvc = new DeptService();
		List<MaterialVO> list = materialSvc.getAll();
		return list;
	}
	
	@ModelAttribute("storeEmpListData2") 
	protected List<StoreEmpVO> referenceListData2(Model model) {
		List<StoreEmpVO> list = storeEmpSvc.getAll();
		return list;
	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("pickingStatusMapData") 
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(0, "審核中");
//		map.put(1, "已領取");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(PickingVO pickingVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(pickingVO, "pickingVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request 複合查詢
	 */
	@PostMapping("listPicking_ByCompositeQuery")
	public String listAllPicking(HttpServletRequest req, Model model) {
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
		List<PickingVO> list = pickingSvc.getAll(map);
		model.addAttribute("pickingListData", list);
		for(PickingVO rs: list) {
			System.out.println(rs.getPickingNo());
	}
	return "sFunction/picking/listAllPicking";
}

	// 全資料一覽
	@ModelAttribute("pickingListData")
	protected List<PickingVO> referenceListData(Model model) {

		List<PickingVO> list = pickingSvc.getAll();
		return list;
	}

	@GetMapping("/sFunction/picking/listAllPicking")
	public String listAllPicking(Model model) {
		return "sFunction/picking/listAllPicking";
	}
	
	
	@ModelAttribute("storesVO") 
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
