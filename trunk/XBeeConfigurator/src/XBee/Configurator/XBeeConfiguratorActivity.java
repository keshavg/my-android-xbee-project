package XBee.Configurator;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class XBeeConfiguratorActivity extends Activity {

	final Context c = this;
	ConnectionClass cc;
	Auxiliar aux;
	String language;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		cc=new ConnectionClass(c);
		
		aux=new Auxiliar();
		
		this.getLanguage();
		
		setContentView(R.layout.main);
		this.inicialization();
	}

	
	private void getLanguage(){
		
		System.out.println(Locale.getDefault().getDisplayLanguage());
		Locale l=new Locale("en_US");
		Locale.setDefault(l);
		
		Configuration config2 = new Configuration();
	    config2.locale = l;
	    
	    System.out.println(Locale.getDefault().getDisplayLanguage());
	    
	    c.getResources().updateConfiguration(config2, null);
	    
		
	}
	
	private void inicialization() {

		/*
		 * XBEE DETECTED DEVICES TABLE
		 */
		final TableLayout tlXBeeDevices = (TableLayout) findViewById(R.id.tlXBeeDevices);

		/*
		 * BUTTONS INICIALIZATION
		 */
		final Button bOK = (Button) findViewById(R.id.bOKPan);
		Button bDetect = (Button) findViewById(R.id.bDetectDevices);

		/*
		 * TEXT BOX'S INICIALIZATION
		 */

		final EditText etPan = (EditText) findViewById(R.id.editPan);

		/*
		 * TEXT BOX'S LISTENERS
		 */

		etPan.addTextChangedListener(new TextWatcher() {

			// METHOD THAT CHECKS IF THE TEXT IS CHANGED
			public void afterTextChanged(Editable s) {
				// IF TEXT SIZE IS HIGHER THAN 5, APPLICATIOAN LAUNCHES AN ERROR
				if (s.length() > 5) {
					new AlertMessage(c)
							.newMessage(MessageType.TEXT_OUT_OF_BOUNDS);
					etPan.setText("");
					// IF TEXT SIZE IS HIGHER THAN 0, OK BUTTON TURNS ACTIVE
				} else if (s.length() > 0)
					bOK.setEnabled(true);
				// IF TEXT SIZE IS LOWER OR EQUAL TO 0, OK BUTTON TURNS
				// DEACTIVATED
				else if (s.length() <= 0)
					bOK.setEnabled(false);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

		});

		/*
		 * BUTTONS LISTENERS
		 */
		
		//OK BUTTON
		bOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (inBoundsPanID(etPan.getText().toString())) {
					changeXbeePanID(Integer
							.parseInt(etPan.getText().toString()));

				} else
					new AlertMessage(c)
							.newMessage(MessageType.PAN_ID_OUT_OF_BOUNDS);

				etPan.setText("");

			}

			private boolean inBoundsPanID(String string) {
				if (Integer.parseInt(string) > 5000
						|| Integer.parseInt(string) <= 0)
					return false;
				return true;
			}

			private void changeXbeePanID(int parseInt) {

			}
		});

		//DETECT BUTTON
		bDetect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				aux.clearList();
				tlXBeeDevices.removeAllViews();
				
				cc.searchXBeeDevices();
				aux.setList(cc.getList());
				
				if (aux.getListSize() < 0)
					new AlertMessage(c)
							.newMessage(MessageType.DEVICES_NOT_DETECTED);
				else {
					for (int i = 0; i != aux.getListSize(); i++) {
						TableRow r = new TableRow(c);
						final TextView addr=new TextView(c);
						TextView type=new TextView(c);
						TextView ss=new TextView(c);
						
						addr.setText(aux.getAddress(i));
						addr.setId(i);
						addr.setClickable(true);
						addr.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								Intent i = new Intent(c, XbeeDetailsActivity.class);
								
								i.putExtra("position", addr.getId());
								
								//i.putExtra("list", cc.getList());
								
								Bundle b= new Bundle();
								
								b.putSerializable("auxiliar", aux);
								
								i.putExtras(b);
								c.startActivity(i);
							}


						});
						
						type.setText(aux.getType(i));
						
						ss.setText(aux.getSignalStrength(i));
						
						r.addView(addr);
						r.addView(type);
						r.addView(ss);
						tlXBeeDevices.addView(r);

					}
				}
			}

		});
	}
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {  
	    //menu.add(1, new Languages().getPreferences(""));  
	    //return super.onCreateOptionsMenu(menu);
		 MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.menu, menu);
		    return true;
	  }
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.Preferences:
	            preferencesMenu();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void preferencesMenu(){
		Intent i = new Intent(c, PreferencesActivity.class);
		c.startActivity(i);
	}
}