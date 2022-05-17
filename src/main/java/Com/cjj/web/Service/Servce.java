package Com.cjj.web.Service;

import Com.cjj.web.Dao.UserDao;
import Com.cjj.web.Idao.IdaoInterface;
import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    Set<TestundileForm> list=userDao.SelectGetuser();
    private  static TestundileForm testundileForm=new TestundileForm();
    private static RegisterForm registerForm=new RegisterForm();

    @Override
    public Set<TestundileForm> getuser() {
        return list;
    }

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
}
