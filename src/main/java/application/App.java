package application;

import java.util.Random;
import java.util.Scanner;

public class App {
    static int numberOfIds = 0;
    static int numberOfDepartments = 0;
    static int numberOfEmployees = 0;
    static Random rand = new Random();



    public static void main(String[] args) {
        int[] ids = new int[1]; // generate array containing created IDs
        Department[] departments = new Department[1]; // generate array containing departments
        Employee[] employees = new Employee[1]; // generate array containing employees

        application(ids, departments, employees); // launch interface application
    }

    private static int[] generateID(int[] ids) {
        int id = rand.nextInt(10000) + 1000;

        // ----- check if generated ID already exists
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                generateID(ids); //restart generation, if generated ID already exists
            }
        }
        ids[numberOfIds] = id; //write new ID to ID-Array
        numberOfIds++; // increase ID counter
        return ids; //return array with IDs
    }

    private static void application(int[] ids, Department[] departments, Employee[] employees) {
        Scanner scanner = new Scanner(System.in); // initialise Scanner for input
        Department department;

        System.out.println("Wollen Sie eine Abteilung anlegen? (j für ja / n für nein)");
        String departmentCreation = scanner.nextLine();


        if (departmentCreation.equalsIgnoreCase("j")) {
            // ----- input department information
            System.out.println("Bitte legen Sie eine Abteilung an:" + "\n");
            System.out.print("Name ");
            String departmentName = scanner.nextLine();
            System.out.print("Land ");
            String departmentCountry = scanner.nextLine();
            System.out.print("Stadt ");
            String departmentCity = scanner.nextLine();


            // ----- create department object
            if (numberOfIds >= ids.length) { // check if ID array needs to be extended
                ids = increaseIdArray(ids);
            }
            generateID(ids);
            department = new Department(departmentName, ids[(numberOfIds - 1)], departmentCountry, departmentCity);
            if (numberOfDepartments >= departments.length) {
                departments = increaseDepartmentsArray(departments);
            }
            departments[numberOfDepartments] = department;
            numberOfDepartments++;

        } else {
            if (departments[0] == null) {
                department = new Department();
                System.out.println("Keine Abteilungen gefunden! Testabteilung wurde angelegt.");
                departments[0] = department;
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
                int departmentSelection = scanner.nextInt() - 1;
                department = departments[departmentSelection];
            }
        }

        // ----- input employee information
        System.out.println("\n" + "Bitte legen Sie einen Mitarbeiter an:" + "\n");
        System.out.print("Vorname ");
        String firstName = scanner.nextLine();
        System.out.print("Nachname ");
        String lastName = scanner.nextLine();


        // ----- create employee object
        if (numberOfIds >= ids.length) {
            ids = increaseIdArray(ids);
        }
        generateID(ids);
        Employee employee = new Employee(firstName, lastName, ids[(numberOfIds - 1)], department);

        // ----- check if employee array needs to be extended
        if (numberOfEmployees >= employees.length) {
            employees = increaseEmployeeArray(employees);
        }

        // ----- write employee object to employee array
        employees[numberOfEmployees] = employee;
        numberOfEmployees++; // increase employee count


        System.out.println("\n" + "Sie haben folgenden Mitarbeiter angelegt:");
        employee.print(); // print line to show created employee information

        System.out.println("\n" + "Möchten Sie noch einen Mitarbeiter anlegen?");
        System.out.println("j für ja und n für beenden");
        String repeat = scanner.nextLine();

        if (repeat.equalsIgnoreCase("j")) { // repeat employee entry
            System.out.println();
            application(ids, departments, employees);
        } else if (repeat.equalsIgnoreCase("n")) { // quit application
            printEmployees(employees);
            System.out.println("\n" + "Auf Wiedersehen!");
        } else { // wrong input
            System.out.println("\n" + "Ungültige Eingabe.");
        }
    }

    private static int[] increaseIdArray(int[] ids) {
        int[] idsNew = new int[2 * ids.length]; // create new array with double size of previous array
        for (int i = 0; i < ids.length; i++) { // copy data from old array to new array
            idsNew[i] = ids[i];
        }
        return idsNew; // return new array
    }

    private static Department[] increaseDepartmentsArray(Department[] departments) {
        Department[] departmentsNew = new Department[2 * departments.length]; // create new array with double size of previous array
        for (int i = 0; i < departments.length; i++) { // copy data from old array to new array
            departmentsNew[i] = departments[i];
        }
        return departmentsNew; // return new array
    }

    private static Employee[] increaseEmployeeArray(Employee[] employees) {
        Employee[] employeesNew = new Employee[2 * employees.length]; // create new array with double size of previous array
        for (int i = 0; i < employees.length; i++) { // copy data from old array to new array
            employeesNew[i] = employees[i];
        }
        return employeesNew; // return new array
    }

    private static void printEmployees(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) { // iterate through all employee entries of employee array
            if (employees[i] != null) { // only output employee object if entry contains data
                employees[i].print();
            } else { // break loop after reaching last entry
                break;
            }
        }
    }
}
