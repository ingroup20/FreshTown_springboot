package com.cha104g1.freshtown_springboot.material.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;

@Service("materialService")
public class MaterialService {

	@Autowired
	MaterialRepository repository;

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

}
