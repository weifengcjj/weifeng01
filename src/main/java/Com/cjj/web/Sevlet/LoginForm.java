package Com.cjj.web.Sevlet;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class LoginForm {
    private String id;

    public LoginForm(String id) {
        this.id = id;
    }

    public LoginForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
