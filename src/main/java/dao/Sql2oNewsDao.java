package dao;

import DB.DB;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {

    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o){this.sql2o = sql2o;}

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (news, departmentId) VALUES (:news, :departmentId) ";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<News> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> getAllNewsByDepartment(int departmentId) {
        String sql = "SELECT * FROM news WHERE departmentId=:departmentId";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id=:id;";
        try(Connection conn = sql2o.open()) {
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public void update(int id, String news, int departmentId) {
        String sql = "UPDATE news SET (news, departmentId) = (:news, :departmentId) WHERE id=:id";
        try (Connection conn = sql2o.open()){
        conn.createQuery(sql,true)
                .addParameter("id", id)
                .addParameter("news", news)
                .addParameter("departmentId", departmentId)
                .throwOnMappingFailure(false)
                .executeUpdate();
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE id=:id";
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
        String sql = "DELETE FROM news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
