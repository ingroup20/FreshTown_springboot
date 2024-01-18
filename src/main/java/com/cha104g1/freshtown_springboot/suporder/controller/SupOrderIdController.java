package com.cha104g1.freshtown_springboot.suporder.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.suporder.model.SupOrderService;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;

@Controller
@RequestMapping("/sFunction/supOrder")
public class SupOrderIdController {

	
	@Autowired
	SupOrderService suporderSvc;

	@GetMapping("addSup")
	public String addSup(ModelMap model) {
		SupOrderVO suporderVO = new SupOrderVO();
		model.addAttribute("suporderVO", suporderVO);
		return "sFunction/supplier/supplierAdd";
	}


	 @PostMapping("/insert")
	    public String insert(@Valid SupOrderVO suporderVO, BindingResult result, ModelMap model) {
	        if (result.hasErrors()) {
	            return "sFunction/supplier/supplierAdd";
	        }
	        suporderSvc.addSupOrder(suporderVO);
	        List<SupOrderVO> list = suporderSvc.getAll();
	        model.addAttribute("supListData", list);
	        model.addAttribute("success", "- (新增成功)");
	        return "sFunction/supplier/supList";
	    }
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("id") String Id, ModelMap model) {
		SupOrderVO supVO = suporderSvc.getOneSupOrder(Integer.valueOf(Id));
		System.out.println("test");
		model.addAttribute("supVO", supVO);
		return "sFunction/supplier/updateSup";
	}
	
	@PostMapping("update")
	public String update(@Valid SupOrderVO suporderVO, BindingResult result, ModelMap model, @RequestParam("id") String id) throws IOException {
		if (result.hasErrors()) {
			return "pFunction/supplier/updateSup";
		}
		suporderVO.setSupId(Integer.valueOf(id));
		suporderSvc.updateSupOrderVO(suporderVO);
		model.addAttribute("success", "- (修改成功)");
		suporderVO = suporderSvc.getOneSupOrder(Integer.valueOf(suporderVO.getId()));
		model.addAttribute("suporderVO", suporderVO);
		return "sFunction/supplier/supOne";
	}


	public BindingResult removeFieldError(SupOrderVO suporderVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(suporderVO, "suporderVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

}