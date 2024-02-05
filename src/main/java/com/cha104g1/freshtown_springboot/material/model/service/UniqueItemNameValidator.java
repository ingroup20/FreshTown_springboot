package com.cha104g1.freshtown_springboot.material.model.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO.UniqueItemName;

@Component
public class UniqueItemNameValidator implements ConstraintValidator<UniqueItemName, String> {
	@Autowired
    private ApplicationContext applicationContext;
	
	public boolean isValid(String itemName, ConstraintValidatorContext context) {
        MaterialService materialSvc = applicationContext.getBean(MaterialService.class);
        return (materialSvc.findMaterialByItemName(itemName) == null);
    }
}
