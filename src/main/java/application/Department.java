package application;

public class Department {
	static int numberOfDepartments = 0;


	String name;
	int id;
	String country;
	String city;

	public Department(String name, int id, String country, String city) {
		this.name = name;
		this.id = id;
		this.country = country;
		this.city = city;
		numberOfDepartments++;
	}

	public Department() {
		name = "Test";
		id = 123;
		country = "Neverland";
		city = "Gotham";
		numberOfDepartments++;
	}


	public boolean isEquals(Department other) {
		if (this.id == other.id) {
			return true;
		}
		return false;
	}
}
