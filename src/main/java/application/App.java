package application;

import java.util.Random;
import java.util.Scanner;

public class App {
    // ------ static variables start ------

    static int numberOfIds = 0;
    static int numberOfDepartments = 0;
    static int numberOfEmployees = 0;
    static Scanner scanner = new Scanner(System.in); // initialise Scanner for input
    static Random rand = new Random();
    static int[] ids = new int[1]; // generate array containing created IDs
    static Department[] departments = new Department[1]; // generate array containing departments
    static Employee[] employees = new Employee[1]; // generate array containing employees

    // ------ static variables end ------


    public static void main(String[] args) {
        application(); // launch interface application
    }

    private static void application() {
        Department department = new Department();

        System.out.println("Wollen Sie eine Abteilung anlegen? (j für ja / n für nein)");
        String departmentCreation = scanner.nextLine();

        if (departmentCreation.equalsIgnoreCase("j")) {
            department = createDepartment();
        } else {
            if (departments[0] == null) {
                System.out.println("Keine Abteilungen gefunden! Testabteilung wurde angelegt.");
                departments[0] = new Department();
                numberOfDepartments++;
            } else {
                System.out.println("Für welche Abteilung wollen Sie den Mitarbeiter anlegen?");

                for (int i = 0; i < departments.length; i++) {
                    if (departments[i] != null) {
                        System.out.println((i + 1) + ") " + departments[i].name);
                    } else {
                        break;
                    }
                }
                int departmentSelection = scanner.nextInt();
                department = departments[departmentSelection - 1];
            }
        }

        createEmployee(department);

        repeatQuestion();
    }

    private static int[] generateID() {
        if (numberOfIds >= ids.length) { // check if ID array needs to be extended
            increaseIdArray();
        }

        int id = rand.nextInt(10000) + 1000;

        // ----- check if generated ID already exists
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                generateID(); //restart generation, if generated ID already exists
            }
        }

        ids[numberOfIds] = id; //write new ID to ID-Array
        numberOfIds++; // increase ID counter
        return ids; //return array with IDs
    }

    private static void increaseIdArray() {
        int[] idsNew = new int[2 * ids.length]; // create new array with double size of previous array
        for (int i = 0; i < ids.length; i++) { // copy data from old array to new array
            idsNew[i] = ids[i];
        }
        ids = idsNew; // return new array
    }

    private static void increaseDepartmentsArray() {
        Department[] departmentsNew = new Department[2 * departments.length]; // create new array with double size of previous array
        for (int i = 0; i < departments.length; i++) { // copy data from old array to new array
            departmentsNew[i] = departments[i];
        }
        departments = departmentsNew; // return new array
    }

    private static void increaseEmployeeArray() {
        Employee[] employeesNew = new Employee[2 * employees.length]; // create new array with double size of previous array
        for (int i = 0; i < employees.length; i++) { // copy data from old array to new array
            employeesNew[i] = employees[i];
        }
        employees = employeesNew; // return new array
    }

    private static void printEmployees() {
        for (int i = 0; i < employees.length; i++) { // iterate through all employee entries of employee array
            if (employees[i] != null) { // only output employee object if entry contains data
                employees[i].print();
            } else { // break loop after reaching last entry
                break;
            }
        }
    }

    private static Department createDepartment() {
        // ----- input department information
        System.out.println("Bitte legen Sie eine Abteilung an:" + "\n");
        System.out.print("Name ");
        String departmentName = scanner.nextLine();
        System.out.print("Land ");
        String departmentCountry = scanner.nextLine();
        System.out.print("Stadt ");
        String departmentCity = scanner.nextLine();

        // ----- create department object
        generateID();
        Department department = new Department(departmentName, ids[(numberOfIds - 1)], departmentCountry, departmentCity);
        if (numberOfDepartments >= departments.length) {
            increaseDepartmentsArray();
        }
        departments[numberOfDepartments] = department;
        numberOfDepartments++;
        return department;
    }

    private static void createEmployee(Department department) {
        // ----- input employee information
        System.out.println("\n" + "Bitte legen Sie einen Mitarbeiter an:" + "\n");
        System.out.print("Vorname ");
        String firstName = scanner.nextLine();
        System.out.print("Nachname ");
        String lastName = scanner.nextLine();


        // ----- create employee object
        generateID();
        Employee employee = new Employee(firstName, lastName, ids[(numberOfIds - 1)], department);

        // ----- check if employee array needs to be extended
        if (numberOfEmployees >= employees.length) {
            increaseEmployeeArray();
        }

        // ----- write employee object to employee array
        employees[numberOfEmployees] = employee;
        numberOfEmployees++; // increase employee count

        System.out.println("\n" + "Sie haben folgenden Mitarbeiter angelegt:");
        employee.print(); // print line to show created employee information
    }

    public static void repeatQuestion() {
        System.out.println("\n" + "Möchten Sie noch einen Mitarbeiter anlegen?");
        System.out.println("j für ja und n für beenden");
        String repeat = scanner.nextLine();

        if (repeat.equalsIgnoreCase("j")) { // repeat employee entry
            System.out.println();
            application();
        } else if (repeat.equalsIgnoreCase("n")) { // quit application
            printEmployees();
            System.out.println("\n" + "Auf Wiedersehen!");
        } else { // wrong input
            System.out.println("\n" + "Ungültige Eingabe.");
            repeatQuestion();
        }
    }
}
