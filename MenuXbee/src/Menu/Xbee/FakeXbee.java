package Menu.Xbee;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ListIterator;

public class FakeXbee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6912906708507450701L;
	private String address;
	private int type;
	private String ss;
	private LinkedList<String> myActuators=new LinkedList<String>();

	public FakeXbee(String a, int t, String s){
		this.address=a;
		this.type=t;
		this.ss=s;
	}
	
	public void setActuator(String addr){
		myActuators.add(addr);
	}
	
	public String getAdress() {
		return this.address;
	}

	public String getType() {

		if (this.type == 0)
			return "Router";
		else if (this.type == 1)
			return "Sensor";
		else
			return "Actuator";

	}

	public String getSignalStrength() {
		return this.ss;
	}
	
	
	public LinkedList<String> getActuators(){
		return myActuators;
	}
	
	public void removeActuator(String addr){
		ListIterator<String> it=myActuators.listIterator();
		while(it.hasNext()){
			if(it.next().equals(addr))
				myActuators.remove(addr);
		}
	}

}
