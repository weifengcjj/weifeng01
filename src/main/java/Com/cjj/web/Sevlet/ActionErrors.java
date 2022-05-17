package Com.cjj.web.Sevlet;

import java.util.HashMap;
import java.util.Map;

public class ActionErrors {
	private Map map=new HashMap();
	public void addMessage(String key,String value){
		map.put(key, value);
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}

}
