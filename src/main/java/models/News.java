package models;

import java.util.Objects;

public class News {

    private String news;
    private int departmentId = -1;
    private int id;

    public News(String news){
        this.news = news;
    }
    public News(String news, int departmentId){
        this.news = news;
        this.departmentId = departmentId;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news1 = (News) o;
        return getDepartmentId() == news1.getDepartmentId() &&
                getId() == news1.getId() &&
                getNews().equals(news1.getNews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNews(), getDepartmentId(), getId());
    }
}
