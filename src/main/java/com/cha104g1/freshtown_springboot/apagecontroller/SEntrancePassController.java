package com.cha104g1.freshtown_springboot.apagecontroller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.service.ItemsClassService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.picking.service.PickingService;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpService;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderService;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;
import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

import redis.clients.jedis.Jedis;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;


@Controller
@Validated
@RequestMapping("/sFunction")
public class SEntrancePassController {

	@Autowired
	StoresService storesSvc;
	@Autowired
	MealsService mealsSvc;
	@Autowired
	LikeStoreService likeStoreSvc;
	@Autowired
	CustomerService customerSvc;
	@Autowired
	OrdersService ordersSvc;
	@Autowired
	SupService supSvc;
	@Autowired
	SupOrderService supOrderSvc;
	@Autowired
	StoreEmpService storeEmpSvc;
	@Autowired
	CustomizedDetailService customizedDetailSvc;	
	@Autowired
	CustomizedItemsService customizedItemsSvc;
	
	@Autowired
	CustomizedService customizedSvc;
	
	@Autowired
	MaterialService materialSvc;

	@Autowired
	ItemsClassService itemsClassSvc;
	
	@Autowired
	PickingService pickingSvc;

	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {

	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("storeEmpLogin");
	StoreEmpVO storeEmpVO= (StoreEmpVO)idVO;
	if (storeEmpVO == null) {
		System.out.println("出現null啦");
	}
	System.out.println("身分暱稱="+storeEmpVO.getsEmpName());
	 model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
   }
   
	//登出
    @GetMapping("/logout")
    public String logout(HttpServletRequest req ,Model model) {
    	// 獲取 HttpSession，防止在會話不存在時創建新的會話。如果您確定會話一定存在，可以使用 getSession()。
        HttpSession session = req.getSession(false);
     // 檢查 HttpSession 是否存在，並且 platformEmpLogin 屬性是否存在
        if (session != null && session.getAttribute("storeEmpLogin") != null) {
            session.removeAttribute("storeEmpLogin");
        }
    	return "sFunction/sEntrancePass"; //view
    }
    

  //==insert訂單管理=================== 
	@PostMapping("manageOrders")
   	public String manageOrders(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
   		/***************************2.查詢*********************************************/
//		Integer storeId = (Integer)model.getAttribute("storeId");
//   		List<OrdersVO> ordersListData = ordersSvc.getAllByStore(storeId); 	
//   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
   	
   		/***************************3.顯示*****************/
   		model.addAttribute("manageOrders", "true"); // for cEnrance.html
   		
   		return "sFunction/sEntrancePass"; 	
   	}
    
	  //==insert訂單排程=================== 
		@PostMapping("orderOrders")
	   	public String orderOrders(HttpServletRequest req,ModelMap model) {
	   		/***************************1.接收請求↑ ************************/
	   		/***************************2.查詢*********************************************/
			Integer storeId = (Integer)model.getAttribute("storeId");
	   		List<OrdersVO> ordersListData = ordersSvc.getAllByStore(storeId); 	
	   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
	   	
	   		/***************************3.顯示*****************/
	   		model.addAttribute("orderOrders", "true"); // for cEnrance.html
	   		
	   		return "sFunction/sEntrancePass"; 	
	   	}
		
