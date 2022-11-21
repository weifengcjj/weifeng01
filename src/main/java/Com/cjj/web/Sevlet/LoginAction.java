package Com.cjj.web.Sevlet;

import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.TestundileForm;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component("LoginAction")
public class LoginAction extends Action {

    @Override
    public void execute(ActionForm form, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response){
//		TestundileForm tf=(TestundileForm) form;
//		String user=tf.getName();
//		User u=new User();
//		ad.addObject(u);
        TestundileForm tf001=(TestundileForm) form;
        System.out.println("path0001:"+tf001.getName()+"--value---"+tf001.getPassword()+"----LoginAction");
//
//        TestundileForm tf0=Servce.comeusercome();
////
//         System.out.println("path:"+tf0.getName()+"--value---"+tf0.getPassword()+"----LoginAction");

        Servce servce=new Servce();
        TestundileForm tf=servce.login(tf001);

        try {
            if(tf!=null)
            {
                System.out.println("这里是LoginAction的getTarge："+mapping.getTarge()+"----LoginAction");
                System.out.println("此时的头像在："+tf.getImage());

                request.getSession().setAttribute("image",tf.getImage());
                request.getSession().setAttribute("userdename",tf.getName());
                request.getRequestDispatcher(mapping.getTarge()).forward(request, response);
            }
            else{
                System.out.println("密码错误！"+"----LoginAction");

                request.getRequestDispatcher(mapping.getValidateTarget()).forward(request,response);

                ActionErrors errors=form.validateForm();
                //Map map=errors.getMap();
                request.setAttribute("errors", errors);
                request.setAttribute("form", form);
            }
        }
        catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
