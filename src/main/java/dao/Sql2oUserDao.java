package dao;

import DB.DB;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {

    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o){this.sql2o = sql2o;}

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (username, address, phone, email, departmentid, position, roles) VALUES (:userName, :address, :phone, :email, :departmentId, :position, :roles);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM users")
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public List<User> getAllUsersByDepartment(int departmentId) {
        String sql = "SELECT * FROM users WHERE departmentId=:departmentId";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id=:id;";
        try(Connection conn = sql2o.open()) {
           return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
