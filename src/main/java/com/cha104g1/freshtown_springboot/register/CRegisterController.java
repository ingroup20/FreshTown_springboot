package com.cha104g1.freshtown_springboot.register;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;

@Controller
@RequestMapping("/")
public class CRegisterController {
	
	@Autowired
	CustomerService customerSvc;
	
	
	
	@GetMapping("/registerC")
	public String addCustomer(ModelMap model) {
		CustomerVO customerVO = new CustomerVO();
		model.addAttribute("customerVO", customerVO);
		System.out.println("轉交請求");
		return "registerC";
	}

	@PostMapping("insertC")
	public String insert(@Valid CustomerVO customerVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "registerC";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customerSvc.addCustomer(customerVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomerVO> list = customerSvc.getAll();
		model.addAttribute("customerListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "cEntrance"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}

}
