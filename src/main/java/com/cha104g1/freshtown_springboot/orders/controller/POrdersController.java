package com.cha104g1.freshtown_springboot.orders.controller;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;

@Controller
@Validated
@RequestMapping("/pFunction/orders")
public class POrdersController {

	@Autowired
	OrdersService ordersSvc;

	@Autowired
	StoresService storesSvc;
	//==========================================
	
	
	
	
	
	
	
	
	
	
	//================================================
	@GetMapping("orders/select_page")
	public String selectOrders(Model model) {
		return "/pFunction/orders/select_page";
	}
	
	@GetMapping("atm/select_page")
	public String selectATM(Model model) {
		return "/pFunction/atm/select_page";
	}
	
	//======================================================

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("orderId") String orderId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		OrdersVO ordersVO = ordersSvc.getOneOrders(Integer.valueOf(orderId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("ordersVO", ordersVO);
		System.out.println("修改成功1");
		return "pFunction/orders/update_orders_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid OrdersVO ordersVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "pFunction/orders/update_orders_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		ordersSvc.updateOrders(ordersVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		ordersVO = ordersSvc.getOneOrders(Integer.valueOf(ordersVO.getOrderId()));
		model.addAttribute("ordersVO", ordersVO);
		return "pFunction/orders/listOneOrders"; // 修改成功後轉交listOneEmp.html
	}
	
	
	//複合查詢
	@PostMapping("listOrders_ByCompositeQuery")
	public String listAllOrders(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<OrdersVO> list = ordersSvc.getAll(map);
		model.addAttribute("ordersListData", list); // for listAllEmp.html 第85行用
		for(OrdersVO rs: list) {
			System.out.println(rs.getOrderId());
		}
		return "pFunction/orders/listAllOrders";
	}

	
	
	
}
