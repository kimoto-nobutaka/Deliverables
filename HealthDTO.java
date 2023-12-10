package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class HealthDTO implements Serializable{
	private ArrayList<HealthBean>list;
	
	public HealthDTO(){
		list = new ArrayList<HealthBean>();
	}
	public void add(HealthBean hb) {
		list.add(hb);
	}
	public HealthBean get(int i) {
		return list.get(i);
	}
	public int size() {
		return list.size();
	}
}
