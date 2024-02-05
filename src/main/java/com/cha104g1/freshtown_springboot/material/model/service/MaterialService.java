package com.cha104g1.freshtown_springboot.material.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.material.model.model.HibernateUtil_CompositeQuery_Material;


@Service("materialService")
public class MaterialService implements MaterialServiceIntf{

	@Autowired
	MaterialRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;
	
    @Autowired
    private Validator validator;
    
    @Override
    public MaterialVO addMaterial(MaterialVO materialVO) {
    	
    Set<ConstraintViolation<MaterialVO>> violations = validator.validate(materialVO);
    if (!violations.isEmpty()) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<MaterialVO> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage());
        }
        throw new ConstraintViolationException(sb.toString(), violations);
    }
    MaterialVO newMaterial = repository.save(materialVO);
    return newMaterial;
}
    @Override
    public MaterialVO findMaterialByItemName(String itemName) {
        return repository.findByItemName(itemName);
    }
    
    @Override
	public MaterialVO updateMaterial(MaterialVO materialVO) {
		repository.save(materialVO);
		return materialVO;
	}
    
    @Override
	public MaterialVO getOneMaterial(Integer itemNumber) {
		Optional<MaterialVO> optional = repository.findById(itemNumber);
		return optional.orElse(null);//原本是null
	}
	

	public List<MaterialVO> getAllMaterials() {
        return repository.findAll();
    }

	public List<MaterialVO> getAll() {
		return repository.findAll();
	}
    @Override
	public List<MaterialVO> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Material.getAllC(map, sessionFactory.openSession());
	}
    @Override
	public Set<PickingVO> getpickingVOByPickingNo(Integer pickingNo) {
		return getOneMaterial(pickingNo).getPickingVO();
	}
    @Override
	public List<MaterialVO> getAllByStoreId(Integer storeId) {
		List<MaterialVO> list = repository.findAllByStoreId(storeId);
		return list;
	}


}
