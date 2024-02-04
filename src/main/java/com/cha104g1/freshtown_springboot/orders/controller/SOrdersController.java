package com.cha104g1.freshtown_springboot.orders.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderService;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailService;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
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
	@Autowired
	RefundsService refundsSvc;
	@Autowired
	OrderDetailService orderDetailSvc;
	@Autowired
	CustomizedOrderService customizedOrderSvc;
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	 
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
	
	@ModelAttribute("orderDetailList") // for select_page.html 第135行用
	protected Map<OrdersVO,List<OrderDetailVO>> getOrderDetailList(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		Object object =session.getAttribute("storeEmpLogin");	
		StoreEmpVO storeEmpVO = (StoreEmpVO) object;
		
		Map<OrdersVO,List<OrderDetailVO>> orderDetailList = new HashMap<>();

        List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
        for(OrdersVO ordersVO :list) {
        	List<OrderDetailVO> oDetailVOList = orderDetailSvc.getAllByOrderId(ordersVO.getOrderId());
        	orderDetailList.put(ordersVO, oDetailVOList);

        }
        System.out.println(list.size());
        return orderDetailList;
	}
	
//	@ModelAttribute("customizedOrderList") // for select_page.html 第135行用
//	protected Map<CustomizedOrderVO,List<CustomizedDetailVO>> getCustomizedOrderList(HttpServletRequest req,Model model) {
//		HttpSession session = req.getSession();
//		Object object =session.getAttribute("storeEmpLogin");	
//		StoreEmpVO storeEmpVO = (StoreEmpVO) object;
//		
//		Map<CustomizedOrderVO,List<CustomizedDetailVO>> customizedOrderList = new HashMap<>();
//
//        List<CustomizedOrderVO> list = customizedOrderSvc.getAll();
////        for(CustomizedOrderVO customizedOrderVO :list) {
////        	if(customizedOrderVO.getOrderDetailVO())
////        	List<CustomizedDetailVO> cDetailVOList = customizedDetailSvc.getAll();
////        	orderDetailList.put(ordersVO, oDetailVOList);
////
////        }
//        System.out.println(list.size());
//        return orderDetailList;
//	}
	
	
	//=====================================
	
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
	
	@PostMapping("refundOrder")
	public String creatRefundOrder(HttpServletRequest req,String orderId, ModelMap model) throws IOException {
		System.out.println("進入1");
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		OrdersVO ordersVO =ordersSvc.getOneOrders(Integer.valueOf(orderId));
		/*************************** 2.開始修改資料 *****************************************/
		ordersVO.setPayState(2);
		ordersVO.setOrderState(5);
		ordersSvc.updateOrders(ordersVO);
		System.out.println("修改成功2");
		
		RefundsVO refundsVO = new RefundsVO();
		refundsVO.setOrdersVO(ordersVO);
		refundsVO.setRefundState("N");
		refundsVO.setRefundDollar(ordersVO.getTotalPrice());
		
		 LocalDate currentDate = LocalDate.now();
         // 將 LocalDate 轉換為 java.sql.Date
         java.sql.Date Date = java.sql.Date.valueOf(currentDate);
		refundsVO.setCreationDate(Date);
		try {
			refundsSvc.addRefunds(refundsVO);
		}catch(Exception e) {
			System.err.println("e");
		}
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (申請成功)");
		HttpSession session = req.getSession();
		Object object =session.getAttribute("storeEmpLogin");	
		StoreEmpVO storeEmpVO = (StoreEmpVO) object;
		List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
		model.addAttribute("ordersListDataS",list);
		return "sFunction/orders/select_page"; // 修改成功後轉交listOneEmp.html
	}
	
	
	@PostMapping("editDelayDesc")
	public String editDelayDesc(HttpServletRequest req,String orderId,String message, ModelMap model) throws IOException {
		System.out.println("進入21");
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		OrdersVO ordersVO =ordersSvc.getOneOrders(Integer.valueOf(orderId));
		/*************************** 2.開始修改資料 *****************************************/
		ordersVO.setDelayDesc(message);
		ordersSvc.updateOrders(ordersVO);
		System.out.println("修改成功2");
		
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (申請成功)");
		HttpSession session = req.getSession();
		Object object =session.getAttribute("storeEmpLogin");	
		StoreEmpVO storeEmpVO = (StoreEmpVO) object;
		List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
		model.addAttribute("ordersListDataS",list);
		return "sFunction/orders/select_page"; // 修改成功後轉交listOneEmp.html
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
