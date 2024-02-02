package com.cha104g1.freshtown_springboot.suporder.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderService;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/sFunction/suporder")
public class SupOrderIdController {

	
	@Autowired
	SupOrderService suporderSvc;
	@Autowired
	MaterialService materialSvc;

	@ModelAttribute
	   public void whoareyou(HttpServletRequest req ,Model model) {

		HttpSession session = req.getSession(false);
		Object store =session.getAttribute("storeEmpLogin");
		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
		if (storeEmpVO != null) {
			System.out.println("身分暱稱="+storeEmpVO.getsEmpName());
		}
		 model.addAttribute("storeEmpId", storeEmpVO.getStoresVO().getStoreId());
	   }
	
	@GetMapping("addSupOrder")
	public String addSupOrder(HttpServletRequest req, ModelMap model) {
		SupOrderVO suporderVO = new SupOrderVO();
		HttpSession session = req.getSession(false);
 		Object store =session.getAttribute("storeEmpLogin");
 		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
 		model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
 		System.out.println("店家="+storeEmpVO.getStoresVO().getStoreId());
		model.addAttribute("supOrderVO", suporderVO);
		return "sFunction/suporder/supOrderAdd";
	}


	 @PostMapping("/insert")
	    public String insert(@Valid SupOrderVO suporderVO, BindingResult result, ModelMap model, HttpServletRequest req) {
	        if (result.hasErrors()) {
	            return "sFunction/suporder/supOrderAdd";
	        }
	        HttpSession session = req.getSession(false);
	 		Object store =session.getAttribute("storeEmpLogin");
	 		StoreEmpVO storeEmpVO= (StoreEmpVO)store;
	 		model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
	 		StoresVO storesVO = new StoresVO();
	 	    storesVO.setStoreId(storeEmpVO.getStoresVO().getStoreId());
	 	    suporderVO.setStoresVO(storesVO);
	 		suporderSvc.addSupOrder(suporderVO);
	 		List<SupOrderVO> list = suporderSvc.getAll();
	        model.addAttribute("supOrderListData", list);
	        MaterialVO material = materialSvc.getOneMaterial(suporderVO.getMaterialVO().getItemNumber());
	        material.setQuantityNot(material.getQuantityNot() + suporderVO.getAmount());
	        materialSvc.updateMaterial(material);
	        model.addAttribute("success", "- (新增成功)");
	        return "sFunction/suporder/supOrderList";
	    }
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("id") String Id, ModelMap model) {
		SupOrderVO supOrderVO = suporderSvc.getOneSupOrder(Integer.valueOf(Id));
		model.addAttribute("supOrderVO", supOrderVO);
		return "sFunction/suporder/updateSupOrder";
	}
	
	@PostMapping("update")
	public String update(@Valid SupOrderVO suporderVO, BindingResult result, ModelMap model, @RequestParam("id") String id) throws IOException {
		if (result.hasErrors()) {
			return "pFunction/suporder/updateSupOrder";
		}
		suporderVO.setId(Integer.valueOf(id));

		switch (suporderVO.getoStatus()) {
		case 7:
//			MaterialVO stock = suporderSvc.getStock(suporderVO.getMaterialVO().getItemNumber());
//			MaterialVO quantity = suporderSvc.getQuantity(suporderVO.getMaterialVO().getItemNumber());
//			MaterialVO materials = suporderSvc.getMaterial(suporderVO.getMaterialVO().getItemNumber());
//			System.out.println(String.valueOf(suporderVO.getMaterialVO().getStockQuantity()));
//			System.out.println(String.valueOf(suporderVO.getMaterialVO().getItemNumber()));
//			System.out.println(String.valueOf(material));
//			System.out.print(material);
////			suporderSvc.updateSupOrderVO(suporderVO);
//			suporderVO.getMaterialVO().getStockQuantity();
//			stock.setStockQuantity(suporderVO.getMaterialVO().getStockQuantity() + suporderVO.getAmount());
//			quantity.setQuantityNot(suporderVO.getMaterialVO().getQuantityNot() - suporderVO.getAmount());
			
			MaterialVO material = materialSvc.getOneMaterial(suporderVO.getMaterialVO().getItemNumber());
//System.out.println("-------------------------");	
//
//System.out.println("suporderVO.getMaterialVO()==null:"+material==null);	
//System.out.println("suporderVO.getMaterialVO().getStockQuantity()="+material.getStockQuantity());	
//System.out.println("suporderVO.getMaterialVO().getItemName()="+material.getItemName());	
//
//
//System.out.println("-------------------------");			
			material.setStockQuantity(material.getStockQuantity() + suporderVO.getAmount());
			material.setQuantityNot(material.getQuantityNot() - suporderVO.getAmount());
//			material.setStockQuantity(suporderVO.getMaterialVO().getStockQuantity() + suporderVO.getAmount());
//			material.setQuantityNot(suporderVO.getMaterialVO().getQuantityNot() - suporderVO.getAmount());
			materialSvc.updateMaterial(material);
			suporderSvc.updateSupOrderVO(suporderVO);
			break;
		case 6:
			MaterialVO materials = suporderSvc.getMaterial(suporderVO.getMaterialVO().getItemNumber());
			materials.setQuantityNot(materials.getQuantityNot() - suporderVO.getAmount());
			materialSvc.updateMaterial(materials);
			suporderSvc.updateSupOrderVO(suporderVO);
			break;
		default:
			suporderSvc.updateSupOrderVO(suporderVO);
		}
		model.addAttribute("success", "- (修改成功)");
		suporderVO = suporderSvc.getOneSupOrder(Integer.valueOf(suporderVO.getId()));
		model.addAttribute("supOrderVO", suporderVO);
		return "sFunction/suporder/supOrderOne";
	}


	public BindingResult removeFieldError(SupOrderVO suporderVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(suporderVO, "supOrderVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}


}