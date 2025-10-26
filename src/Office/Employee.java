package Office;

public class Employee {
    private String firstName;
    private String lastName;
    private String surName;
    private String email;
    private String post;
    private Integer age;

    public Employee(String firstName, String lastName, String email, String phone, Integer age, String post, String surName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.surName = surName;
        this.age = age;
        this.email = email;
        this.post = phone;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
