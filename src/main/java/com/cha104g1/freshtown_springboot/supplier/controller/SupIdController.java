package com.cha104g1.freshtown_springboot.supplier.controller;

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

import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Controller
@RequestMapping("/sFunction/supplier")
public class SupIdController {

	
	@Autowired
	SupService supSvc;

	@GetMapping("addSup")
	public String addSup(ModelMap model) {
		SupVO supVO = new SupVO();
		model.addAttribute("supVO", supVO);
		return "sFunction/supplier/supplierAdd";
	}


	 @PostMapping("/insert")
	    public String insert(@Valid SupVO supVO, BindingResult result, ModelMap model) {
	        if (result.hasErrors()) {
	            return "sFunction/supplier/supplierAdd";
	        }
	        supSvc.addSup(supVO);
	        List<SupVO> list = supSvc.getAll();
	        model.addAttribute("supListData", list);
	        model.addAttribute("success", "- (新增成功)");
	        return "sFunction/supplier/supList";
	    }
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("supId") String supId, ModelMap model) {
		SupVO supVO = supSvc.getOneSup(Integer.valueOf(supId));
		System.out.println("test");
		model.addAttribute("supVO", supVO);
		return "sFunction/supplier/updateSup";
	}
	
	@PostMapping("update")
	public String update(@Valid SupVO supVO, BindingResult result, ModelMap model, @RequestParam("supId") String supId) throws IOException {
		if (result.hasErrors()) {
			return "pFunction/supplier/updateSup";
		}
		supVO.setSupId(Integer.valueOf(supId));
		supSvc.updateSupVO(supVO);
		model.addAttribute("success", "- (修改成功)");
		supVO = supSvc.getOneSup(Integer.valueOf(supVO.getSupId()));
		model.addAttribute("supVO", supVO);
		return "sFunction/supplier/supOne";
	}


	public BindingResult removeFieldError(SupVO supVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(supVO, "supVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

}