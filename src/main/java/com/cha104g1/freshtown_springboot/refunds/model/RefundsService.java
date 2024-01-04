package com.cha104g1.freshtown_springboot.refunds.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Service("refundsService")
public class RefundsService implements RefundsServiceIntf{

	@Autowired
	RefundsRepository repository;
	
//	  @Autowired
	    public RefundsService(RefundsRepository repository) {
	        this.repository = repository;//
	    }
	  
	    @Autowired
		public RefundsService() {
		}//多加的試試
		
	//不開sessionFactory用Spring boot 的EntityManager試拿session
//	private final EntityManager entityManager;
	

	
	@Override
	public RefundsVO addRefundsVO(RefundsVO refundsVO) {
		repository.save(refundsVO);
		return refundsVO;
	}

	@Override
	public RefundsVO updateRefundsVO(RefundsVO refundsVO) {
		repository.save(refundsVO);
		return refundsVO;
	}

	@Override
	public RefundsVO getRefundsVOById(Integer id) {
		Optional<RefundsVO> optional = repository.findById(id);
		return optional.orElse(null); 
	}

	@Override
	public List<RefundsVO> getAllRefundsVO(int currentPage) {

		return repository.findAll();//
	}

	@Override
	public List<RefundsVO> getAllRefundsVO() {

		return null;//回傳就500 :repository.findAll()???
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RefundsVO> getRefundsVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
