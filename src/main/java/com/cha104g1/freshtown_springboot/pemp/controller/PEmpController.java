package com.cha104g1.freshtown_springboot.pemp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;

@Controller
@RequestMapping("/pEmp")
public class PEmpController {

	@Autowired
	PlatformEmpService platformEmpSvc;
	
	
	

	// 全資料一覽
	@ModelAttribute("pEmpListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<PlatformEmpVO> referenceListData(Model model) {
		System.out.println("準備硬了");
		List<PlatformEmpVO> list = platformEmpSvc.getAll();
		return list;
	}

}
