package com.cha104g1.freshtown_springboot.mealtype.controller;

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

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;

/*在退款頁面(退款模塊)，能操作的功能:
 * 	1. 訂單申請退款時，被新增一筆資料(此功能做作在ordersController)
 * 	2. 進行平台員工登入比對，權限比對
 * 	3. 查看所有退款訂單，依條件查找訂單
 *  4. 有勾選欄位可以選訂單，並修改訂單狀態為已退款，同時寫入退款時間S
 * 
 * */

@Controller
@Validated
@RequestMapping("/mealType")
public class MealTypeController {

	@Autowired
	MealTypeService mealTypeSvc;

	// 全資料一覽
	@ModelAttribute("mealTypeListData") // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<MealTypeVO> referenceListData(Model model) {

		List<MealTypeVO> list = mealTypeSvc.getAll();
		return list;
	}

}
