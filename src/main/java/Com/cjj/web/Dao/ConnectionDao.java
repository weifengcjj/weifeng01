package Com.cjj.web.Dao;

import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Component(value = "ConnectionDao")
public class ConnectionDao {
    public static Connection getcon() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/weifeng9?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "20040309cjj");
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet)
    {
        try {
            if(resultSet!=null)
            {
                resultSet.close();
            }
            if(connection!=null)
            {
                connection.close();
            }
            if(statement!=null)
            {
                statement.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
