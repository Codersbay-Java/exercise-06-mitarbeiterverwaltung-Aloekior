package application;

public class Employee {
	final Department dept;

	String firstName;
	String lastName;
	int id;

	public Employee(String firstName, String lastName, int id, Department dept) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.dept = dept;
	}


	public Employee(int id) {
		firstName = "Max";
		lastName = "Mustermann";
		this.id = id;
		dept = new Department();
	}

	public Employee() {
		firstName = "Max";
		lastName = "Mustermann";
		id = 123;
		dept = new Department();
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
