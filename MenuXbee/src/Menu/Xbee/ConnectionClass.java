package Menu.Xbee;

import java.util.LinkedList;
import java.util.Random;

public class ConnectionClass {

	private LinkedList<FakeXbee> xbee = new LinkedList<FakeXbee>();

	public void SeekNDestroy() {
		Random r = new Random();
		int numXbee = (r.nextInt(10) + 1);

		String address = "142AFC2D00C";
		int type = 3;
		String ss = "DEFAULT";

		for (int i = 0; i < numXbee; i++) {


			type = r.nextInt(3);

			switch (r.nextInt(5)) {
			case 0:
				ss = "Excelente";
				break;
			case 1:
				ss = "Muito Bom";
				break;
			case 2:
				ss = "Bom";
				break;
			case 3:
				ss = "M�dio";
				break;
			case 4:
				ss="Fraco";
				break;
			case 5:
				ss="Muito Fraco";
			}

			xbee.add(new FakeXbee(address+i, type, ss));

		}
	}
	
	public void clearList(){
		
		xbee.clear();
	}
	
	public int getListSize(){
		return xbee.size();
	}
	
	public String getAddress(int pos){
		return xbee.get(pos).getAdress();
	}
	
	public String getType(int pos){
		return xbee.get(pos).getType();
	}
	
	public String getSignalStrength(int pos){
		return xbee.get(pos).getSignalStrength();
	}
}