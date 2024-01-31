package com.cha104g1.freshtown_springboot.platformemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;

public interface PlatformEmpServiceIntf {

	PlatformEmpVO addPlatformEmp(PlatformEmpVO platformEmpVO);

	PlatformEmpVO updatePlatformEmpVO(PlatformEmpVO platformEmpVO);

	PlatformEmpVO getOnePlatformEmp(Integer pEmpId);

	List<PlatformEmpVO> getAllPlatformEmpVO(int currentPage);

	List<PlatformEmpVO> getAll();

	int getPageTotal();

	List<PlatformEmpVO> getPlatformEmpVOsByCompositeQuery(Map<String, String[]> map);

}
