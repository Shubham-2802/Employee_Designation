package empcontroller;

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

import emprepository.DesignationRepository;
import empmodel.Designation;

@Controller
@RequestMapping("/api/v1/")
public class DesignationController {
	
	@Autowired
	DesignationRepository designationRepository;
	
	//get all designations
	@GetMapping("/designations")
	public List<Designation> getAllDesignation(){
		return designationRepository.findAll();
	}
	
	//add a new designation
	@PostMapping("/designations")
	public Designation createDesignation(@RequestBody Designation desig) {
		return designationRepository.save(desig);
	}
	
	//get designation by id
	@GetMapping("/designations/{id}")
	public ResponseEntity<Designation> getDesignationByID(@PathVariable Integer id) {
		Designation desig=designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Designation not exist with id:"+id));
		return ResponseEntity.ok(desig);
	}
	
	//get designation by name
	@GetMapping("/designations/{name}")
	public ResponseEntity<List<Designation>> getDesignationByName(@PathVariable String name) {
		List<Designation> desig=designationRepository.findByName(name);
		return ResponseEntity.ok(desig);
	}
	
	//update designation by id
	@PutMapping("designations/{id}")
	public ResponseEntity<Designation> updateEmployee(@PathVariable Integer id,@RequestBody Designation designation) {
		
		Designation desig=designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Designation not exist with id:"+id));
		
		desig.setId(designation.getId());
		desig.setDesignation(designation.getDesignation());
		
		Designation updDesig=designationRepository.save(desig);
		
		return ResponseEntity.ok(updDesig);
	}
	
	//delete designation by id
	@DeleteMapping("/designations/{id}")
	public  ResponseEntity<Map<String,Boolean>> deleteDesignation(@PathVariable Integer id) {
		Designation desig=designationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Designation not exist with id:"+id));
		designationRepository.delete(desig);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
