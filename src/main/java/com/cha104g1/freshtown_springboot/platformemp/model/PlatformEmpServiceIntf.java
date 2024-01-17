package com.cha104g1.freshtown_springboot.platformemp.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;

public interface PlatformEmpServiceIntf {

	PlatformEmpVO addPlatformEmpVO(PlatformEmpVO platformEmpVO);

	PlatformEmpVO updatePlatformEmpVO(PlatformEmpVO platformEmpVO);

	PlatformEmpVO getPlatformEmpVOById(Integer id);

	List<PlatformEmpVO> getAllPlatformEmpVO(int currentPage);

	List<PlatformEmpVO> getAll();

	int getPageTotal();

	List<PlatformEmpVO> getPlatformEmpVOsByCompositeQuery(Map<String, String[]> map);

}
