package main;
import java.util.*;
import model.Student;
import dao.StudentDAO;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== Student Registration System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    dao.addStudent(new Student(name, email, course, age));
                }
                case 2 -> {
                    System.out.println("\nID    Name                 Email                     Course          Age");
                    System.out.println("--------------------------------------------------------------------------");
                    dao.getAllStudents().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter student ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter new course: ");
                    String course = sc.nextLine();
                    System.out.print("Enter new age: ");
                    int age = sc.nextInt();
                    dao.updateStudent(new Student(id, name, email, course, age));
                }
                case 4 -> {
                    System.out.print("Enter student ID to delete: ");
                    int id = sc.nextInt();
                    dao.deleteStudent(id);
                }
                case 5 -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice, try again.");
            }
        }
    }
}
