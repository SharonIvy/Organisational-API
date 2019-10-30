package dao;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private Connection conn;
    private Sql2oUserDao userDao;

    @Before
    public void setup() throws Exception{
        String connectionString = "jdbc:postgresql://localhost:5432/org_api_test";
        Sql2o sql2o = new Sql2o(connectionString,"moringa","c3l12i9f6f6" );
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception{
//        userDao.clearAll();
        conn.close();
    }

    @Test
    public void add() {
        User user = setUpNewUser();
        assertEquals(user.getId(), user.getId());
    }

    @Test
    public void getAll() {
        User user = setUpNewUser();
        assertEquals(true, userDao.getAll().contains(user));
    }

    @Test
    public void getAllUsersByDepartment() {
        User user = setUpNewUser();
        List<User> allUserByDept = userDao.getAllUsersByDepartment(user.getDepartmentId());
        assertEquals(user.getDepartmentId(),allUserByDept.get(0).getDepartmentId());
    }

    @Test
    public void findById() {
        User user = setUpNewUser();
        assertEquals(user, userDao.findById(user.getId()));
    }

    @Test
    public void deleteById() {
        User user = setUpNewUser();
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void clearAll() {
        User user = setUpNewUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());

    }

    //helpers
    public User setUpNewUser(){
        User user = new User("Arnold Oduma","5094","+254-234-093-940","ano@gmail.com",24, "Department head","Networking, Customer relations");
        userDao.add(user);
        return user;
    }
}