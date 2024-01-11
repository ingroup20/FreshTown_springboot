package com.cha104g1.freshtown_springboot.supplier.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

@Controller
@RequestMapping("/supplier")
public class SupIdController {

	
	@Autowired
	SupService supSvc;

	@GetMapping("addEmp")
	public String addEmp(ModelMap model) {
		SupVO supVO = new SupVO();
		model.addAttribute("supVO", supVO);
		return "supplier/supplierAdd";
	}


	@PostMapping("insert")
	public String insert(@Valid SupVO supVO, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "supplier/supplierAdd";
		}
		SupService supSvc = new SupService();
		supSvc.addSup(supVO);
		List<SupVO> list = supSvc.getAll();
		model.addAttribute("supListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "supplier/supList";
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("empno") String empno, ModelMap model) {
		EmpVO empVO = empSvc.getOneEmp(Integer.valueOf(empno));

		model.addAttribute("empVO", empVO);
		return "back-end/emp/update_emp_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid EmpVO empVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(empVO, result, "upFiles");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			// EmpService empSvc = new EmpService();
			byte[] upFiles = empSvc.getOneEmp(empVO.getEmpno()).getUpFiles();
			empVO.setUpFiles(upFiles);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] upFiles = multipartFile.getBytes();
				empVO.setUpFiles(upFiles);
			}
		}
		if (result.hasErrors()) {
			return "back-end/emp/update_emp_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		empSvc.updateEmp(empVO);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		empVO = empSvc.getOneEmp(Integer.valueOf(empVO.getEmpno()));
		model.addAttribute("empVO", empVO);
		return "back-end/emp/listOneEmp"; // 修改成功後轉交listOneEmp.html
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(EmpVO empVO, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(empVO, "empVO");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

}
