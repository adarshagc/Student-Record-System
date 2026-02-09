import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class StudentService {

    static ArrayList<Student> students = new ArrayList<>();

    //============ Add Student ============//
    void addStudent(Scanner sc) {

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

        //Grade Input
        System.out.print("\nEnter Student Grade: ");
        String grade = sc.nextLine();

        // Phone Input
        System.out.print("\nEnter Student Phone: ");
        String phone = sc.nextLine();

        // Email Input
        System.out.print("\nEnter Student Email: ");
        String email = sc.nextLine();

        // Create and add the student to the list   
        Student s = new Student(id, name, age, gender, course, grade, phone, email);
        students.add(s);

        // Save the student information to a file
        saveStudentsToFile(); // Save all students to the file

        System.out.println("Student added successfully!");
    }

    //============ Display Student ============//
    void displayStudent() {
        // Check if there are students to display
        if(students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        } else {
            // Display student information
            System.out.println("\nStudent Information:");
            System.out.println("-----------------------");
            for (Student student : students) {
                student.display();
            }
        }
    }

    //============ Search Student ============//
    void searchStudent(Scanner sc) {

        System.out.println("\nEnter Student ID to search: ");
        String id = sc.nextLine();
        boolean found = false;

        // Search for the student by ID and display their information if found
        for(Student student : students) {
            if(student.id.equals(id)) {
                student.display();
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Student not found.");
        }
    }

    //============ Delete Student ============//
    void DeleteStudent(Scanner sc) {
        
        System.out.println("Enter the Student ID to delete: ");
        String id = sc.nextLine();
        boolean found = false;
        Iterator<Student> iterator = students.iterator();

        // Search for the student by ID and remove them from the list if found
        while(iterator.hasNext()) {
            Student student = iterator.next();
            if(student.id.equals(id)) {
                iterator.remove();
                saveStudentsToFile(); //rewrite file after deletion
                System.out.println("Student deleted successfully!");
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Student not found.");
        }
    }

    //============ Update Student ============//
    void UpdateStudent(Scanner sc) {

        System.out.println("Enter the student ID to update: ");
        String id = sc.nextLine();
        boolean found = false;

        // Search for the student by ID and update their information if found
        for(Student student : students) {

            if(student.id.equals(id)) {

                // Update student name
                System.out.println("Enter new name for the student: ");
                String newName = sc.nextLine();
                student.name = newName;

                // Update student age
                System.out.println("Enter new age for the student: ");
                int newAge = sc.nextInt();
                sc.nextLine(); // Consume newline
                student.age = newAge;
                
                //Update student gender
                System.out.println("Enter new gender for the student: ");
                String newGender = sc.nextLine();
                student.gender = newGender;

                //Update student course
                System.out.println("Enter new course for the student: ");
                String newCourse = sc.nextLine();
                student.course = newCourse;

                //Update student grade
                System.out.println("Enter new grade for the student: ");
                String newGrade = sc.nextLine();
                student.grade = newGrade;

                //Update student phone
                System.out.println("Enter new phone for the student: ");
                String newPhone = sc.nextLine();
                student.phone = newPhone;

                //Update student email  
                System.out.println("Enter new email for the student: ");
                String newEmail = sc.nextLine();
                student.email = newEmail;

                saveStudentsToFile(); //rewrite file after update

                System.out.println("Student information updated successfully!");
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Student not found.");
        }
    }

    //============ Sort Students ============//
    void SortStudents() {

        if(students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        } else {

            // Sort students by ID using a comparator
            students.sort(Comparator.comparing(student -> student.id));
            System.out.println("Students sorted by ID successfully!");
        }
    }

    //============ Save One (APPEND) ============//
    void saveStudentsToFile(Student student) {

        try {
            FileWriter fw = new FileWriter("students.txt", true);

            fw.write(
                student.id + "," +
                student.name + "," +
                student.age + "," +
                student.gender + "," +
                student.course + "," +
                student.grade + "," +
                student.phone + "," +
                student.email + "\n"
            );
            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    //============ Save All (OVERWRITE) ============//
    void saveStudentsToFile() {
        try {
            FileWriter fw = new FileWriter("students.txt");

            for(Student student : students) {
                fw.write(
                    student.id + "," +
                    student.name + "," +
                    student.age + "," +
                    student.gender + "," +
                    student.course + "," +
                    student.grade + "," +
                    student.phone + "," +
                    student.email + "\n"
                );
            }
            fw.close();
            System.out.println("Students saved to file successfully!");

        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    //============ Load Students from File ============//   
    void loadStudentsFromFile() {
        // This method can be implemented to read student information from the file and populate the students list when the application starts.
        try {
            File file = new File("students.txt");

            if(!file.exists()) {
                System.out.println("No existing student records found.");
                return;
            }
            Scanner fileScanner = new Scanner(file);

            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                Student student = new Student(
                    data[0], // id
                    data[1], // name
                    Integer.parseInt(data[2]), // age
                    data[3], // gender
                    data[4], // course
                    data[5], // grade
                    data[6], // phone
                    data[7]  // email
                );

                students.add(student);
            }
            fileScanner.close();
            System.out.println("Students loaded from file successfully!");
        }
        catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
