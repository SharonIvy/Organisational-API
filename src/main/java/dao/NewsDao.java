package dao;

import models.News;

import java.util.List;

public interface NewsDao {

    //create
    void add(News news);

    //read
    List<News> getAll();
    List<News> getAllNewsByDepartment(int departmentId);
    News findById(int id);


    //update
    void update(int id, String news, int departmentId);

    //delete
    void deleteById(int id);
    void clearAll();
}
