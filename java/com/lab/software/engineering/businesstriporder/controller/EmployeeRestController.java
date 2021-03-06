
package com.lab.software.engineering.businesstriporder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lab.software.engineering.businesstriporder.entity.Employee;
import com.lab.software.engineering.businesstriporder.service.EmployeeService;
import org.springframework.http.HttpStatus;
 

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	 
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}

	@RequestMapping(value = "/addemployee", method = RequestMethod.POST,   produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody()
	   public Employee addNewEmployee(@RequestBody Employee employee) {
	       return this.employeeService.addEmployee(employee);
    }
	
	@RequestMapping(value = "/updateemployee/{id}", method = RequestMethod.POST,   produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ResponseBody()
	   public Employee updateEmployee(@RequestBody Employee emp, @PathVariable("id") int id) {
		return this.employeeService.updateEmployee(id, emp);
    }
	 //if the employee has a vehicle, he cannot be deactivated, implement that mate
	@RequestMapping(value = "/deactivateemployee/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	   public ResponseEntity<?>  deativateEmployee(@PathVariable("id")  int id) { 
	       employeeService.deactivateEmployee(id);
	       return  ResponseEntity.ok().body("Employee has been deactivated.");
	}  
	@RequestMapping(value = "/activateemployee/{id}", method = RequestMethod.PUT) 
	@ResponseStatus(HttpStatus.OK)
	   @ResponseBody()
	   public void activateEmployee(@PathVariable ("id")  int id) { 
		   employeeService.activateEmployee(id);
	} 
	  
}

