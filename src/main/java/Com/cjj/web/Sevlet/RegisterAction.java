package Com.cjj.web.Sevlet;
import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.RegisterForm;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Component(value = "register.do")
public class RegisterAction extends Action{

    @Override
    public void execute(ActionForm form, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
//        RegisterForm rf=(RegisterForm) form;
//        String user=rf.getName();
//        System.out.println("use="+user);
        RegisterForm rf= Servce.getRegisterForm();
        System.out.println("注册的账户："+rf.getName()+"----注册的密码"+rf.getPassword());
        System.out.println("这里是RegisterAction的getTarge："+mapping.getTarge());
        Servce servce=new Servce();
        servce.insert(rf);
        try {
            request.getRequestDispatcher(mapping.getTarge()).forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
