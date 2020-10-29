package com.beto.apps.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beto.apps.models.Admin;
import com.beto.apps.service.repository.AdminRepository;

@Component
public class AdminImpl {
@Autowired
private AdminRepository db;

public Admin getAdmin() {
	
	return db.findAll().isEmpty()?null:db.findAll().get(0);
}
}
