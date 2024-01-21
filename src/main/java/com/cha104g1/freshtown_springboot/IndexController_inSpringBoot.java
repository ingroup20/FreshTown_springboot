package com.cha104g1.freshtown_springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.itemsclass.model.service.ItemsClassService;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;

import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpService;

import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.picking.service.PickingService;

import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;
import com.cha104g1.freshtown_springboot.service.model.service.SvcService;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.cha104g1.freshtown_springboot.supplier.model.SupService;
import com.cha104g1.freshtown_springboot.supplier.model.SupVO;

import java.util.*;


//@Controller
public class IndexController_inSpringBoot {

	@Autowired
	RefundsService refundsSvc;
	
	@Autowired
	OrdersService ordersSvc;
	
	@Autowired
	StoresService storesSvc;
	
	@Autowired
	MealTypeService mealTypeSvc;
	

	
	@Autowired
	PlatformEmpService platformEmpSvc;
	

	@Autowired
	SupService supSvc;

	@Autowired
	MaterialService materialSvc;
	
	@Autowired
	PickingService pickingSvc;
	
	@Autowired
	ItemsClassService itemsClassSvc;
	
	@Autowired
	SvcService svcSvc;
	
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	
	@Autowired
	CustomizedItemsService customizedItemsSvc;

	@Autowired
	MealsService mealsSvc;
	
	@Autowired
	CustomizedService customizedSvc;
	
    @Value("${welcome.message}")
    private String message;
	
    private List<String> myList = Arrays.asList("refunds 官網 ", "mealtype 官網", "likestore 官網", "orders 官網", "stores 官網");
//    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
    // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
    
  
    //=========== refunds ===================   
    @GetMapping("/pFunction/refunds/select_page")
	public String select_page1(Model model) {
		return "pFunction/refunds/select_page";
	}
    
    @GetMapping("/pFunction/refunds/listAllRefunds")
	public String listAllRefunds(Model model) {
		return "pFunction/refunds/listAllRefunds";
	}
    
    @ModelAttribute("refundsListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<RefundsVO> referenceListData_Refunds(Model model) {
		
    	List<RefundsVO> list = refundsSvc.getAll();
		return list;
	}
    

	
    //=========== mealtype  要使用的資料 ===================   
    @GetMapping("/pFunction/mealtype/select_page")
	public String select_page2(Model model) {
		return "pFunction/mealtype/select_page";
	}
    
    @GetMapping("/pFunction/mealtype/listAllMealType")
	public String listAllMealtype(Model model) {
		return "pFunction/mealtype/listAllMealType";
	}
    
    @ModelAttribute("mealTypeListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<MealTypeVO> referenceListData_MealType(Model model) {
		
    	List<MealTypeVO> list = mealTypeSvc.getAll();
		return list;
	}
    
    //=========== orders  要使用的資料 ===================   
    @GetMapping("/pFunction/orders/select_page")
	public String select_page3(Model model) {
		return "pFunction/orders/select_page";
	}
    
    @GetMapping("/pFunction/orders/listAllOrders")
	public String listAllOrders(Model model) {
		return "pFunction/orders/listAllOrders";
	}
    
	@ModelAttribute("ordersListData") // for select_page.html 第135行用
	protected List<OrdersVO> referenceListData_Orders(Model model) {
		List<OrdersVO> list = ordersSvc.getAll();
		return list;
	}
	
    @GetMapping("/sFunction/orders/select_page")
	public String select_pageS(Model model) {
		return "sFunction/orders/select_page";
	}


    //=========== stores  要使用的資料 ===================   
    @GetMapping("/pFunction/stores/select_page")
	public String select_page4(Model model) {
		return "pFunction/stores/select_page";
	}
    
    @GetMapping("/pFunction/stores/listAllStores")
	public String listAllStores(Model model) {
		return "pFunction/stores/listAllStores";
	}
    
	@ModelAttribute("storesListData") // for select_page.html 第135行用
	protected List<StoresVO> referenceListData_Supplier(Model model) {
		List<StoresVO> list = storesSvc.getAll();
		return list;
	}
	

	//=================================
//	@GetMapping("/cEntrance")
//	public String goCEntrance(Model model) {
//		return "cEntrance";
//	}
	
	
	


    //=========== supplier  要使用的資料 ===================   
    @GetMapping("/pFunction/supplier/supplierMain")
	public String supplierMain(Model model) {
		return "pFunction/supplier/supplierMain";
	}
    
    @GetMapping("/pFunction/supplier/supList")
	public String supList(Model model) {
		return "pFunction/supplier/supList";
	}
    
	@ModelAttribute("supListData") // for select_page.html 第135行用
	protected List<SupVO> referenceListData_Stores(Model model) {
		List<SupVO> list = supSvc.getAll();
		return list;
	}
	
//    //=========== stores  要使用的資料 ===================   
//    @GetMapping("/cFunction/stores/addStoresC")
//	public String select_page5(Model model) {
//		return "cFunction/stores/addStores";
//	}



