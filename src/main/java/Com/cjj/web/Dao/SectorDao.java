package Com.cjj.web.Dao;

import Com.cjj.web.form.SectorForm;
import Com.cjj.web.form.TestundileForm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SectorDao {
    public List<SectorForm> getallsector()
    {
        List<SectorForm> list=new ArrayList<>();
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=ConnectionDao.getcon();
            st=con.createStatement();
            String sql="SELECT SectorID,SectorName,ClickingRate,TopicCount FROM `sector`";
            rs=st.executeQuery(sql);
            while (rs.next())
            {
                SectorForm sf=new SectorForm();
                sf.setId(rs.getInt("SectorID"));
                sf.setSectorName(rs.getString("SectorName"));
                sf.setClickingRate(rs.getInt("ClickingRate"));
                sf.setTopicCount(rs.getInt("TopicCount"));
                list.add(sf);
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
