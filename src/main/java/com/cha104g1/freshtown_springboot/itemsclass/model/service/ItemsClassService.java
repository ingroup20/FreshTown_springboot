package com.cha104g1.freshtown_springboot.itemsclass.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;

@Service("itemsClassService")
public class ItemsClassService {
	
	@Autowired
	ItemsClassRepository repository;
           
         public void addItemsclass(ItemsClassVO itemsClassVO) {
         	repository.save(itemsClassVO);
         }
         
         public void updateItemsclass(ItemsClassVO itemsClassVO) {
        	repository.save(itemsClassVO);
          }
         
         public ItemsClassVO getOneItemsclass(Integer itemClassId) {
     		Optional<ItemsClassVO> optional = repository.findById(itemClassId);
    		return optional.orElse(null);
         }
         
     	public List<ItemsClassVO> getAll() {
     		return repository.findAll();
    	}
}
