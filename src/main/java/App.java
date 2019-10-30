//import static spark.Spark.port;
import  com.google.gson.*;

import DB.DB;
import dao.*;
import exceptions.ApiException;
import models.*;
import spark.ModelAndView;
import spark.template.handlebars.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {

        staticFileLocation("/public");

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        }else {
            port = 4567;
        }
        port(port);

        Sql2oUserDao userDao = new Sql2oUserDao(DB.sql2o);
        Sql2oNewsDao newsDao = new Sql2oNewsDao(DB.sql2o);
        Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        final String notAvailableMsg = "Sorry, we do not have any %s to display currently",notAvailable;
        final String cannotBeEmptyMsg = "Warning!!!, %s cannot be empty!!!, Please try again",cannotBeEmpty;

        Gson gson = new Gson();

        //TEMPLATES
        get("/",(req, res)->{
            Map<String, Object> model = new HashMap<>();
            List<News> news = newsDao.getAll();
            int deptId = 2;

            Department department = departmentDao.findById(deptId);
            model.put("news", news);
            model.put("deptId", deptId);
            model.put("department", department);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        get("/dash",(req, res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "dash.hbs");
        }, new HandlebarsTemplateEngine());
        post("/new/news", (req, res)->{
            Map<String, Object> model = new HashMap<>();
            String news = req.queryParams("news");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            News newNews = new News(news,departmentId);
            newsDao.add(newNews);
            return new ModelAndView(model, "dash.hbs");
        }, new HandlebarsTemplateEngine());
        post("/new/user", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String userName = req.queryParams("userName");
            String address = req.queryParams("address");
            String phone = req.queryParams("phone") ;
            String email = req.queryParams("email");
            int departmentId = Integer.parseInt(req.queryParams("departmentId"));
            String position = req.queryParams("position");
            String roles = req.queryParams("roles");
            User newUser = new User(userName,address,phone,email,departmentId,position,roles);
            userDao.add(newUser);
            return new ModelAndView(model, "dash.hbs");
        }, new HandlebarsTemplateEngine());
        post("/new/department",(req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name") ;
            String description = req.queryParams("description") ;
            int employees = Integer.parseInt(req.queryParams("employees"));
            Department newDepartment = new Department(name, description, employees);
            departmentDao.add(newDepartment);
            return new ModelAndView(model, "dash.hbs");
        }, new HandlebarsTemplateEngine());


        // JSON READ
        get("/users", "application/json",(req, res) -> {
            if(userDao.getAll().size() < 1){
                throw new ApiException(404, String.format(notAvailableMsg,"Users"));
            }
            res.type("application/json");
            return gson.toJson(userDao.getAll());
        });
        get("/news", "application/json",(req, res) ->{
           if(newsDao.getAll().size() < 1){
               throw new ApiException(404, String.format(notAvailableMsg,"news"));
           }
           res.type("application/json");
           return gson.toJson(newsDao.getAll());
        });
        get("/departments", "application/json",(req, res) ->{
           if(departmentDao.getAll().size() < 0){
                throw new ApiException(404, String.format(notAvailableMsg,"departments"));
           }
           res.type("application/json");
           return gson.toJson(departmentDao.getAll());
        });

        // JSON CREATE
        post("/users/new", "application/json", (req, res)->{
            User user = gson.fromJson(req.body(), User.class);
            if(user == null || user.getUserName() == null){
                throw new ApiException(404, String.format(cannotBeEmptyMsg,"User"));
            }
            userDao.add(user);
            res.type("application/json");
            res.status(201);
            return gson.toJson(user);
        });
        post("/news/new", "application/json", (req, res)->{
            News news = gson.fromJson(req.body(), News.class);
            if(news == null || news.getNews() == null){
                throw new ApiException(404, String.format(cannotBeEmptyMsg,"User"));
            }
            newsDao.add(news);
            res.type("application/json");
            res.status(201);
            return gson.toJson(news);
        });
        post("/department/new", "application/json", (req, res) -> {
           Department department = gson.fromJson(req.body(), Department.class);
           if (department == null){
               throw new ApiException(404, String.format(cannotBeEmptyMsg,"Department"));
           }
           departmentDao.add(department);
           res.type("application/json");
           res.status(201);
           return gson.toJson(department);
        });


        exception(ApiException.class, (exc, req, res) ->{
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

    }
}
