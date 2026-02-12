import java.util.*;
import java.sql.*;

public class StudentService {

    static ArrayList<Student> students = new ArrayList<>();

    //====== Grde Calculation Method ======//
    String calculateGrade(double marks) {
        if(marks >= 90) {
            return "A";
        } else if(marks >= 80) {
            return "B";
        } else if(marks >= 70) {
            return "C";
        } else if(marks >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    //============ Add Student ============//
    void addStudent(Scanner sc) {

        try {
            //Get DB connection
            Connection con = DBConnection.getConnection();

            //ID Input  
            System.out.print("\nEnter Student ID: ");
            String id = sc.nextLine();

            // Check for duplicate ID
            for(Student student : students) {
                if(student.id.equals(id)) {
                    System.out.println("Student ID already exists. Please try again.");
                    return;
                }
            }

            //Name Input
            System.out.print("\nEnter Student Name: ");
            String name = sc.nextLine();

            //Age Input
            System.out.print("\nEnter Student Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume newline

            //Gender Selection
            System.out.println("1.Male");
            System.out.println("2.Female");
            System.out.println("3.Others");
            System.out.print("\nSelect Gender: ");

            int gChoice = sc.nextInt();
            sc.nextLine(); 

            String gender;

            switch(gChoice) {
                case 1 -> gender = "Male";
                case 2 -> gender = "Female";
                case 3 -> gender = "Others";
                default -> {
                    System.out.println("Invalid choice. Defaulting to 'Others'.");
                    gender = "Others";
                }
            }

            //Course Input
            System.out.print("\nEnter Student Course: ");
            String course = sc.nextLine();

            //Marks Input
            System.out.print("\nEnter Student Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine(); // Consume newline

            if(marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                return;
            }

            // Calculate Grade
            String grade = calculateGrade(marks);
            System.out.println("Calculated Grade: " + grade);

            // Phone Input
            System.out.print("\nEnter Student Phone: ");
            String phone = sc.nextLine();

            // Email Input
            System.out.println("Validating email format...");
            System.out.print("\nEnter Student Email: ");
            String email = sc.nextLine();

            // SQL Inser Query
            String query = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement and set parameters
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, course);
            ps.setDouble(6, marks);
            ps.setString(7, grade);
            ps.setString(8, phone);
            ps.setString(9, email);

            // Execute the insert query
            int rowsAffected = ps.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Student added to database successfully!");
            } else {
                System.out.println("Failed to add student to database.");
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    //============ Display Student ============//
    void displayStudent() {

        try {

            // Get DB connection
            Connection con = DBConnection.getConnection();

            // SQL Select Query
            String query = "SELECT * FROM students";

            // Create Statement
            Statement stmt = con.createStatement();

            // Execute the query and get ResultSet
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n=====Student RECORDS =====");

            // Fetch each row
            while(rs.next()) {
                System.out.println("ID : " + rs.getString("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Gender : " + rs.getString("gender"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Grade : " + rs.getString("grade"));
                System.out.println("Phone : " + rs.getString("phone"));
                System.out.println("Email : " + rs.getString("email"));

                System.out.println("-----------------------");
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println("Error displaying students: " + e.getMessage());
        }
    }

    //============ Search Student ============//
    void searchStudent(Scanner sc) {

        try {
            // Get DB connection
            Connection con = DBConnection.getConnection();

            //Input ID 
            System.out.println("\n Enter Student ID to Search: ");

            //SQL Query
            String query = "SELECT * FROM students WHERE id = ?";

            //Prepare Statement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sc.nextLine());

            //Execute Query
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.println("\n===== STUDENT FOUND =====");
                System.out.println("ID : " + rs.getString("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Gender : " + rs.getString("gender"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Grade : " + rs.getString("grade"));
                System.out.println("Phone : " + rs.getString("phone"));
                System.out.println("Email : " + rs.getString("email"));
            } else {
                System.out.println("Student not found.");
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println("Error searching student: " + e.getMessage());
        }
    }

    //============ Delete Student ============//
    void DeleteStudent(Scanner sc) {

        try {
            // Get DB connection
            Connection con = DBConnection.getConnection();

            //Input ID
            System.out.println("\nEnter Student ID to Delete: ");

            //SQL Query
            String query = "DELETE FROM students WHERE id = ?";

            //Prepare Statement
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, sc.nextLine());
            
            //Execute Query
            int rowsAffected = ps.executeUpdate();

            //Check if deletion was successful
            if(rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found. Deletion failed.");
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    //============ Update Student ============//
    void UpdateStudent(Scanner sc) {

        try {
            //Get DB Connection
            Connection con = DBConnection.getConnection();

            //Input ID
            System.out.println("\n Enter Student ID to Update: ");
            String id = sc.nextLine();

            // Check if student exists
            String checkquery = "SELECT * FROM students WHERE id = ?";

            // Prepare Statement
            PreparedStatement checkps = con.prepareStatement(checkquery);
            checkps.setString(1, id);

            // Execute Query
            ResultSet rs = checkps.executeQuery();

            if(!rs.next()) {
                System.out.println("Student not found.");
                return;
            }

            // Input new details
            System.out.print("\nEnter New Name: ");
            String name = sc.nextLine();

            System.out.print("\nEnter New Age: ");
            int age = sc.nextInt();
            sc.nextLine(); // Consume newline

            System.out.println("1.Male");
            System.out.println("2.Female");
            System.out.println("3.Others");
            System.out.print("\nSelect New Gender: ");
            int gChoice = sc.nextInt();
            sc.nextLine();
            
            String gender;  
            switch(gChoice) {
                case 1 -> gender = "Male";
                case 2 -> gender = "Female";
                case 3 -> gender = "Others";
                default -> {
                    System.out.println("Invalid choice. Defaulting to 'Others'.");
                    gender = "Others";
                }
            }

            System.out.print("\nEnter New Course: ");
            String course = sc.nextLine();

            System.out.print("\nEnter New Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine(); // Consume newline

            if(marks < 0 || marks > 100) {
                System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                return;
            }

            String grade = calculateGrade(marks);

            System.out.println("Calculated Grade: " + grade);

            System.out.print("\nEnter New Phone: ");
            String phone = sc.nextLine();

            System.out.print("\nEnter New Email: ");
            String email = sc.nextLine();

            // SQL Update Query
            String updateQuery = "UPDATE students SET " + "name = ?, age = ?, gender = ?," + " course = ?, marks = ?, grade = ?, phone = ?, email = ? WHERE id = ?";

            // Prepare Statement
            PreparedStatement ps = con.prepareStatement(updateQuery);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, course);
            ps.setDouble(5, marks);
            ps.setString(6, grade);
            ps.setString(7, phone);
            ps.setString(8, email);
            ps.setString(9, id);

            // Execute Update
            int rowsAffected = ps.executeUpdate();

            // Check if update was successful
            if(rowsAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found. Update failed.");
            }

        }
        catch(Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
}
