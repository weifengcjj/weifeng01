package Com.cjj.web.Sevlet;

public class ActionMapping {
    private String path;
    private String actionFormName;
    private String validateFlag;
    private String validateTarget;
    private String validateSpring;
    private String actionName;
    private String targe;

    public String getValidateSpring() {
        return validateSpring;
    }
    public void setValidateSpring(String validateSpring) {
        this.validateSpring = validateSpring;
    }
    public String getValidateFlag() {
        return validateFlag;
    }

    public ActionMapping(String path, String actionFormName) {

    }

    public ActionMapping(String path, String actionFormName,
                         String validateFlag, String validateTarget, String validateSpring,
                         String actionName, String targe) {
        super();
        this.path = path;
        this.actionFormName = actionFormName;
        this.validateFlag = validateFlag;
        this.validateTarget = validateTarget;
        this.validateSpring = validateSpring;
        this.actionName = actionName;
        this.targe = targe;
    }
    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag;
    }
    public ActionMapping(String path, String actionFormName,
                         String validateFlag, String validateTarget, String actionName,
                         String targe) {
        super();
        this.path = path;
        this.actionFormName = actionFormName;
        this.validateFlag = validateFlag;
        this.validateTarget = validateTarget;
        this.actionName = actionName;
        this.targe = targe;
    }
    public String getValidateTarget() {
        return validateTarget;
    }
    public void setValidateTarget(String validateTarget) {
        this.validateTarget = validateTarget;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getActionFormName() {
        return actionFormName;
    }
    public void setActionFormName(String actionFormName) {
        this.actionFormName = actionFormName;
    }
    public String getActionName() {
        return actionName;
    }
    public ActionMapping(String path, String actionFormName, String actionName,
                         String targe) {
        super();
        this.path = path;
        this.actionFormName = actionFormName;
        this.actionName = actionName;
        this.targe = targe;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public String getTarge() {
        return targe;
    }
    public void setTarge(String targe) {
        this.targe = targe;
    }

}
