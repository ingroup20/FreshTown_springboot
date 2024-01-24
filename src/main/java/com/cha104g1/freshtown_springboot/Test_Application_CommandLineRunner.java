package com.cha104g1.freshtown_springboot;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialRepository;
import com.cha104g1.freshtown_springboot.material.model.service.MaterialService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpRepository;

import redis.clients.jedis.Jedis;



@SpringBootApplication
public class Test_Application_CommandLineRunner implements CommandLineRunner {
    //main方法，單獨測區塊功能
	
	
//	@Autowired
//	PlatformEmpRepository repository;
	@Autowired
	MaterialRepository repository;
	@Autowired
	MaterialService materialSvc;
	
	@Autowired
	LikeStoreService likeStoreSvc;

	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner.class);
    }

    @Override
    public void run(String...args) throws Exception {


    	List<LikeStoreVO> likeStoreList = likeStoreSvc.getAllByCustomer(1,"L");


//    	
    	
    	
//    	List<RefundsVO> list = repository.findAll();

    	for (LikeStoreVO refunds : likeStoreList) {
			System.out.print(refunds.getLikeUnlike() + ",");
			System.out.print(refunds.getClass() + ",");
			System.out.print(refunds.getCustomerVO() + ",");
			System.out.print(refunds.getStoresVO() + ",");

    	}
    	
    	

//    	
//    	PlatformEmpVO platformEmpVO= repository.findByPEmpAccount("dog");
//	System.out.println(platformEmpVO.getpEmpName());

//    	
//    	List<LikeStoreVO> jList=null;

    		
//    	jList=getJedis();
//    		
//    		if(jList==null) {
//    			List<LikeStoreVO>  list = likeStoreSvc.getAll();//sql取出vo物件List		
//    			setJedis(list);	
//    			jList=list;
    			
    			
//    		}
    		
//    		
//    		System.out.println(jList);
//    		
//    	}
//    	
    	//=============================================================

	

//    	protected void setJedis(List<LikeStoreVO>  list) {
//    		Jedis jedis=null;
//    		List<LikeStoreVO> jList=null;
//    		String jsonStr = "";
//    		
//    		try {
//    			 jedis = new Jedis("localhost", 6379);
//    			jedis.select(1);
//    			System.out.println(jedis.ping());//測試連線
//    			
//    			if (jedis.exists("likeStoreVO"))//重置儲存位置
//    				jedis.del("likeStoreVO");
//    			
//    			// Object (with List) to JSON
////    			jsonStr = new JSONObject(list).toString();
//    			JSONArray jsonArray = new JSONArray(list);
//    			jedis.rpush("likeStoreVO",jsonStr);//放入jedis，json(List)格式
//    					
//    		}finally {
//    			if(jedis != null)
//    				jedis.close();
//    		}
//    	}
//    	
//    	
//    	protected List getJedis() {
//    		Jedis jedis=null;
//    		List<LikeStoreVO> likeStoreList = new ArrayList<LikeStoreVO>();
//    		try {
//    			 jedis = new Jedis("localhost", 6379);
//    			jedis.select(1);
//    			System.out.println(jedis.ping());//測試連線
//    			
//    			List<String> jsonList = jedis.lrange("likeStoreVO", 0, -1);//取出
//    			
//    			return jsonList;
//    			
//    			
//    		}finally {
//    			if(jedis != null)
//    				jedis.close();
//    		}
   	}


}  
    

