package Com.cjj.web.Sevlet;

import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Enumeration;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class ActionFormServlet extends HttpServlet {
    private Map<String,ActionMapping> map=new HashMap();
    @Override
    public void init() throws ServletException {
        System.out.println("数据库所有的用户如下");
        Servce servce=new Servce();
        Set<TestundileForm> list=servce.getuser();
        for (TestundileForm testundileForm : list) {
            System.out.println("账户："+testundileForm.getName()+"  密码："+testundileForm.getPassword());
        }

        ResourceBundle rb=ResourceBundle.getBundle("struct_config");
        Enumeration keys=rb.getKeys();
        while(keys.hasMoreElements()){
            String key=(String)keys.nextElement();
            String message=rb.getString(key);
            String mess[]=message.split("=");
            map.put(key, new ActionMapping(key,mess[0],mess[1],mess[2],mess[3],mess[4],mess[5]));
            System.out.println("path是："+key+"--"+mess[0]+"--"+mess[1]+"--"+mess[2]+"--"+mess[3]+"--"+mess[4]+"--"+mess[5]);
            //map.put(key,new ActionMapping(key,mess[0]));
            //System.out.println(key+"---"+mess[0]);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String requestURI = req.getRequestURL().toString();
        System.out.println("URL："+requestURI);

        String target=requestURI.substring(requestURI.lastIndexOf("/")+1);
        System.out.println("target:"+target);

        ActionMapping mapping=map.get(target);
        String path1=mapping.getPath();
        String actionFormName=mapping.getActionFormName();
        String actionName=mapping.getActionName();
        String valideFlag=mapping.getValidateFlag();

        System.out.println("配置文件里的："+path1+"----"+actionFormName);
        System.out.println("别跟我说你最后会跳转到："+mapping.getTarge());
        Class<? extends Object> c=null;
        Object o=null;
        ActionForm form=null;
        Servce servce=new Servce();
        String username=null;
        String password=null;
        System.out.println("actionFormName为空吗:"+actionFormName);
        if(!(actionFormName.equals("null")))
        {
            try {
                c=Class.forName(actionFormName);
                o=c.newInstance();
                form=(ActionForm)o;

                Enumeration<String> parameterNames = req.getParameterNames();
                System.out.println("parameterNames=="+parameterNames);
//
            while(parameterNames.hasMoreElements())
            {
                String path=parameterNames.nextElement();

                username=req.getParameter("username");
                password=req.getParameter("password");

                System.out.println("输入账户："+username+"----输入密码"+password);

                String methodname="set"+path.substring(0,1).toUpperCase()+path.substring(1);

                String[] val=req.getParameterValues(path);
                StringBuffer stringBuffer=new StringBuffer();
                String last="";
                for(int i=0;i<val.length;i++)
                {
                    stringBuffer.append(val[i]).append("-");
                }
                if(stringBuffer.toString().endsWith("-"))
                {
                    last=stringBuffer.substring(0,stringBuffer.length()-1).toString();
                    System.out.println("last=="+last);
                }
                Method[] methods=c.getDeclaredMethods();
                for(int i=0;i<methods.length;i++)
                {
                    Method m=methods[i];
                    if(m.getName().equals(methodname))
                    {
                        m.invoke(o,last);
                    }
                }
            }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(valideFlag);
            if(valideFlag.equals("1"))
            {
                ActionErrors errors=form.validateForm();
                Map map=errors.getMap();
                int errorsNum=map.size();
                if(errorsNum>2){
                    req.setAttribute("errors", errors);
                    req.setAttribute("form", form);
                    System.out.println("这里判断好了："+mapping.getValidateTarget());
                    req.getRequestDispatcher(mapping.getValidateTarget()).forward(req,resp);
                    return;
                }
            }
        }
        String valdspringflag=mapping.getValidateSpring();
        System.out.println("valddatespring:"+valdspringflag);

        if(valdspringflag.equals("0"))
        {
            try {
                 Class c1 = Class.forName(actionName);
                Object o1=c1.newInstance();
                Action action=(Action) o1;
                action.execute(form,mapping,req,resp);
             } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        if(valdspringflag.equals("1")){

            if(path1.equals("login.do"))
            {
                servce.Comeuser(new TestundileForm(username,password));
                TestundileForm tf=servce.comeusercome();
                System.out.println("login跳转传过去的数据---"+tf.getName()+" "+tf.getPassword());
            }
            if(path1.equals("register.do"))
            {
                servce.Comeregiter(new RegisterForm(username,password));
                RegisterForm rf=servce.getRegisterForm();
                System.out.println("register跳转传过去的数据---"+rf.getName()+" "+rf.getPassword());
            }

            System.out.println("此时的getPath="+mapping.getPath());
            ServletContext context=this.getServletContext();
            System.out.println("context: "+context);
            WebApplicationContext applicationContext= WebApplicationContextUtils.getWebApplicationContext(context);
            Object o2=applicationContext.getBean(mapping.getPath());
            Action action=(Action)o2;
            action.execute(form, mapping, req,resp);
        }
//
    }
}
