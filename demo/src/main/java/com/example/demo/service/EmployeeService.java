package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.model.Employee;
public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public void saveEmployee(Employee emp);
	public Employee getEmployeeByID(long id); 
	public void deleteEmpByID(long id);
	public Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
