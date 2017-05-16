package com.example.ssudoku;

import com.example.ssudoku.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class RuleActivity extends ActionBarActivity {
	private Button btn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SysApplication.getInstance().addActivity(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rule);
		
		String gifPath = "file:///android_asset/beargif.gif";
        WebView wvGif = (WebView) findViewById(R.id.a);
        wvGif.loadUrl(gifPath);
        
		btn1=(Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){
	        	
			public void onClick(View v) {
				Intent intent = new Intent(RuleActivity.this, MainActivity.class);
	        	startActivity(intent);
	        }
	        	
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rule, menu);
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
