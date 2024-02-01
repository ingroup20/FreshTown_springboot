package com.cha104g1.freshtown_springboot.register;

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

import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@Controller
@RequestMapping("/")
public class SRegisterController {
	
	@Autowired
	StoresService storesSvc;
	
	
	
	@GetMapping("/registerS")
	public String addStores(ModelMap model) {
		StoresVO storesVO = new StoresVO();
		model.addAttribute("storesVO", storesVO);
		System.out.println("轉交請求");
		return "registerS";
	}

	@PostMapping("insertS")
	public String insert(@Valid StoresVO storesVO, BindingResult result, ModelMap model) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/

		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "registerS";
		}
		/*************************** 2.開始新增資料 *****************************************/
		storesSvc.addStores(storesVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<StoresVO> list = storesSvc.getAll();
		model.addAttribute("storesListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "sEntrancePass"; // 新增成功後重導至IndexController_inSpringBoot.java的第50行@GetMapping("/emp/listAllEmp")
	}


}
