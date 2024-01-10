package com.cha104g1.freshtown_springboot.likestore.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;

@Controller
@Validated
@RequestMapping("/custFunction/likestore")
public class LikeStoreController {

	@Autowired
	LikeStoreService refundsSvc;
	
	//依會員id查全部
//	@ModelAttribute("customerListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
//	protected List<LikeStoreVO> customerListData(@RequestParam("customerId") int customerId, Model model){
//		List<LikeStoreVO> list = refundsSvc.getAll(customerId);
//		return list;
//	}
//	
	//平台查全部
	@ModelAttribute("allList")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<LikeStoreVO> allList(Model model) {
		List<LikeStoreVO> list = refundsSvc.getAll();
		return list;
	}
	
	//下層連結
	@GetMapping("/list")
	public String refundPage(Model model) {
		return "custFunction/likestore/list";
	}
	
}
