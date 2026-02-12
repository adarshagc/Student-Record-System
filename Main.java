import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Test DB Connection
        DBConnection.getConnection();

        // Create an instance of StudentService to manage student records
        StudentService student = new StudentService();

        System.out.println("Welcome to the Student Record System! - Final Version");

        // Main menu loop
        while(true) {
            System.out.println("\n1.Add Student");
            System.out.println("\n2.Display Students");
            System.out.println("\n3.Search Student");
            System.out.println("\n4.Delete Student");
            System.out.println("\n5.Update Student");
                
            System.out.println("\n6.Exit");

            System.out.print("\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch(choice) {
                case 1 -> student.addStudent(sc);
                case 2 -> student.displayStudent();
                case 3 -> student.searchStudent(sc);
                case 4 -> student.DeleteStudent(sc);
                case 5 -> student.UpdateStudent(sc); 
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again."); 
            }
        }
        
    }
}

