package com.cha104g1.freshtown_springboot.orders.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
@RequestMapping("/sFunction/manageOrders")
public class SOrdersController {
	
	@Autowired
	OrdersService ordersSvc;

	@Autowired
	StoresService storesSvc;
	
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
	@PostMapping("listOrders_ByCompositeQuery")
	public String listAllOrders(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<OrdersVO> list = ordersSvc.getAll(map);
		model.addAttribute("ordersListData", list); // for listAllEmp.html 第85行用
		for(OrdersVO rs: list) {
			System.out.println(rs.getOrderId());
		}
		return "sFunction/orders/listAllOrders";
	}

	//==========================================

    
	@ModelAttribute("ordersListDataS") // for select_page.html 第135行用
	protected List<OrdersVO> getListData_OrdersS(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession();
		Object object =session.getAttribute("storeEmpLogin");
		System.out.println(object);
		 // 检查类型
	    if (object instanceof StoreEmpVO) {
	        // 强制转换为StoresVO类型
	    	StoreEmpVO storeEmpVO = (StoreEmpVO) object;
	        
	        // 使用storesVO进行操作
	        List<OrdersVO> list = ordersSvc.getAllByStore(storeEmpVO.getStoresVO().getStoreId());
	        System.out.println(list.size());
	        return list;
	    } else {
	        // 如果类型不匹配，你可以选择采取适当的措施，例如返回空列表或者抛出异常
	       System.out.println("xxx");
	    	return Collections.emptyList();
	    }
	}
	
//    @GetMapping("/listAllOrders")
//	public String listAllOrders(Model model) {
//		return "sFunction/orders/listAllOrders";
//	}
	
	//===================================================
//	 @GetMapping("/orderOrders")
//		public String orderOrders( ModelMap model) {
//			ServerSocket sc = null;
//			int count = 0;
//			
//			System.out.println("TcpServerM listening port 8888.......");
//			try {
//				sc = new ServerSocket(8888); // 在8888埠建立ServerSocket, 並等待客戶端的連結
//			} catch (IOException ioe) {
//				System.err.println("Could not listen on port: 1024.");
//				return "-1";
//			}	
//			
//			try {
//				while (true) {//雖然是無窮迴圈創thread，但有accept方法會發生wait，等有client連進來時，才繼續執行
//					 // 連線start()
//					new ConnSocketThread(sc.accept(),++count).start();
//
//					
//					
//				}
//			} catch (IOException ioe) {
//				System.err.println("Exception" + ioe);
//			}
//			sc.close();
//			
//	
//		 return "1";
//	 }
	
	
	
	
	
	
}
