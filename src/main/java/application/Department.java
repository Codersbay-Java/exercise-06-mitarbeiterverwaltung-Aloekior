package application;

public class Department {
	/*
	 *
	 * Your code comes here. Remember you can overload constructors.
	 *
	 */
	String name;
	int id;
	String country;
	String city;

	public Department(String name, int id, String country, String city) {
		this.name = name;
		this.id = id;
		this.country = country;
		this.city = city;
	}

	public Department() {
		name = "Test";
		id = 123;
		country = "Neverland";
		city = "Gotham";
	}


	public boolean isEquals(Department other) {
		if (this.id == other.id) {
			return true;
		}
		return false;
	}
}
