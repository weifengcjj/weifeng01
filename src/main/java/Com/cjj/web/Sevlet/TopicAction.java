package Com.cjj.web.Sevlet;

import Com.cjj.web.Service.Servce;
import Com.cjj.web.form.ActionForm;
import Com.cjj.web.form.TopicForm;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
@Component("TopicAction")
public class TopicAction extends Action{
    @Override
    public void execute(ActionForm form, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) {
        //TopicForm form1=(TopicForm) form;

        Servce servce=new Servce();
        List<Map<Integer,TopicForm>> list=servce.gettopic111();
        List<TopicForm> list1=new ArrayList<>();
        try{
        int id =Integer.parseInt(request.getParameter("id")) ;
        for (Map<Integer, TopicForm> map : list) {
            for (Integer s : map.keySet()) {
                if (s.equals(id))
                {
                    list1.add(map.get(s));
                    request.getSession().setAttribute("list",list1);
                    request.getRequestDispatcher(mapping.getTarge()).forward(request,response);
                }
            }
        }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
