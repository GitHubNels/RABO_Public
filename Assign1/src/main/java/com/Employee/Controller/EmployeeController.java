package com.Employee.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Employee.DAO.*;//.EmployeeDAO;
import com.Employee.Exception.ErrorResponse;
import com.Employee.Model.*;

@RestController
@RequestMapping(value = "/Employee")
public class EmployeeController 
{
    @Autowired
    private EmployeeDAO employeeDao;
    
    @RequestMapping(value = "/Home" )
    public String greet() 
    {
    	return "All the API's are tested successfully";
       
    }
    
    @RequestMapping(value = "/getEmployees", method = RequestMethod.GET )
    public Employees getEmployees() 
    {
    	
    	System.out.println("Entered inside");
        return employeeDao.getAllEmployees();
    }
    @RequestMapping(value = "/name={ename}", method = RequestMethod.GET )
    public Employee getEmployee(String eName) 
    {
    	
    	System.out.println("Entered inside");
        return employeeDao.getEmployee(eName);//.getAllEmployees();
    }    
    
    
    @PostMapping(value = "/name={uname}/pwd= {pwd}/post", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee,
                        @RequestBody LogCredentials log,String uname,String pwd) 
                 throws Exception 
    {      
    	
    		if(log.logValidation(uname,pwd)) {
    			System.out.println("Login Successfull");
    		}
    		else
    		{
    			return null;
    		}
    	
        //Generate resource id
        Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);
               //add resource
        employeeDao.addEmployee(employee);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
    
    @RequestMapping(value = "/name={uname}/pwd= {pwd}/put", method = RequestMethod.PUT, 
    		consumes = "application/json", produces = "application/json")
    public Employee updateEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Employee employee,
                        @RequestBody LogCredentials log,String uname,String pwd) 
                 throws Exception 
    {      
    	
    		if(log.logValidation(uname,pwd)) {
    			System.out.println("Login Successfull");
    		}
    		else
    		{
    			return null;
    		}
    	
        employeeDao.update(uname);//addEmployee(employee);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
        
        //Send location in response
        return getEmployee(uname);//ResponseEntity.created(location).build();
    }
    
    
    @RequestMapping(value = "/name={uname}/pwd= {pwd}/delete", method = RequestMethod.DELETE, 
    		consumes = "application/json", produces = "application/json")
    public boolean deleteEmployee(String uname,String pwd)throws Exception 
    {      
    	
    		if(LogCredentials.logValidation(uname,pwd)) {
    			System.out.println("Login Successfull");
    		}
    		else
    		{
    			return false;
    		}
    	
        return employeeDao.remove(uname);
		      
    }    
    
    		
}
