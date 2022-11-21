package Com.cjj.web.Model;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class EmailModel {
    private String Email;
    private int emailnum;

    public EmailModel() {
    }

    public EmailModel(String email) {
        Email = email;
    }

    public int getEmailnum() {
        return emailnum;
    }

    public void setEmailnum(int emailnum) {
        this.emailnum = emailnum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
