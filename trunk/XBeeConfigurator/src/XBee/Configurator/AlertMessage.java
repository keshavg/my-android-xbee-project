package XBee.Configurator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertMessage extends AlertDialog{

	Context c;
	String language;
	public AlertMessage(Context c) {
		super(c);
		this.c=c;
	}
	
	public void newMessage(MessageType msg){
		
		if(msg.equals(MessageType.COORDINATOR_NOT_DETECTED)){
			coordenatorNotDetected();
		}else if(msg.equals(MessageType.PAN_ID_OUT_OF_BOUNDS)){
			panIdOutOfBounds();
		}else if(msg.equals(MessageType.TEXT_OUT_OF_BOUNDS)){
			textOutOfBounds();
		}else if(msg.equals(MessageType.DEVICES_NOT_DETECTED)){
			devicesNotDetected();
		}
		
		
	}

	private void devicesNotDetected() {
		this.setMessage(c.getString(R.string.devicesNotDetected));
		this.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		this.show();
		
	}

	private void coordenatorNotDetected() {
		this.setMessage(c.getString(R.string.coordNotFound));
		this.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		this.show();
	}
	
	private void panIdOutOfBounds(){
		this.setMessage(c.getString(R.string.panIdOutOfBounds));
		this.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		this.show();
	}

	private void textOutOfBounds(){
		this.setMessage(c.getString(R.string.textOutOfBounds));
		//this.setMessage(new Languages().getMessageAlert_ValueTooHigh(language));
		this.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		this.show();
	}
}