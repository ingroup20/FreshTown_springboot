package com.cha104g1.freshtown_springboot.orders.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Controller
@Validated
@RequestMapping("sFunction/orders")
public class SOrdersController {
	
	@Autowired
	OrdersService ordersSvc;

	@Autowired
	StoresService storesSvc;
	
	@ModelAttribute("ordersListDataS") // for select_page.html 第135行用
	protected List<OrdersVO> getListData_OrdersS(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		Object object =session.getAttribute("storeEmpLogin");	
		StoreEmpVO storeEmpVO = (StoreEmpVO) object;
		
        List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
        System.out.println(list.size());
        return list;
		 // 检查类型
//		    if (object instanceof StoreEmpVO) {
//		        // 强制转换为StoresVO类型
//		    	StoreEmpVO storeEmpVO = (StoreEmpVO) object;   
//		        // 使用storesVO进行操作
//		        List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
//		        System.out.println(list.size());
//		        return list;
//		    } else {
//		        // 如果类型不匹配，你可以选择采取适当的措施，例如返回空列表或者抛出异常
//		       System.out.println("xxx");
//		    	return Collections.emptyList();
//		    }
		}
	
	
	
    @GetMapping("select_page")
	public String orderselectpage(Model model) {	
		return "sFunction/orders/select_page";
	}
	
    @GetMapping("orderorders")
	public String orderOrders(Model model) {
		return "sFunction/orders/orderorders";
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("orderId") String orderId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		OrdersVO ordersVO = ordersSvc.getOneOrders(Integer.valueOf(orderId));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("ordersVO", ordersVO);
		System.out.println("修改成功1");
		return "sFunction/orders/update_orders_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid OrdersVO ordersVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "sFunction/orders/update_orders_input";
		}
		/*************************** 2.開始修改資料 *****************************************/

		ordersSvc.updateOrders(ordersVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		ordersVO = ordersSvc.getOneOrders(Integer.valueOf(ordersVO.getOrderId()));
		model.addAttribute("ordersVO", ordersVO);
		return "sFunction/orders/listOneOrders"; // 修改成功後轉交listOneEmp.html
	}
	
	
	//複合查詢
	@PostMapping("storeListOrders_ByCompositeQuery")
	public String storeListAllOrders(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<OrdersVO> list = ordersSvc.getAll(map);

		return "redirect: /freshtown_springboot/sFunction/orders/select_page";
	}

	//==========================================

    

}
