package com.example.generatorlutrije;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	  private RadioGroup radioGroupId;
	  private Button button;
	  

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addButtonListener();
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	public void addButtonListener() {
		
		radioGroupId = (RadioGroup) findViewById(R.id.radioLotoGrupa);

		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
								
				final TextView text = (TextView) findViewById(R.id.textView1);
				text.setTextSize(36);

			    //Radnja ovisna o tome koji je radio button oznaèen
				switch (radioGroupId.getCheckedRadioButtonId()) {
			    
			    case R.id.radio_6od45:
			    	
			    	//Isprazni textview1
			    	text.setText(null);
			    	//Definiranje liste koja æe sadržavati listu brojeva
			        ArrayList<Integer> sestOd45 = new ArrayList<Integer>();
			        for(int i = 1; i < 45; i++)
			        {
			        	sestOd45.add(i+1);
			        }
			    
			        //Želimo da brojevi budu nasumièno pomiješani
			        Collections.shuffle(sestOd45);
			        
			      //Izvrtit æe šest brojeva iz kolekcije i prikvaèiti ih na textView1
			        for(int j =0; j < 6; j++)
			        {
			        	text.append(String.valueOf(sestOd45.get(j) + " "));
			        }
		 				    	
			    	break;
			    	
			    case R.id.radio_radio7do39:
			    	
			    	//Isprazni textview1
			    	text.setText(null);
			    	//Definiranje liste koja æe sadržavati listu brojeva
			        ArrayList<Integer> sedamOd39 = new ArrayList<Integer>();
			        for(int i = 1; i < 39; i++)
			        {
			        	sedamOd39.add(i+1);
			        }
			    
			      //Želimo da brojevi budu nasumièno pomiješani
			        Collections.shuffle(sedamOd39);
			        
			        //Izvrtit æe šest brojeva iz kolekcije i prikvaèiti ih na textView1
			        for(int j =0; j < 7; j++)
			        {
			        	text.append(String.valueOf(sedamOd39.get(j) + " "));
			        }
			    	
			    	break;
			    }
			}
		});
	  }
	}
