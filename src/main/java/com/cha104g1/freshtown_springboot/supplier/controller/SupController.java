package com.cha104g1.freshtown_springboot.supplier.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;
import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Controller
@RequestMapping("/sFunction/supplier/")
public class SupController {
	
	
	@Autowired
	SupService supSvc;

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
	@ModelAttribute
	   public void whoareyou(HttpServletRequest req ,Model model) {

		HttpSession session = req.getSession(false);
		Object store =session.getAttribute("storeEmpLogin");
		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
		 model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
		 System.out.println("whoareyou method triggered. storeId: " + storeEmpVO.getStoresVO().getStoreId());
	   }
	
//	@PostMapping("listSupplier_ByCompositeQuery")
//	public String listAllSupplier(HttpServletRequest req, Model model) {
//		Map<String, String[]> map = req.getParameterMap();
//		HttpSession session = req.getSession(false);
//		Object store =session.getAttribute("storeEmpLogin");
//		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
//		model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
//		List<SupVO> list = supSvc.getAll(map);
//		model.addAttribute("supListData", list);
//		return "sFunction/supplier/supList";
//	}
	
	@PostMapping("listSupplier_ByCompositeQuery")
	public String listAllSupplier(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<SupVO> list = supSvc.getAll(map);
		model.addAttribute("supListData", list); 
		return "sFunction/supplier/supList";
	}
	
	//全都要
	@ModelAttribute("supListData")
	protected List<SupVO> referenceListData(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Object store =session.getAttribute("storeEmpLogin");
		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
		model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
		
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
	    return new ModelAndView("sFunction/supplier/supplierMain", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	
}