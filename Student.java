class Student extends Person {
    String course;
    double marks;
    String grade;

    public Student(String id, String name, int age, String gender, String phone, String email, String course, double marks, String grade) {
        super(id, name, age, gender, phone, email);
        this.marks = marks;
        this.course = course;
        this.grade = grade;
    }
    public double getMarks() {
        return marks;
    }
    public String getGrade() {
        return grade;
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