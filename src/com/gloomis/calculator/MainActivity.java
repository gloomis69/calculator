package com.gloomis.calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private double firstnum;
	private int operator;
	private boolean scndNum;
	private boolean dec;
	private int decpower;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			final int blue = getResources().getColor(R.color.lightblue);
			final int gray = getResources().getColor(R.color.greyvl);
			//LOAD CONTROLS INTO VARIABLES SO THAT YOU CAN MANIPULATE THEM
			//controls that were made at design time are assigned to a variable using findViewById(R.id.________)
				LinearLayout mn = (LinearLayout)findViewById(R.id.LLMain);
				
				TableLayout tblMain = (TableLayout)findViewById(R.id.tblMain);
				tblMain.setBackgroundColor(gray);
				//get 1st row
				TableRow rw1 = (TableRow)findViewById(R.id.tableRow1);
				rw1.setBackgroundColor(blue);
				rw1.setPadding(0, 5, 5, 5);
				scndNum = false;
				dec = false;
				//get the textview for the readout
				TextView readout = (TextView)findViewById(R.id.textView1);
				
				readout.setText(0+"");
				readout.setPadding(0, 5, 5, 5);
				for (int x = 4; x>0; x--){
					makeButtons(x);
				}
				
	}
	private void makeButtons(int row){
		//get the tablerow
		TableRow rw =(TableRow)findViewById(R.id.tableRow5);
		switch(row){
		case 4:
			rw =(TableRow)findViewById(R.id.tableRow2);
			break;
		case 3:
			rw =(TableRow)findViewById(R.id.tableRow3);
			break;
		case 2:
			 rw =(TableRow)findViewById(R.id.tableRow4);
			break;
		}
		
		
		//PROGRAMATICALLY add 4 buttons to the row
		
		for (int x = 0; x < 4; x++){
			Button b = new Button(this);
			switch(row){
			case 4:
				if(x<3){
					b.setText(x + 7 +"");
					b.setId(x+7);
				}else{
					b.setText("/");
					b.setId(43);
				}				
				break;
			case 3:
				if(x<3){
					b.setText(x + 4 +"");
					b.setId(x+4);
				}else{
					b.setText("X");
					b.setId(33);
				}	
				break;				
			case 2:
				if(x<3){
					b.setText(x + 1 +"");
					b.setId(x+1);
				}else{
					b.setText("+");
					b.setId(23);
				}	
				break;
			case 1:
				switch(x){
				case 0:
					b.setText("C");
					b.setId(10);
					break;
				case 1:
					b.setText("0");
					b.setId(0);
					break;
				case 2:
					b.setText(".");
					b.setId(12);
					break;
				case 3:
					b.setText("-");
					b.setId(13);
					break;
				}
				break;
			}
			buttonEvents(b);			
			rw.addView(b);
		}
		Button eql = (Button)findViewById(R.id.btnEquals);
		eqlEvent(eql);
	}
	private void eqlEvent(Button btn){
		btn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				
				TextView readout = (TextView)findViewById(R.id.textView1);
				double x = Double.parseDouble(readout.getText().toString());
				switch(operator){
				case 13:
					readout.setText(firstnum - x +"");
					break;
				case 23:
					readout.setText(firstnum + x +"");			
					break;
				case 33:
					readout.setText(firstnum * x +"");
					break;
				case 43:
					readout.setText(firstnum / x +"");
					break;
				}
				
				DecimalFormat decimalFormat=new DecimalFormat("#.#");
				x = Double.parseDouble(readout.getText().toString());
				
				int y = (int) Math.rint(x);
				if (x == y){
					readout.setText(decimalFormat.format(x)+"");
				}
				dec = false;
				decpower = 0;
			}
		});
	}
	private void buttonEvents(Button btn){		
		btn.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Button b = (Button)v;
				int idnum = b.getId();
				TextView readout = (TextView)findViewById(R.id.textView1);
				Double x = Double.parseDouble(readout.getText().toString());
				
				if(idnum<10){
					if (scndNum==true){
						readout.setText(idnum+"");
						scndNum = false;
						dec = false;
						decpower = 0;
					}else{
						if (dec==true){
							decpower -= 1;
							double decnum;
							decnum = idnum*Math.pow(10, decpower);							
							readout.setText(x+decnum+"");
						}else{
							readout.setText(x*10+idnum+"");
						}						
					}
					
				}else{
					
					if (idnum==10){
						readout.setText("0");
						firstnum = 0;
						operator = 0;
						scndNum = false;
						dec = false;
					}else if(idnum==12){
						dec = true;
						readout.setText(readout.getText()+".");
					}else{
						firstnum = x;
						operator = idnum;					
						scndNum = true;
					}
					
				}
				DecimalFormat decimalFormat=new DecimalFormat("#.#");
				x = Double.parseDouble(readout.getText().toString());
				
				int y = (int) Math.rint(x);
				if (x == y){
					readout.setText(decimalFormat.format(x)+"");
				}
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
