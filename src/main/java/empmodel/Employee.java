package empmodel;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int empid;
		private String name;
		private double salary;
		@ManyToOne(fetch= FetchType.EAGER)
		@JoinColumn
		private int designation_id;
		
		public Employee() {
			super();
		}
		public Employee(String name, double salary) {
			super();
			this.name = name;
			this.salary = salary;
		}
		public int getId() {
			return empid;
		}
		public void setId(int id) {
			this.empid = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public int getDesignation_id() {
			return designation_id;
		}
		public void setDesignation_id(int designation_id) {
			this.designation_id = designation_id;
		}
		
		
}
