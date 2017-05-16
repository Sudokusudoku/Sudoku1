package com.example.ssudoku;



import com.example.ssudoku.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class MainActivity extends ActionBarActivity {
	private Button btn1,btn2,btn3,btn4;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	SysApplication.getInstance().addActivity(this); 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
        
        btn1.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
        		startActivity(intent);
        	}
        	
        });
        
        btn2.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this, ModeActivity.class);
        		startActivity(intent);
        	}
        	
        });
        
        btn3.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v) {
        		SysApplication.getInstance().exit();
        	}
        	
        });   
        
        btn4.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v) {
        		Intent intent = new Intent(MainActivity.this, RuleActivity.class);
        		startActivity(intent);
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