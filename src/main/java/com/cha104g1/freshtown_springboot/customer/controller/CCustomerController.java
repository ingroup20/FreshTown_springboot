package com.cha104g1.freshtown_springboot.customer.controller;

import java.io.IOException;
import java.util.List;

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

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;



@Controller
@RequestMapping("/cFunction/customer")
public class CCustomerController {
	

	@Autowired
	CustomerService customerSvc;
	private String customerId;
			
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("customerId") String customerId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		CustomerVO customerVO = customerSvc.getOneCustomer(Integer.valueOf(customerId));
		System.out.println(customerId);
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("customerVO", customerVO);
		System.out.println("修改成功1");
		return "cFunction/customer/update_customer_input"; // 查詢完成後轉交update_emp_input.html
	}

	@PostMapping("update")
	public String update(@Valid CustomerVO customerVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料不全");
			return "cFunction/customer/update_customer_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		System.out.println(customerVO.getCustomerId());

		customerSvc.updateCustomerVO(customerVO);
		System.out.println("修改成功2");
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		customerVO = customerSvc.getOneCustomer(Integer.valueOf(customerVO.getCustomerId()));
		model.addAttribute("customerVO", customerVO);
   		model.addAttribute("searchPersonalInfo", "true"); // for cEnrance.html
		return "cFunction/cEntrancePass"; // 修改成功後轉交listOneEmp.html
	}

	
	

	// 全資料一覽
	@ModelAttribute("customerListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<CustomerVO> referenceListData(Model model) {

		List<CustomerVO> list = customerSvc.getAll();
		return list;
	}


}
