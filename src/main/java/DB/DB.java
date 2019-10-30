package DB;

import org.sql2o.Sql2o;

public class DB {

    // DEVELOPMENT DATABASE
    private static String testConnectionString = "jdbc:postgresql://localhost:5432/org_api_test";
    private static String testUser = "moringa";
    private static String testPassword = "c3l12i9f6f6";
//    public static Sql2o sql2o = new Sql2o( testConnectionString, testUser, testPassword);// Comment this out if you are testing your app locally

    // PRODUCTION DATABASE
    private static String connectionString = "jdbc:postgresql://ec2-174-129-229-106.compute-1.amazonaws.com:5432/ddsnv9l24nb7pb";
    private static String user = "vreacoydvlrxdy";
    private static String password = "b721105fc5316a0793169bf515d3bd7ce3ff4147394a24a8de2074bf8bd82062";
    public static Sql2o sql2o = new Sql2o( connectionString, user, password);

}
