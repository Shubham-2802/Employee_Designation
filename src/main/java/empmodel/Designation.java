package empmodel;

import javax.persistence.*;

@Entity
@Table(name="designation")
public class Designation {
	
	@Id
	@OneToMany(mappedBy="designation_id")
	private int id;
	private String designation_name;
	
	public Designation() {
		super();
	}
	public Designation(int id, String designation) {
		super();
		this.id = id;
		this.designation_name = designation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation_name;
	}
	public void setDesignation(String designation) {
		this.designation_name = designation;
	}
	
	
}
