package com.cha104g1.freshtown_springboot.material.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;

@Controller
@Validated
@RequestMapping("/materialFunction")
public class MaterialController {
    
	@Autowired
	MaterialService materialSve;
	
	public String getOne_For_Display() {
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/	
	
		/***************************2.開始查詢資料*********************************************/
	
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
	
	}
	
	
}
