package empcontroller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import empexception.ResourceNotFound;

import org.springframework.web.bind.annotation.RequestBody;

import emprepository.EmployeeRepository;
import empmodel.Employee;

@Controller
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	//add a new employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable Integer id) {
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee not exist with id:"+id));
		return ResponseEntity.ok(employee);
	}
	
	//get employee by name
	@GetMapping("/employees/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name) {
		List<Employee> employee=employeeRepository.findByName(name);
		return ResponseEntity.ok(employee);
	}
	
	//get employee by designation
		@GetMapping("/employees/{desid}")
		public ResponseEntity<List<Employee>> getEmployeeByDesignation(@PathVariable Integer desigid) {
			List<Employee> employee=employeeRepository.findByDesignation(desigid);
			return ResponseEntity.ok(employee);
		}
		
	//get employee by salary ascending
		@GetMapping("/employees/sort")
		public List<Employee> getAllEmployeeinSortedOrder(){
			List<Employee> list = employeeRepository.findAll();
			Collections.sort(list,new salaryCompare());
			return list;
		}
		
	
	//update employee by id
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@RequestBody Employee emp) {
		
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("employee not exist with id:"+id));
		
		employee.setId(emp.getId());
		employee.setName(emp.getName());
		employee.setSalary(emp.getSalary());
		
		Employee updEmp=employeeRepository.save(employee);
		
		return ResponseEntity.ok(updEmp);
	}
	
	//delete employee by id
	@DeleteMapping("/employees/{id}")
	public  ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Integer id) {
		Employee desig=employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee not exist with id:"+id));
		employeeRepository.delete(desig);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
