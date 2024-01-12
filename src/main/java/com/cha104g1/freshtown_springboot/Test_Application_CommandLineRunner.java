package com.cha104g1.freshtown_springboot;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.login.model.IdentityVO;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpRepository;
import com.cha104g1.freshtown_springboot.pemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsRepository;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresRepository;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;



@SpringBootApplication
public class Test_Application_CommandLineRunner implements CommandLineRunner {
    //main方法，單獨測區塊功能
	
	
	@Autowired
	PlatformEmpRepository repository;
	
//	@Autowired
//	StoresRepository repository ;
//	
	public static void main(String[] args) {
        SpringApplication.run(Test_Application_CommandLineRunner.class);
    }

    @Override
    public void run(String...args) throws Exception {

//    	//● 新增
//		DeptVO deptVO = new DeptVO(); // 部門POJO
//		deptVO.setDeptno(1);

//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000));
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptVO(deptVO);
//		repository.save(empVO1);

		//● 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptVO(deptVO);
//		repository.save(empVO2);
		
		//● 刪除   //●●● --> EmployeeRepository.java 內自訂的刪除方法
//		repository.deleteByEmpno(7014);
		
		//● 刪除   //XXX --> Repository內建的刪除方法目前無法使用，因為有@ManyToOne
		//System.out.println("--------------------------------");
		//repository.deleteById(7001);      
		//System.out.println("--------------------------------");

    	//● 查詢-findByPrimaryKey (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	Optional<EmpVO> optional = repository.findById(7001);
//		EmpVO empVO3 = optional.get();
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		// 注意以下三行的寫法 (優!)
//		System.out.print(empVO3.getDeptVO().getDeptno() + ",");
//		System.out.print(empVO3.getDeptVO().getDname() + ",");
//		System.out.print(empVO3.getDeptVO().getLoc());
//		System.out.println("\n---------------------");
      
    	
		//● 查詢-getAll (多方emp2.hbm.xml必須設為lazy="false")(優!)
//    	List<EmpVO> list = repository.findAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			// 注意以下三行的寫法 (優!)
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}
    
    	
//    	List<RefundsVO> list = repository.findAll();
//    	for (RefundsVO refunds : list) {
//			System.out.print(refunds.getOrdersVO().getOrderId() + ",");
//			System.out.print(refunds.getRefundState() + ",");
//			System.out.print(refunds.getRefundDollar() + ",");
//			System.out.print(refunds.getRefundDate() + ",");
//    	}
    	
    	String pEmpAccount = "dog";
    	
    	PlatformEmpVO platformEmpVO= repository.findByPEmpAccount(pEmpAccount);
    	System.out.println("000000000000000000000000000");
        // 这里根据实际情况，创建并返回IdentityVO对象
        IdentityVO identityVO = new IdentityVO();
        identityVO.setId(platformEmpVO.getpEmpId());
        identityVO.setName(platformEmpVO.getpEmpName());
        identityVO.setPersonal(platformEmpVO.getpEmpPw());
        identityVO.setPermissions(String.valueOf(platformEmpVO.getpEmpPerm()));

 		System.out.println("111111111111111111111111");
 		System.out.println(identityVO);
 		System.out.println("22222222222222222222222");


   }

	public IdentityVO findByPEmpAccount(String pEmpAccount) {
		
  		 // 调用Repository中的方法来查询数据库
  		PlatformEmpVO platformEmpVO= repository.findByPEmpAccount(pEmpAccount);

         // 这里根据实际情况，创建并返回IdentityVO对象
         IdentityVO identityVO = new IdentityVO();
         identityVO.setId(platformEmpVO.getpEmpId());
         identityVO.setName(platformEmpVO.getpEmpName());
         identityVO.setPersonal(platformEmpVO.getpEmpPw());
         identityVO.setPermissions(String.valueOf(platformEmpVO.getpEmpPerm()));

  		System.out.println("111111111111111111111111");
  		System.out.println(identityVO);
  		System.out.println("22222222222222222222222");
         return identityVO;
  	}

    
}
