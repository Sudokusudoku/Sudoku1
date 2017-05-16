package com.example.ssudoku;

import java.util.Random;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class FourActivity extends Activity{

	Game4 game=new Game4();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	SysApplication.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        
        Button[] button=new Button[4];
        button[0]=(Button)findViewById(R.id.button41);
        button[1]=(Button)findViewById(R.id.button42);
        button[2]=(Button)findViewById(R.id.button43);
        button[3]=(Button)findViewById(R.id.button44);

//        DisplayMetrics metrics = new DisplayMetrics();
//		this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(
//                (int) (metrics.widthPixels * 0.5f + 0.5f),
//                (int) (metrics.widthPixels  * 0.5f + 0.5f));
//		button[0].setLayoutParams(lps);
		
        OnClickListener listener=new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btnButton=(Button)v;
				String string=(String) btnButton.getText();
				game.getNumber(string);
			}
		};
		for (int i=0; i<4; i++){			
			button[i].setOnClickListener(listener);
		}

		Button finishB = (Button)findViewById(R.id.buttonFinish);
		OnClickListener Button_Finish = new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(game.isFinished()){
			 			Log.d(" ", " finish");
			 			if(game.isRight()){
			 				showWinDialog();
			 			}
			 			else Log.d(" ", " not win");
			 	 	}
			 	else 
			 		Log.d("not finish", "yes");
			}
		};
		finishB.setOnClickListener(Button_Finish);
		
		Button hintB = (Button)findViewById(R.id.buttonHint);
		OnClickListener Button_Hint = new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
		};
		hintB.setOnClickListener(Button_Hint);
		
		Button backB = (Button)findViewById(R.id.buttonBack);
		OnClickListener Button_Back = new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
		};
		backB.setOnClickListener(Button_Back);
		
		Button ONB = (Button)findViewById(R.id.buttonON);
		OnClickListener Button_on = new OnClickListener(){
			@Override
			public void onClick(View v) {
				
			}
		};
		ONB.setOnClickListener(Button_on);
		
		Button exitB = (Button)findViewById(R.id.buttonExit);
		OnClickListener Button_Exit = new OnClickListener(){
			@Override
			public void onClick(View v) {
        		Intent intent = new Intent(FourActivity.this, ModeActivity.class);
        		startActivity(intent);
			}
		};
		exitB.setOnClickListener(Button_Exit);
}	
    public void showWinDialog(){

        final AlertDialog.Builder winlDialog = 
            new AlertDialog.Builder(FourActivity.this);

        winlDialog.setTitle("Game Over");
        winlDialog.setMessage("Congratulations!You Win!");
        winlDialog.setPositiveButton("OK", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
        winlDialog.setNegativeButton("back", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
        		Intent intent = new Intent(FourActivity.this, ModeActivity.class);
        		startActivity(intent);
            }
        });
        winlDialog.show();
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

