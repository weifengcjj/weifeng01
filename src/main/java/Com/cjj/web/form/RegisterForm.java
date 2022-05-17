package Com.cjj.web.form;

import Com.cjj.web.Sevlet.ActionErrors;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class RegisterForm extends  ActionForm{
    private String name;
    private String password;

    public RegisterForm(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public RegisterForm() {
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
}
