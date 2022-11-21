package Com.cjj.web.Dao;

import Com.cjj.web.form.TopicForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class TopicDao {
    public List<TopicForm> gettopic()
    {
        List<TopicForm> list=new ArrayList<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            con=ConnectionDao.getcon();
            st=con.createStatement();
            String sql="SELECT * FROM topic";
            rs=st.executeQuery(sql);
            while(rs.next())
            {
                TopicForm tf=new TopicForm();
                tf.setId(rs.getInt("cid"));
                tf.setTitle(rs.getString("title"));
                tf.setCreattime(rs.getString("creattime"));
                tf.setContent(rs.getString("content"));
                tf.setAuthuser(rs.getInt("authuser"));
                tf.setType(rs.getInt("type"));
                tf.setHits(rs.getInt("hits"));
                list.add(tf);
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

    public List<Map<Integer,TopicForm>> getgezi()
    {
        List<Map<Integer,TopicForm>> list=new ArrayList<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try{
            con=ConnectionDao.getcon();
            st=con.createStatement();
            String sql="SELECT\n" +
                    "\t        a.cid,\n" +
                    "\t\t\t\t\ta.title,\n" +
                    "\t        a.creattime,\n" +
                    "\t        a.content,\n" +
                    "\t        a.authuser,\n" +
                    "\t        a.type,\n" +
                    "\t        a.hits,\n" +
                    "\t        b.SectorName as title,\n" +
                    "\t\t\t\t\tb.SectorID as cid\n" +
                    "        FROM\n" +
                    "\t        topic AS a\n" +
                    "\t        LEFT JOIN sector as b on a.type = b.SectorID";
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                TopicForm tf=new TopicForm();
                tf.setId(rs.getInt("cid"));
                tf.setTitle(rs.getString("title"));
                tf.setCreattime(rs.getString("creattime"));
                tf.setContent(rs.getString("content"));
                tf.setAuthuser(rs.getInt("authuser"));
                tf.setType(rs.getInt("type"));
                tf.setHits(rs.getInt("hits"));
                Map<Integer, TopicForm> map=new HashMap<>();
                int anInt = rs.getInt(7);
                //String a=String.valueOf(anInt);
                map.put(anInt,tf);
                list.add(map);
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
