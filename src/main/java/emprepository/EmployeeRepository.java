package emprepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import empmodel.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	
	List<Employee> findByName(String name);

	List<Employee> findByDesignation(Integer designation_id);
}