	//=========== material  要使用的資料 =================== 
    @GetMapping("/sFunction/material/select_page")
	public String select_page5(Model model) {
		return "sFunction/material/select_page";
	}
    
    @GetMapping("/sFunction/material/listAllMaterial")
	public String listAllMaterial(Model model) {
		return "sFunction/material/listAllMaterial";
	}
    
	@ModelAttribute("materialListData") // for select_page.html 第135行用
	protected List<MaterialVO> referenceListData_Material(Model model) {
		List<MaterialVO> list = materialSvc.getAll();
		return list;
	}
	
	//=========== picking  要使用的資料 =================== 
    @GetMapping("/sFunction/picking/select_page")
	public String select_page6(Model model) {
		return "sFunction/picking/select_page";
	}
    
    @GetMapping("/sFunction/picking/listAllPicking")
	public String listAllPicking(Model model) {
		return "sFunction/picking/listAllPicking";
	}
    
	@ModelAttribute("pickingListData") // for select_page.html 第135行用
	protected List<PickingVO> referenceListData_Picking(Model model) {
		List<PickingVO> list = pickingSvc.getAll();
		return list;
	}
	
	//=========== itemsclass  要使用的資料 =================== 
    @GetMapping("/sFunction/itemsclass/select_page")
	public String select_page7(Model model) {
		return "sFunction/itemsclass/select_page";
	}
    
    @GetMapping("/sFunction/itemsclass/listAllItemsClass")
	public String listAllItemsClass(Model model) {
		return "sFunction/itemsclass/listAllItemsClass";
	}
    
	@ModelAttribute("itemsClassListData") // for select_page.html 第135行用
	protected List<ItemsClassVO> referenceListData_ItemsClass(Model model) {
		List<ItemsClassVO> list = itemsClassSvc.getAll();
		return list;
	}
	
	//=========== service  要使用的資料 =================== 
    @GetMapping("/sFunction/service/select_page")
	public String select_page8(Model model) {
		return "sFunction/service/select_page";
	}
    
    @GetMapping("/sFunction/service/listAllService")
	public String listAllService(Model model) {
		return "sFunction/service/listAllService";
	}
    
	@ModelAttribute("serviceListData") // for select_page.html 第135行用
	protected List<ServiceVO> referenceListData_Service(Model model) {
		List<ServiceVO> list = svcSvc.getAll();
		return list;
	}
	
	//=========== CustomizedDetail  要使用的資料 ===================   
    @GetMapping("/pFunction/customizeddetail/select_page")
	public String select_page9(Model model) {
		return "/pFunction/customizeddetail/select_page";
	}
    
    @GetMapping("/pFunction/customizeddetail/listAllCustomizedDetail")
	public String listAllCustomizedDetail(Model model) {
		return "/pFunction/customizeddetail/listAllCustomizedDetail";
	}
    
	@ModelAttribute("customizedDetailListData") // for select_page.html 第135行用
	protected List<CustomizedDetailVO> referenceListData_CustomizedDetail(Model model) {
		List<CustomizedDetailVO> list = customizedDetailSvc.getAll();
		return list;
	}
	
	//=========== CustomizedItems  要使用的資料 ===================   
	@GetMapping("/pFunction/customizeditems/select_page")
	public String select_page10(Model model) {
		return "/pFunction/customizeditems/select_page";
	}
	
	@GetMapping("/pFunction/customizeditems/listAllCustomizedItems")
	public String listAllCustomizedItems(Model model) {
		return "/pFunction/customizeditems/listAllCustomizedItems";
	}
	
	@ModelAttribute("customizedItemsListData") // for select_page.html 第135行用
	protected List<CustomizedItemsVO> referenceListData_CustomizedItems(Model model) {
		List<CustomizedItemsVO> list = customizedItemsSvc.getAll();
		return list;
	}
	
	//=========== Meals  要使用的資料 ===================   
	@GetMapping("/pFunction/meals/select_page")
	public String select_page11(Model model) {
		return "/pFunction/meals/select_page";
	}
	
	@GetMapping("/pFunction/meals/listAllMeals")
	public String listAllMeals(Model model) {
		return "/pFunction/meals/listAllMeals";
	}
	
	@ModelAttribute("mealsListData") // for select_page.html 第135行用
	protected List<MealsVO> referenceListData_Meals(Model model) {
		List<MealsVO> list = mealsSvc.getAll();
		return list;
	}
	
	//=========== Customized  要使用的資料 ===================   
	@GetMapping("/pFunction/customized/select_page")
	public String select_page12(Model model) {
		return "/pFunction/customized/select_page";
	}
	
	@GetMapping("/pFunction/customized/listAllCustomized")
	public String listAllCustomized(Model model) {
		return "/pFunction/customized/listAllCustomized";
	}
	
	@ModelAttribute("customizedListData") // for select_page.html 第135行用
	protected List<CustomizedVO> referenceListData_Customized(Model model) {
		List<CustomizedVO> list = customizedSvc.getAll();
		return list;
	}

}