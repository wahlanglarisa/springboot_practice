package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
@Service

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepository;
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}
	@Override
	public void saveEmployee(Employee emp) {
		this.empRepository.save(emp);
		// TODO Auto-generated method stub
		
	}
	@Override
	public Employee getEmployeeByID(long id) {
		// TODO Auto-generated method stub
		Optional<Employee> optional=empRepository.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			employee=optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id ::"+id);
		}
		return employee;
	}
	@Override
	public void deleteEmpByID(long id) {
		empRepository.deleteById(id);
		// TODO Auto-generated method stub
		
	}
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():Sort.by(sortField).descending();
		Pageable pageable= PageRequest.of(pageNo-1, pageSize,sort);
		// TODO Auto-generated method stub
		return this.empRepository.findAll(pageable);
	}


	

}
