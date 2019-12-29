package com.raj.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.raj.pma.dao.EmployeeRepository;

@Service
public class EmployeeService {

	// This is example for field injection
	@Autowired
	EmployeeRepository empRepo;

	
	// @Qualifier example with field injection
	/*
	 * @Qualifier("staffReopsitoryImpl2")
	 * 
	 * @Autowired 
	 * IStaffRepository iRepo;
	 */
	
	IStaffRepository iRepo;


	@Qualifier("staffReopsitoryImpl2")
	@Autowired
	public void setiRepo(IStaffRepository iRepo) {
		this.iRepo = iRepo;
	}
	
	
	

	// @Qualifier example with constructor injection
	/*
	 * public EmployeeService(@Qualifier("staffReopsitoryImpl2") IStaffRepository
	 * iRepo) { super(); this.iRepo = iRepo; }
	 */
	
	
	
	//This is example for constructor injection
	/*
	 * public EmployeeService(EmployeeRepository ezzzzzmpRepo) { this.empRepo =
	 * empRepo; }
	 */
	
	// This is example for setter injection
	/*
	 * @Autowired public void setEmpRepo(EmployeeRepository empRepo) { this.empRepo
	 * = empRepo; }
	 */
	
	
	
	
}
