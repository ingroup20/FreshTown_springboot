package com.cha104g1.freshtown_springboot.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Controller
@RequestMapping("/supplier")
public class SupController {
	
	
	@Autowired
	SupService supSvc;
	
	//供應商名稱
	@PostMapping("getOneSupplierName")
	public String getOneSupplierName(@RequestParam("supplierName") String supplierName, ModelMap model) {
		SupVO supVO = supSvc.getOneSupplierName(supplierName);
		if(supVO == null) {
			model.addAttribute("errorMessage","查無資料");
			return "back-page";
		}
		return supplierName;
	}
	
	//聯絡人名稱
	@PostMapping("getOneSupplierContact")
	public String getOneSupplierContact(@RequestParam("supplierContact") String supplierContact,ModelMap model) {
		return supplierContact;
		
	}
	
	//全都要
	@ModelAttribute("supListData")
	protected List<SupVO> referenceListData() {
		SupService supSvc = new SupService();
		List<SupVO> list = supSvc.getAll();
		return list;
	}
	
}
