package com.cha104g1.freshtown_springboot.refunds.controller;



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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;


/*在退款頁面(退款模塊)，能操作的功能:
 * 	1. 訂單申請退款時，被新增一筆資料(此功能做作在ordersController)
 * 	2. 進行平台員工登入比對，權限比對
 * 	3. 查看所有退款訂單，依條件查找訂單
 *  4. 有勾選欄位可以選訂單，並修改訂單狀態為已退款，同時寫入退款時間S
 * 
 * */


@Controller
@Validated
@RequestMapping("/Refunds")
public class RefundsController {

	@Autowired
	RefundsService refundsSvc;
	
	
	//查紀錄
	@PostMapping("getOneDisplay")
	public int getOneDisplay(@RequestParam("id")	String id,ModelMap model) {
		//1.接收參數
		RefundsVO refundsVO = refundsSvc.getRefundsVOById(Integer.valueOf(id));
		//2.查詢永續層
		List<RefundsVO> list = refundsSvc.getAll();
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
	
	
	//需作成多選update功能
	@PostMapping("updateRefundState")
	public String updateRefundsVO(@Valid RefundsVO refundsVO , BindingResult result, ModelMap model ) {
		
		// 獲取當前的日期和時間
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 將當前的日期和時間轉換為 Timestamp
        Timestamp currentTimestamp = Timestamp.valueOf(currentDateTime);
		
		//1.接收參數
        //update改指定狀態
        refundsVO.setRefundState("Y");
        //update時強制寫入當前日期時間
		refundsVO.setRefundDate(currentTimestamp);
		
		//多選，選到已退款的項目，需要停止報錯
		if(refundsVO.getRefundState().equals("Y")) {
			System.out.println("退款狀態變更包含已完成退款的訂單");
			return "-1";
		}	
		if (result.hasErrors()) {
			return "參數錯誤，操作停止";
		}
		
		
		//2.修改永續層
		refundsSvc.updateRefundsVO(refundsVO);
		//3.轉交(重刷頁面)
		return "/haveToLogin/pFunction/refundPage";
		
	};
	
	//全資料一覽
    @ModelAttribute("refundsListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<RefundsVO> referenceListData(Model model) {
		
    	List<RefundsVO> list = refundsSvc.getAll();
		return list;
	}
	
	
}
