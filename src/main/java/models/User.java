package models;

import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String address;
    private String phone;
    private String email;
    private int departmentId;
    private String position;
    private String roles;

    public User(String userName, String address, String phone, String email, int departmentId, String position, String roles){
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.departmentId = departmentId;
        this.position = position;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getDepartmentId() == user.getDepartmentId() &&
                getUserName().equals(user.getUserName()) &&
                getAddress().equals(user.getAddress()) &&
                getPhone().equals(user.getPhone()) &&
                getEmail().equals(user.getEmail()) &&
                getPosition().equals(user.getPosition()) &&
                getRoles().equals(user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getAddress(), getPhone(), getEmail(), getDepartmentId(), getPosition(), getRoles());
    }
}
