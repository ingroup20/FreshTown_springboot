package com.cha104g1.freshtown_springboot.pemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreRepository;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.login.model.IdentityVO;

@Service("platformEmpService")
public class PlatformEmpService implements PlatformEmpServiceIntf{
	
	@Autowired
	PlatformEmpRepository repository;
	
	public PlatformEmpService(PlatformEmpRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public PlatformEmpVO addPlatformEmpVO(PlatformEmpVO platformEmpVO) {
		repository.save(platformEmpVO);
		return platformEmpVO;
	}

	@Override
	public PlatformEmpVO updatePlatformEmpVO(PlatformEmpVO platformEmpVO) {
	    repository.save(platformEmpVO);
	    return platformEmpVO;
	}
    
	@Override
	public PlatformEmpVO getPlatformEmpVOById(Integer id) {
		Optional<PlatformEmpVO> optional = repository.findById(id);
		return optional.orElse(null);
	}
    
	@Override
	public List<PlatformEmpVO> getAllPlatformEmpVO(int currentPage) {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


	@Override
	public List<PlatformEmpVO> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PlatformEmpVO> getPlatformEmpVOsByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
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

        return identityVO;
	}
	
	

}
