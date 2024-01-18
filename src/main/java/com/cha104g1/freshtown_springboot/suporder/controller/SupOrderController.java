package com.cha104g1.freshtown_springboot.suporder.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderService;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;

@Controller
@RequestMapping("/sFunction/supOrder/")
public class SupOrderController {
	
	
	@Autowired
	SupOrderService supOrderSvc;

	//複合查詢
//    @PostMapping("listSupplier_ByCompositeQuery")
//    public String listSupplierByCompositeQuery(
//            @RequestParam(name = "supplierName", required = false) String supplierName,
//            @RequestParam(name = "supplierContact", required = false) String supplierContact,
//            @RequestParam(name = "supplierState", required = false) Integer supplierState,
//            Model model) {
//    	System.out.println("test");
//        List<SupVO> supVO = supSvc.listSupByCompositeQuery(supplierName, supplierContact, supplierState);
//        model.addAttribute("listSupplier_ByCompositeQuery", supVO);
//		model.addAttribute("listSupplier_ByCompositeQuery", "true"); 
//        return "pFunction/supplier/supOne";
//    }
	
	@PostMapping("listSupOrder_ByCompositeQuery")
	public String listAllSupplier(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<SupOrderVO> list = supOrderSvc.getAll(map);
		model.addAttribute("supListData", list); 
		return "sFunction/supOrder/supList";
	}
	
	//全都要
	@ModelAttribute("supListData")
	protected List<SupOrderVO> referenceListData() {
		List<SupOrderVO> list = supOrderSvc.getAll();
		return list;
	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
		List<SupOrderVO> list = supOrderSvc.getAll();
		model.addAttribute("supListData", list);
		
		String message = strBuilder.toString();
	    return new ModelAndView("sFunction/supOrder/supplierMain", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
}