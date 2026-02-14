public class Person {
    String id;
    String name;
    int age;
    String gender;
    String phone;
    String email;

    public Person(String id, String name, int age, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }
    //Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

