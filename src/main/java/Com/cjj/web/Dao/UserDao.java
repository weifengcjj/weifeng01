package Com.cjj.web.Dao;

import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public Set<TestundileForm> SelectGetuser() {
        Set<TestundileForm> list=new HashSet<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=ConnectionDao.getcon();
            st=con.createStatement();
            String sql="select username,password,image from userform";
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                TestundileForm tf=new TestundileForm();
                tf.setName(rs.getString("username"));
                tf.setPassword(rs.getString("password"));
                tf.setImage(rs.getString("image"));
                list.add(tf);
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            ConnectionDao.closeAll(con,st,rs);
        }
        return  list;
    }
    public void insert(RegisterForm rf){
        // TODO Auto-generated method stub
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=ConnectionDao.getcon();
            Date date=new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(formatter.format(date));

            String sql="insert into userform(username,password,image,sex,moblie,email,datatime)values(?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,rf.getName());
            ps.setString(2,rf.getPassword());
            ps.setString(3,rf.getImage());
            ps.setString(4,rf.getSex());
            ps.setString(5, rf.getMobile());
            ps.setString(6, rf.getEmail());
            ps.setString(7, formatter.format(date));
            int af=ps.executeUpdate();//executeUpdate()方法返回的是整数，成功影响的记录数， 即成功插入记录数。

            System.out.println("添加用户成功！！！！成功插入-"+af+"-条数据"+"----UseDao");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            ConnectionDao.closeAll(con,st,rs);
        }
    }
    public List<RegisterForm> getuserallson(String name)
    {
        List<RegisterForm> list=new ArrayList<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            con=ConnectionDao.getcon();
            st=con.createStatement();
            String sql="SELECT * FROM `userform` WHERE username="+name+"";
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                RegisterForm rf=new RegisterForm();
                rf.setId(rs.getInt("id"));
                rf.setName(rs.getString("username"));
                rf.setPassword(rs.getString("password"));
                rf.setImage(rs.getString("image"));
                rf.setSex(rs.getString("sex"));
                rf.setMobile(rs.getString("moblie"));
                rf.setEmail(rs.getString("email"));
                rf.setDatetime(rs.getString("datatime"));
                list.add(rf);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        finally {
            ConnectionDao.closeAll(con,st,rs);
        }
        return list;
    }
}

