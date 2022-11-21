package Com.cjj.web.form;

import Com.cjj.web.Sevlet.ActionErrors;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class RegisterForm extends  ActionForm{
    private int id;
    private String name;
    private String password;
    private String image;
    private String sex;
    private String mobile;
    private String email;
    private String datetime;

    public RegisterForm(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public RegisterForm(String name, String password, String image, String sex, String mobile, String email, String datetime) {
        this.name = name;
        this.password = password;
        this.image = image;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
        this.datetime = datetime;
    }

    public RegisterForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public ActionErrors validateForm(){
        ActionErrors errors=new ActionErrors();
        if("".equals(this.name)||this.name==null){

            errors.addMessage("userError", "name is null");
        }
        if("".equals(this.password)||this.password==null){

            errors.addMessage("pwError", "pw is null");
        }
        return errors;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
