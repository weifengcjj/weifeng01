package Com.cjj.web.form;

import Com.cjj.web.Sevlet.ActionErrors;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class TopicForm extends ActionForm{
    private int id;
    private String title;
    private String creattime;
    private String content;
    private int authuser;
    private int type;
    private int hits;

    public TopicForm() {
    }

    public TopicForm(int id, String title, String creattime, String content, int authuser, int type, int hits) {
        this.id = id;
        this.title = title;
        this.creattime = creattime;
        this.content = content;
        this.authuser = authuser;
        this.type = type;
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthuser() {
        return authuser;
    }

    public void setAuthuser(int authuser) {
        this.authuser = authuser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    @Override
    public ActionErrors validateForm() {
        return null;
    }
}
