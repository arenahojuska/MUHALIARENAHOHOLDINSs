package net.javaguide.springboot_rest_api.bean;

public class Student {

    private int id ;
    private String firstName;
    private String lastName ;

    public Student(String firstName, int id, String lastName) {
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
