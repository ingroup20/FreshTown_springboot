package com.cha104g1.freshtown_springboot.refunds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;


/*在退款頁面(退款模塊)，能操作的功能:
 * 	1. 訂單申請退款時，被新增一筆資料(此功能做作在ordersController)
 * 	2. 進行平台員工登入比對，權限比對
 * 	3. 查看所有退款訂單，依條件查找訂單
 *  4. 有勾選欄位可以選訂單，並修改訂單狀態為已退款
 * 	5. 同時寫入退款時間
 * 
 * 
 * 
 * 
 * */













@Controller
@Validated
@RequestMapping("/Refunds")
public class RefundsController {

	@Autowired
	RefundsService refundsSvc;
	
	@PostMapping("getOneDisplay")
	public int getOneDisplay(@RequestParam("id")	String id,ModelMap model) {
		//1.接收參數
		RefundsVO refundsVO = refundsSvc.getRefundsVOById(Integer.valueOf(id));
		//2.查詢永續層
		List<RefundsVO> list = refundsSvc.getAllRefundsVO();
		model.addAttribute("refundsListData",list);
		
		if(refundsVO == null) {
			model.addAttribute("errorMessage","查無資料");
			return -1;
		}
	
		//3.轉交資料
		model.addAttribute("refundsVO",refundsVO);
		model.addAttribute("getOneDisplay","true");
		
		return 1;
		
		
	};
	
	
	
	
}
