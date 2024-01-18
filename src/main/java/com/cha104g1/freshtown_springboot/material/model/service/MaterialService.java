package com.cha104g1.freshtown_springboot.material.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.material.model.model.HibernateUtil_CompositeQuery_Material;


@Service("materialService")
public class MaterialService {

	@Autowired
	MaterialRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;//如何改寫

	public void addMaterial(MaterialVO materialVO) {
		repository.save(materialVO);
	}

	public void updateMaterial(MaterialVO materialVO) {
		repository.save(materialVO);
	}

	public MaterialVO getOneMaterial(Integer itemNumber) {
		Optional<MaterialVO> optional = repository.findById(itemNumber);
		return optional.orElse(null);
	}

	public List<MaterialVO> getAll() {
		return repository.findAll();
	}

	public List<MaterialVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Material.getAllC(map, sessionFactory.openSession());
	}

}
