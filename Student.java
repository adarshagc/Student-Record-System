class Student {
    String id;
    String name;
    int age;
    String gender;
    String course;
    double marks;
    String grade;
    String phone;
    String email;

    public Student(String id, String name, int age, String gender, double marks, String course, String grade, String phone, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.marks = marks;
        this.course = course;
        this.grade = grade;
        this.phone = phone;
        this.email = email;
    }

    void display() {
        System.out.println("Student ID : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Student Age : " + age);
        System.out.println("Student Gender : " + gender);
        System.out.println("Student Course : " + course);
        System.out.println("Student Grade : " + grade);
        System.out.println("Student Marks : " + marks);
        System.out.println("Student Phone : " + phone);
        System.out.println("Student Email : " + email);
        System.out.println("----------------------------");
    }
}