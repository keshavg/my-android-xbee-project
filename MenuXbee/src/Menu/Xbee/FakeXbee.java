package Menu.Xbee;

import java.io.Serializable;

public class FakeXbee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6912906708507450701L;
	private String address;
	private int type;
	private String ss;

	public FakeXbee(String a, int t, String s){
		this.address=a;
		this.type=t;
		this.ss=s;
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

}
