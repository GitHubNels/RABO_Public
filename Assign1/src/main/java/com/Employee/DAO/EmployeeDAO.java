package com.Employee.DAO;

import org.springframework.stereotype.Repository;

import com.Employee.Model.Employee;
import com.Employee.Model.Employees;
import com.Employee.Model.Login;

@Repository
public class EmployeeDAO 
{
    private static Employees list = new Employees();
    private static Login users = new Login();
    private static  Employee employee;
    
    static 
    {
        list.getEmployeeList().add(new Employee(1, "Asda", "Gupta", "howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex", "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
       
    }
    
    public Employees getAllEmployees() 
    {
    	System.out.println(list);
        return list;
    }
    public Employee getEmployee(String empName) 
    {
    	Employee employee = null;
    	for (Employee emp :  list.getEmployeeList()) {
    		if(empName.equals(emp.getName()))
    		{
    			employee=emp;
    			return emp;
    		}
		}
		return employee;
    }
    
    
    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
    
    public boolean update(String uname)
    { boolean stats=false;
    	for (Employee emp :  list.getEmployeeList()) {
    		if(employee.getName().equals(emp.getName()))
    		{
    			if(!employee.getName().isEmpty())
    				employee.setName(emp.getName());
    			stats=true;
    		}
    		
		}
		return stats;
    }
    
    public boolean remove(String uname)
    {boolean stats=false;
    	for (Employee emp :  list.getEmployeeList()) {
    		if(employee.getName().equals(emp.getName()))
    		{
    			employee.setName("");
    			list.getEmployeeList().remove(emp);//.add(employee);
    			stats=true;
    		}
 		}
		return stats;
    }
}
