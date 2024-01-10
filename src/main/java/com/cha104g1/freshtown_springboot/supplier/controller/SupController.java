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
		SupService supSvc = new SupService();
		SupVO supVO = supSvc.getOneSupplierName(supplierName);
		
		if(supVO == null) {
			List<SupVO> list = supSvc.getAll();
			model.addAttribute("nameListData", list);
		}
		
		model.addAttribute("supVO", supVO);
		model.addAttribute("getOneSupplierName", "true");
		return "back";
	}
	
	//聯絡人名稱
	@PostMapping("getOneSupplierContact")
	public String getOneSupplierContact(@RequestParam("supplierContact") String supplierContact,ModelMap model) {
		SupService supSvc = new SupService();
		SupVO supVO = supSvc.getOneSupplierContact(supplierContact);
		
		if(supVO == null) {
			List<SupVO> list = supSvc.getAll();
			model.addAttribute("conListData", list);
		}
		
		model.addAttribute("supVO", supVO);
		model.addAttribute("getOneSupplierContact", "true");
		return "back";
	}
	
	//狀態
	@PostMapping("getOneSupplierStatus")
	public String getOneSupplierStatus(@RequestParam("supplierState") String supplierState,ModelMap model) {
		SupService supSvc = new SupService();
		SupVO supVO = supSvc.getOneSupplierStatus(Integer.valueOf(supplierState));
		
		if(supVO == null) {
			List<SupVO> list = supSvc.getAll();
			model.addAttribute("conListData", list);
		}
		
		model.addAttribute("supVO", supVO);
		model.addAttribute("getOneSupplierStatus", "true");
		return "back";
	}
	
	//全都要
	@ModelAttribute("supListData")
	protected List<SupVO> referenceListData() {
		SupService supSvc = new SupService();
		List<SupVO> list = supSvc.getAll();
		return list;
	}
	
}
