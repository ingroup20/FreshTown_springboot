package com.cha104g1.freshtown_springboot.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;

@Controller
@Validated
@RequestMapping("/Orders")
public class OrdersController {

	@Autowired
	OrdersService ordersSvc;
	
	//全資料一覽
	@ModelAttribute("OrdersListData")
	protected List<OrdersVO>  referenceListData(Model model){
		List<OrdersVO> list = ordersSvc.getAll();
		return list;
	}
}
