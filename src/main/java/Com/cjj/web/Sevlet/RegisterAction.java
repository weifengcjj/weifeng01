package Com.cjj.web.Sevlet;
import Com.cjj.web.Model.EmailModel;
import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.RegisterForm;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Component("RegisterAction")
public class RegisterAction extends Action{

    @Override
    public void execute(ActionForm form, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {

        //RegisterForm rf0=(RegisterForm) form;
//        System.out.println("rf0的账户:"+ rf0.getName()+" 密码："+rf0.getPassword()+"手机号:"+rf0.getMobile()+"邮箱:"+rf0.getEmail()+"图像："+rf0.getImage()+"性别："+rf0.getSex());
        Servce servce=new Servce();
        RegisterForm rf= Servce.getRegisterForm();
        System.out.println("rf0的账户:"+ rf.getName()+" 密码："+rf.getPassword()+"手机号:"+rf.getMobile()+"邮箱:"+rf.getEmail()+"图像："+rf.getImage()+"性别："+rf.getSex());
        System.out.println("这里是RegisterAction的getTarge："+mapping.getTarge()+"----RegisterAction");
        String sessionCode = (String) request.getSession().getAttribute("code1");
        System.out.println("sessionCode:"+sessionCode);
        try {
        if(sessionCode!=null)
        {
            String inputCode = (String) request.getSession().getAttribute("inputCode");
            System.out.println("页面提交的验证码:" + inputCode);
            if(sessionCode.toLowerCase().equals(inputCode.toLowerCase()))
            {
                servce.insert(rf);
                request.getRequestDispatcher(mapping.getTarge()).forward(request, response);
            }
            else {
                //  验证失败
                request.getRequestDispatcher("fail.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("fail.jsp").forward(request, response);
        }
            request.removeAttribute("code");
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
