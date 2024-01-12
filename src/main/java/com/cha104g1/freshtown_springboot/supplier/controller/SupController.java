package com.cha104g1.freshtown_springboot.supplier.controller;

import java.util.List;
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

import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Controller
@RequestMapping("/supplier")
public class SupController {
	
	
	@Autowired
	SupService supSvc;

	//複合查詢
    @PostMapping("listSupplier_ByCompositeQuery")
    public String listSupplierByCompositeQuery(
            @RequestParam(name = "supplierName", required = false) String supplierName,
            @RequestParam(name = "supplierContact", required = false) String supplierContact,
            @RequestParam(name = "supplierState", required = false) Integer supplierState,
            Model model) {

        List<SupVO> result = supSvc.listSupByCompositeQuery(supplierName, supplierContact, supplierState);
        model.addAttribute("result", result);

        return "resultPage";
    }
	
	//全都要
	@ModelAttribute("supListData")
	protected List<SupVO> referenceListData() {
		SupService supSvc = new SupService();
		List<SupVO> list = supSvc.getAll();
		return list;
	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
		List<SupVO> list = supSvc.getAll();
		model.addAttribute("supListData", list);
		
		String message = strBuilder.toString();
	    return new ModelAndView("pFunction/supplier/supplierMain", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
}
