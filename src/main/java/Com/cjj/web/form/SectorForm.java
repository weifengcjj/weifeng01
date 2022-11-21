package Com.cjj.web.form;

import Com.cjj.web.Sevlet.ActionErrors;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class SectorForm extends ActionForm{
    private int id;
    private String SectorName;
    private int ClickingRate;
    private int TopicCount;

    public SectorForm(int id, String sectorName, int clickingRate, int topicCount) {
        this.id = id;
        SectorName = sectorName;
        ClickingRate = clickingRate;
        TopicCount = topicCount;
    }

    public SectorForm()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSectorName() {
        return SectorName;
    }

    public void setSectorName(String sertorName) {
        SectorName = sertorName;
    }

    public int getClickingRate() {
        return ClickingRate;
    }

    public void setClickingRate(int clickingRate) {
        ClickingRate = clickingRate;
    }

    public int getTopicCount() {
        return TopicCount;
    }

    public void setTopicCount(int topicCount) {
        TopicCount = topicCount;
    }
    @Override
    public ActionErrors validateForm() {
        return null;
    }
}
