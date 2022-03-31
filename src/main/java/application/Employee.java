package application;

public class Employee {
	static int numberOfEmployees = 0;
	final Department dept;

	String firstName;
	String lastName;
	int id;

	public Employee(String firstName, String lastName, int id, Department dept) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.dept = dept;
		numberOfEmployees++; // increase employee count
	}


	public Employee(int id) {
		this.firstName = "Max";
		this.lastName = "Mustermann";
		this.id = id;
		this.dept = new Department();
		numberOfEmployees++; // increase employee count
	}

	public Employee() {
		this.firstName = "Max";
		this.lastName = "Mustermann";
		this.id = 1234;
		this.dept = new Department();
		numberOfEmployees++; // increase employee count
	}

	public void print() {
		System.out.println("Der Mitarbeiter " + id + " " + firstName + " " + lastName + " arbeitet in der Abteilung " + dept.name + " in " + dept.city);
	}

	public boolean isEquals(Employee other) {
		if (this.id == other.id) {
			return true;
		}
		return false;
	}
}
