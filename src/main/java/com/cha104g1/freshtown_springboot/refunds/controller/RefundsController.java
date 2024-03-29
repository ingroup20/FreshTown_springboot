package com.cha104g1.freshtown_springboot.refunds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.*;

import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

@Controller
@RequestMapping("pFunction/refunds")
public class RefundsController {

	@Autowired
	RefundsService refundsSvc;
	@Autowired
	OrdersService ordersSvc;

	@ModelAttribute("ordersListDataP")
	protected List<OrdersVO> getOrdersListDataP() {
		System.out.println("取得所有訂單資料");
		List<OrdersVO> orderslist = ordersSvc.getAll();
		return orderslist;
	}
	
	@ModelAttribute("refundsListData")
	protected List<RefundsVO> getRefundsListData() {
		System.out.println("取得所有退款資料");
		List<RefundsVO> refundsList = refundsSvc.getAll();
		for(RefundsVO id:refundsList) {
			System.out.println("id="+id.getId());
		}
		return refundsList;
	}
	
	//==================================
	@GetMapping("select_page")
	public String selectRefunds(Model model) {
		return "/pFunction/refunds/select_page";
	}
	//=================================

	@PostMapping("updateRefundState")
	public String refundDone(String idJson,Model model) {
		String[] idArray=idJson.split(",");
		for (String id : idArray) {
			RefundsVO refundsVO = refundsSvc.getOneRefunds(Integer.valueOf(id));
			refundsVO.setRefundState("Y");
			refundsSvc.updateRefunds(refundsVO);
		}	
		return "pFunction/refunds/select_page";
	}
	
	
	
	
	@GetMapping("listAllRefunds")
	public String listAllRefunds( ModelMap model) {
	
		return "pFunction/refunds/listAllRefunds"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addRefunds")
	public String addRefunds(ModelMap model) {
		RefundsVO refundsVO = new RefundsVO();
		model.addAttribute("refundsVO", refundsVO);
		return "pFunction/refunds/addRefunds";
	}

//
//	@PostMapping("insert")
//	public String insert(@Valid RefundsVO refundsVO, BindingResult result, ModelMap model,
//			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {
//
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
//		
//		
//		
//
//		if (result.hasErrors() || parts[0].isEmpty()) {
//			return "pFunction/refunds/addRefunds";
//		}
//		/*************************** 2.開始新增資料 *****************************************/
//		// EmpService empSvc = new EmpService();
//		refundsSvc.addRefunds(refundsVO);
//		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
//		List<RefundsVO> list = refundsSvc.getAll();
//		model.addAttribute("refundsListData", list);
//		model.addAttribute("success", "- (新增成功)");
//		return "redirect:/refunds/listAllRefunds"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
//	}
//
//	/*
//	 * This method will be called on listAllEmp.html form submission, handling POST request
//	 */
//	@PostMapping("getOne_For_Update")
//	public String getOne_For_Update(@RequestParam("id") String id, ModelMap model) {
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//		/*************************** 2.開始查詢資料 *****************************************/
//		// EmpService empSvc = new EmpService();
//		RefundsVO refundsVO = refundsSvc.getOneRefunds(Integer.valueOf(id));
//
//		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
//		model.addAttribute("refundsVO", refundsVO);
//		System.out.println("修改成功1");
//		return "pFunction/refunds/update_refunds_input"; // 查詢完成後轉交update_emp_input.html
//	}
//
//	/*
//	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
//	 */
//	@PostMapping("update")
//	public String update(@Valid RefundsVO refundsVO, BindingResult result, ModelMap model, @RequestParam("refundDate") String refundDate) throws IOException {
//
//		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
//		
//
//		
//		
//		
//		
//		if (result.hasErrors()) {
//			System.out.println("資料不全");
//			return "pFunction/refunds/update_refunds_input";
//		}
//		/*************************** 2.開始修改資料 *****************************************/
//		// EmpService empSvc = new EmpService();
//		refundsVO.setRefundDate(java.sql.Date.valueOf(refundDate));
//		refundsSvc.updateRefunds(refundsVO);
//		System.out.println("修改成功2");
//		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
//		model.addAttribute("success", "- (修改成功)");
//		refundsVO = refundsSvc.getOneRefunds(Integer.valueOf(refundsVO.getId()));
//		model.addAttribute("refundsVO", refundsVO);
//		return "pFunction/refunds/listOneRefunds"; // 修改成功後轉交listOneEmp.html
//	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
//	@PostMapping("delete")


	

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${deptListData}" itemValue="deptno" itemLabel="dname" />
	 */


	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : 
	 * <form:select path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("ordersMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(1, "線上刷卡");
//		map.put(2, "LinePay");
//		map.put(3, "街口支付");
//		map.put(4, "現場付現");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄

	
	
	
	
	
	
	
	
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("listRefunds_ByCompositeQuery")
	public String listAllRefunds(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<RefundsVO> list = refundsSvc.getAll(map);
		model.addAttribute("refundsListData", list); // for listAllEmp.html 第85行用
		for(RefundsVO rs: list) {
			System.out.println(rs.getOrdersVO().getOrderId());
		}
		return "pFunction/refunds/listAllRefunds";
	}

}