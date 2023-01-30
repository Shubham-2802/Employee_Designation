package empcontroller;

import java.util.Comparator;

import empmodel.Employee;

public class salaryCompare implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return (int)o1.getSalary()-(int)o2.getSalary();
	}
	
}
