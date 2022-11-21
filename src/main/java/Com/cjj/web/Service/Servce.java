package Com.cjj.web.Service;

import Com.cjj.web.Dao.SectorDao;
import Com.cjj.web.Dao.TopicDao;
import Com.cjj.web.Dao.UserDao;
import Com.cjj.web.Idao.IdaoInterface;
import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.SectorForm;
import Com.cjj.web.form.TestundileForm;
import Com.cjj.web.form.TopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Service("Servce")
public class Servce implements IdaoInterface {
    @Autowired

    UserDao userDao=new UserDao();
    SectorDao sectorDao=new SectorDao();
    TopicDao topicDao=new TopicDao();

    Set<TestundileForm> list=userDao.SelectGetuser();

   List<SectorForm> list2=sectorDao.getallsector();

    List<TopicForm> list3=topicDao.gettopic();

    List<Map<Integer,TopicForm>> list4=topicDao.getgezi();

    private  static TestundileForm testundileForm=new TestundileForm();

    private static RegisterForm registerForm=new RegisterForm();

    private  static int a;

    @Override
    public Set<TestundileForm> getuser() {
        return list;
    }

    @Override
    public List<SectorForm> getsector() {
        return list2;
    }

    @Override
    public List<TopicForm> gettopic(){return list3;}

    public List<Map<Integer,TopicForm>> gettopic111(){return  list4;}



    @Override
    public void insert(RegisterForm registerForm) {
        userDao.insert(registerForm);
    }

    @Override
    public TestundileForm login(TestundileForm tf) {
        String username = tf.getName();
        String password =tf.getPassword();
        if (username == null || password == null) {
            return null;
        }
        for (TestundileForm tf1 : list) {
            if (tf1.getName().equals(username)&&tf1.getPassword().equals(password)) {
                return tf1;
            }
        }
        return null;
    }

    @Override
    public List<RegisterForm> getuserallson(String name) {
        List<RegisterForm> list=userDao.getuserallson(name);
        return list;
    }

    public static void Comeuser(TestundileForm tf)
    {
         testundileForm=tf;
    }
    public static TestundileForm comeusercome()
    {
        return testundileForm;
    }

    public static void Comeregiter(RegisterForm ra){registerForm=ra;}
    public static RegisterForm getRegisterForm(){return registerForm;}

    public static void setnum(int x){a=x;}
    public static int getnum(){return a;};
}
