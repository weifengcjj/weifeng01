package Com.cjj.web.Sevlet;

import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.RegisterForm;
import Com.cjj.web.form.TestundileForm;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
        System.out.println("URL："+requestURI+"----ActionFormServlet");

        String target=requestURI.substring(requestURI.lastIndexOf("/")+1);
        System.out.println("target:"+target+"----ActionFormServlet");

        ActionMapping mapping=map.get(target);
        String path1=mapping.getPath();
        String actionFormName=mapping.getActionFormName();
        String actionName=mapping.getActionName();
        String valideFlag=mapping.getValidateFlag();

        System.out.println("配置文件里的："+path1+"----"+actionFormName+"----ActionFormServlet");
        System.out.println("别跟我说你最后会跳转到："+mapping.getTarge()+"----ActionFormServlet");
        Class<? extends Object> c=null;
        Object o=null;
        ActionForm form=null;
        Servce servce=new Servce();
        String username=null;
        String password=null;
        System.out.println("actionFormName为空吗:"+actionFormName+"----ActionFormServlet");
        if(!(actionFormName.equals("null")))
        {
            try {
                c=Class.forName(actionFormName);
                o=c.newInstance();
                form=(ActionForm)o;

                Enumeration<String> parameterNames = req.getParameterNames();
                System.out.println("parameterNames=="+parameterNames+"----ActionFormServlet");
//
            while(parameterNames.hasMoreElements())
            {
                String path=parameterNames.nextElement();

                username=req.getParameter("name");
                password=req.getParameter("password");

                System.out.println("输入账户："+username+"----输入密码"+password+"----ActionFormServlet");

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
                    System.out.println("last=="+last+"----ActionFormServlet");
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
            if(valideFlag.equals("1"))
            {
                ActionErrors errors=form.validateForm();
                Map map=errors.getMap();
                int errorsNum=map.size();
                if(errorsNum>2){
                    req.setAttribute("errors", errors);
                    req.setAttribute("form", form);
                    System.out.println("这里判断好了："+mapping.getValidateTarget()+"----ActionFormServlet");
                    req.getRequestDispatcher(mapping.getValidateTarget()).forward(req,resp);
                    return;
                }
            }
        }
        String valdspringflag=mapping.getValidateSpring();
        System.out.println("valddatespring:"+valdspringflag+"----ActionFormServlet");

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

//            if(path1.equals("login.do"))
//            {
//                servce.Comeuser(new TestundileForm(username,password));
//                TestundileForm tf=servce.comeusercome();
//                System.out.println("login跳转传过去的数据---"+tf.getName()+" "+tf.getPassword()+"----ActionFormServlet");
//            }
            if(path1.equals("register.do")) {
                String moblie = null;
                String email = null;
                String image = null;
                String sex = null;
                String inputCode=null;
                if (ServletFileUpload.isMultipartContent(req))//判断数据是否为多段数据(只有多段数据，才是文件上传)
                {
                    FileItemFactory fileItemFactory = new DiskFileItemFactory();
                    //创建用于解析上传数据的工具类ServletFileUpload类
                    ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                    try {
                        List<FileItem> list = servletFileUpload.parseRequest(req);//解析上传的数据得到每一个表单项FileItem
                        //循环判断，每一个表单是普通类型还是上传文件
                        for (FileItem fi : list) {
                            if (fi.isFormField()) {
                                System.out.println("普通类型---" + fi.getFieldName() + "---" + "参数值" + fi.getString("UTF-8"));
                                if (fi.getFieldName().equals("username")) {
                                    username = fi.getString("UTF-8");
                                }
                                if (fi.getFieldName().equals("password")) {
                                    password = fi.getString("UTF-8");
                                }
                                if (fi.getFieldName().equals("moblie")) {
                                    moblie = fi.getString("UTF-8");
                                }
                                if (fi.getFieldName().equals("email")) {
                                    email = fi.getString("UTF-8");
                                }
                                if (fi.getFieldName().equals("sex")) {
                                    sex = fi.getString("UTF-8");
                                }
                                if(fi.getFieldName().equals("code"))
                                {
                                    inputCode=fi.getString("UTF-8");
                                }
                            } else  {
                                image=fi.getName();
                                System.out.println("文件上传---" + fi.getFieldName() + "---" + fi.getName());
                                fi.write(new File("F:\\java\\ActionForm10_25\\web\\image\\"+fi.getName()));
                            }
                        }
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("register跳转传过去的数据---username:" + username + "---password:" + password + "" + "--image:" + image + "---sex" + sex + "---moblie" + moblie + "---email" + email + "----ActionFormServlet");
                    Servce.Comeregiter(new RegisterForm(username, password, "image\\" + image, sex, moblie, email, ""));
                    req.getSession().setAttribute("inputCode",inputCode);
                    System.out.println("页面的code:"+inputCode);
                }
            }

            System.out.println("此时的getPath="+mapping.getPath()+"----ActionFormServlet");

            System.out.println("此时的getactionname="+mapping.getActionName()+"----ActionFormServlet");

            ServletContext context=this.getServletContext();
            WebApplicationContext applicationContext= WebApplicationContextUtils.getWebApplicationContext(context);
            Object o2=applicationContext.getBean(mapping.getActionName());
            Action action=(Action)o2;
            action.execute(form, mapping, req,resp);
        }
//
    }
}
