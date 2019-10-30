package dao;

import models.User;

import java.util.List;

public interface UserDao {

    //create
    void add(User user);

    //read
    List<User> getAll();
    List<User> getAllUsersByDepartment(int departmentId);
    User findById(int id);


    //update
//    void update(int id, String name);

    //delete
    void deleteById(int id);
    void clearAll();
}

