package emprepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import empmodel.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation,Integer>{
	
	List<Designation> findByName(String designation_name);

}
