package Com.cjj.web.Dao;

import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Component(value = "UserDao")
public class UserDao{
    @Autowired
    static private JdbcTemplate jdbcTemplate;

    public static Connection getcon() throws ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/weifeng8?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
            connection = DriverManager.getConnection(url, "root", "20040309cjj");
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
        return connection;
    }
    public static void closeAll(Connection connection,Statement statement,ResultSet resultSet)
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
    public Set<TestundileForm> SelectGetuser() {
        Set<TestundileForm> list=new HashSet<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=getcon();
            st=con.createStatement();
            String sql="select * from user11_1";
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                TestundileForm tf=new TestundileForm();
                tf.setName(rs.getString("username"));
                tf.setPassword(rs.getString("password"));
                list.add(tf);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            closeAll(con,st,rs);
        }
        return  list;
    }
    public void insert(RegisterForm rf){
        // TODO Auto-generated method stub
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=getcon();
            String sql="insert into user11_1(username,password)values(?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,rf.getName());
            ps.setString(2,rf.getPassword());
            int af=ps.executeUpdate();//executeUpdate()方法返回的是整数，成功影响的记录数， 即成功插入记录数。

            System.out.println("添加用户成功！！！！成功插入-"+af+"-条数据");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            closeAll(con,st,rs);
        }

    }
}