		//===
				    @GetMapping("/listAllOrders")
					public String listAllOrders(Model model) {
						return "sFunction/orders/listAllOrders";
					}
				    @PostMapping("getOne_For_Display")
					public String getOne_For_Display(
						/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						@NotEmpty(message="訂單編號: 請勿空白")
						@RequestParam("orderId") String orderId,
						ModelMap model) {
						
						/***************************2.開始查詢資料*********************************************/
						OrdersVO ordersVO = ordersSvc.getOneOrders(Integer.valueOf(orderId));
						
						List<OrdersVO> list = ordersSvc.getAll();
						model.addAttribute("ordersListData", list);     // for select_page.html 第97 109行用
			
						if (ordersVO == null) {
							model.addAttribute("errorMessage", "查無資料");
							return "sFunction/manageOrders";
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
						model.addAttribute("ordersVO", ordersVO);
						model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
			
						return "sFunction/manageOrders"; 
					}
				    
	    
	    
	    
   
		  //==insert店家資料=================== 
			@PostMapping("storeInfo")
		   	public String storeInfo(HttpServletRequest req,ModelMap model) {
		   		/***************************1.接收請求↑ ************************/
		   		/***************************2.查詢*********************************************/
//StoresVO原本就在model裡面
				//				StoresVO storesVO = (StoresVO)model.getAttribute("storesVO");
//		   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
		   	
		   		/***************************3.顯示*****************/
		   		model.addAttribute("storeInfo", "true"); // for cEnrance.html
		   		
		   		return "sFunction/sEntrancePass"; 	
		   	}
			
			//=========== StoreEmp  要使用的資料 ===================   
			@GetMapping("/storeEmp/select_page")
			public String select_page14(Model model) {
				return "/sFunction/storeEmp/select_page";
			}
			
			@GetMapping("/storeEmp/listAllStoreEmp")
			public String listAllStoreEmp(Model model) {
				return "/sFunction/storeEmp/listAllStoreEmp";
			}
			
			@ModelAttribute("storeEmpListData") // for select_page.html 第135行用
			protected List<StoreEmpVO> referenceListData_StoreEmp(Model model) {
				List<StoreEmpVO> list = storeEmpSvc.getAll();
				return list;
			}
			
	//=========== supplier  要使用的資料 ===================   
    @GetMapping("/supplier/supplierMain")
	public String supplierMain(Model model) {
		return "sFunction/supplier/supplierMain";
	}
    
    @GetMapping("/supplier/supList")
	public String supList(Model model) {
		return "sFunction/supplier/supList";
	}
    
	@ModelAttribute("supListData")
	protected List<SupVO> referenceListData_Stores(Model model) {
		List<SupVO> list = supSvc.getAll();
		return list;
	}
	
	//=========== suporder  要使用的資料 ===================   
	@GetMapping("/suporder/supOrderMain")
	public String supOrderMain(Model model) {
		return "sFunction/suporder/supOrderMain";
	}
	
	@GetMapping("/suporder/supOrderList")
	public String supOrderList(Model model) {
		return "sFunction/suporder/supOrderList";
	}
	
	@ModelAttribute("supOrderListData")
	protected List<SupOrderVO> referenceListData_SupOrder(Model model) {
		List<SupOrderVO> list = supOrderSvc.getAll();
		return list;
	}


	//=========== CustomizedDetail  要使用的資料 ===================   
    @GetMapping("/customizeddetail/select_page")
	public String select_page9(Model model) {
		return "/sFunction/customizeddetail/select_page";
	}
    
    @GetMapping("/customizeddetail/listAllCustomizedDetail")
	public String listAllCustomizedDetail(Model model) {
		return "/sFunction/customizeddetail/listAllCustomizedDetail";
	}
    
	@ModelAttribute("customizedDetailListData") // for select_page.html 第135行用
	protected List<CustomizedDetailVO> referenceListData_CustomizedDetail(Model model) {
		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		return list;
	}
	
	//=========== CustomizedItems  要使用的資料 ===================   
	@GetMapping("/customizeditems/select_page")
	public String select_page10(Model model) {
		return "/sFunction/customizeditems/select_page";
	}
	
	@GetMapping("/customizeditems/listAllCustomizedItems")
	public String listAllCustomizedItems(Model model) {
		return "/sFunction/customizeditems/listAllCustomizedItems";
	}
	
	@ModelAttribute("customizedItemsListData") // for select_page.html 第135行用
	protected List<CustomizedItemsVO> referenceListData_CustomizedItems(Model model) {
		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		return list;
	}
	
	//=========== Meals  要使用的資料 ===================   
	@GetMapping("/meals/select_page")
	public String select_page11(Model model) {
		return "/sFunction/meals/select_page";
	}
	
	@GetMapping("/meals/listAllMeals")
	public String listAllMeals(Model model) {
		return "/sFunction/meals/listAllMeals";
	}
	
	@ModelAttribute("mealsListData") // for select_page.html 第135行用
	protected List<MealsVO> referenceListData_Meals(Model model) {
		List<MealsVO> list = mealsSvc.getAll();
		return list;
	}
	
	//=========== Customized  要使用的資料 ===================   
	@GetMapping("/customized/select_page")
	public String select_page12(Model model) {
		return "/sFunction/customized/select_page";
	}
	
	@GetMapping("/customized/listAllCustomized")
	public String listAllCustomized(Model model) {
		return "/sFunction/customized/listAllCustomized";
	}
	
	@ModelAttribute("customizedListData") // for select_page.html 第135行用
	protected List<CustomizedVO> referenceListData_Customized(Model model) {
		List<CustomizedVO> list = customizedSvc.getAll();
		return list;
	}
	
	//=========== material  要使用的資料 =================== 
    @GetMapping("/material/select_page")
	public String select_page5(Model model) {
		return "sFunction/material/select_page";
	}
    
    @GetMapping("/material/listAllMaterial")
	public String listAllMaterial(Model model) {
		return "sFunction/material/listAllMaterial";
	}
    
	@ModelAttribute("materialListData") // for select_page.html 第135行用
	protected List<MaterialVO> referenceListData_Material(Model model) {
		//符合要什麼給什麼
		model.addAttribute("itemsClassVO", new ItemsClassVO());
		List<MaterialVO> list = materialSvc.getAll();
		return list;
	}
	
	//=========== picking  要使用的資料 =================== 
    @GetMapping("/picking/select_page")
	public String select_page6(Model model) {
		return "sFunction/picking/select_page";
	}
    
    @GetMapping("/picking/listAllPicking")
	public String listAllPicking(Model model) {
		return "sFunction/picking/listAllPicking";
	}
    
	@ModelAttribute("pickingListData") // for select_page.html 第135行用
	protected List<PickingVO> referenceListData_Picking(Model model) {
		model.addAttribute("materialVO", new MaterialVO());
		List<PickingVO> list = pickingSvc.getAll();
		return list;
	}
	
	//=========== itemsclass  要使用的資料 =================== 
    @GetMapping("/itemsclass/select_page")
	public String select_page7(Model model) {
		return "sFunction/itemsclass/select_page";
	}
    
    @GetMapping("/itemsclass/listAllItemsClass")
	public String listAllItemsClass(Model model) {
		return "sFunction/itemsclass/listAllItemsClass";
	}
    
	@ModelAttribute("itemsClassListData") 
	protected List<ItemsClassVO> referenceListData_ItemsClass(Model model) {
		List<ItemsClassVO> list = itemsClassSvc.getAll();
		return list;
	}
	

}