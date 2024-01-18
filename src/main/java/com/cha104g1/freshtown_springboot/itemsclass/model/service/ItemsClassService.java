package com.cha104g1.freshtown_springboot.itemsclass.model.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;

@Service("itemsClassService")
public class ItemsClassService {
	
	@Autowired
	ItemsClassRepository repository;
           
         public void addItemsClass(ItemsClassVO itemsClassVO) {
         	repository.save(itemsClassVO);
         }
         
         public void updateItemsClass(ItemsClassVO itemsClassVO) {
        	repository.save(itemsClassVO);
          }
         
         public ItemsClassVO getOneItemsClass(Integer itemClassId) {
     		Optional<ItemsClassVO> optional = repository.findById(itemClassId);
    		return optional.orElse(null);
         }
         
     	public List<ItemsClassVO> getAll() {
     		return repository.findAll();
    	}
     	
    	public Set<MaterialVO> getMaterialVOByItemClassId(Integer itemClassId) {
    		return getOneItemsClass(itemClassId).getMaterialVO();
    	}
}
