package Com.cjj.web.Idao;

import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;

import java.util.Set;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public interface IdaoInterface {
    public Set<TestundileForm> getuser();
    void insert(RegisterForm rf);
    TestundileForm login(TestundileForm tf);
}
