package Com.cjj.web.Idao;

import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.SectorForm;
import Com.cjj.web.form.TestundileForm;
import Com.cjj.web.form.TopicForm;

import java.util.List;
import java.util.Set;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public interface IdaoInterface {
    public Set<TestundileForm> getuser();
    public List<SectorForm> getsector();
    public List<TopicForm> gettopic();
    void insert(RegisterForm rf);
    TestundileForm login(TestundileForm tf);
    List<RegisterForm> getuserallson(String name);
}
