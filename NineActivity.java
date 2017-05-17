package com.example.ssudoku;


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

import android.widget.Button;



public class NineActivity extends Activity {

	Game game=new Game();
	public static int displayWidth;
	public static int displayHeight;

    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		SysApplication.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
        

        final Square9View nine=(Square9View)findViewById(R.id.drawView2);
        game=nine.game;

        
        Button[] button=new Button[9];
        button[0]=(Button)findViewById(R.id.button1);
        button[1]=(Button)findViewById(R.id.button2);
        button[2]=(Button)findViewById(R.id.button3);
        button[3]=(Button)findViewById(R.id.button4);
        button[4]=(Button)findViewById(R.id.button5);
        button[5]=(Button)findViewById(R.id.button6);
        button[6]=(Button)findViewById(R.id.button7);
        button[7]=(Button)findViewById(R.id.button8);
        button[8]=(Button)findViewById(R.id.button9);
        
        OnClickListener listener=new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btnButton=(Button)v;
				String string=(String) btnButton.getText();
				game.getNumber(string);
			}
		};
		for (int i=0; i<9; i++){
			button[i].setWidth(displayWidth/20);
			button[i].setOnClickListener(listener);
		}
		
		//finish button
		Button finishB = (Button)findViewById(R.id.buttonFinish);
		OnClickListener Button_Finish = new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(game.isFinished()){
		 			Log.d(String.valueOf(game.blank) ,"finish");
		 			if(game.isRight()){
		 				showWinDialog();
		 			}
		 			else {
		 				Log.d(" 9", " not win");
		 				showFailDialog();
		 			}
		 	 	}
		 	else {
		 		Log.d(String.valueOf(game.blank), "not finish");
		 		
		 		showFailDialog();
		 	}
			 		
			}
		};
		finishB.setOnClickListener(Button_Finish);
		
		//hint button
		Button hintB = (Button)findViewById(R.id.buttonHint);
		OnClickListener Button_Hint = new OnClickListener(){
			@Override
			public void onClick(View v) {
				nine.invalidate();
				if(game.candidate!=2)
					game.candidate=2;
				else 
					game.candidate=3;
			}
		};
		hintB.setOnClickListener(Button_Hint);
		
		//back button
		Button backB = (Button)findViewById(R.id.buttonBack);
		OnClickListener Button_Back = new OnClickListener(){
			@Override
			public void onClick(View v) {
				game.back();
				nine.invalidate();
			}
		};
		backB.setOnClickListener(Button_Back);
		
		//on button
		Button ONB = (Button)findViewById(R.id.buttonON);
		OnClickListener Button_on = new OnClickListener(){
			@Override
			public void onClick(View v) {
				Log.d("click", "on");
				nine.invalidate();
				if(game.candidate==0)
					game.candidate=1;					
				else 
					game.candidate=0;
			}
		};
		ONB.setOnClickListener(Button_on);
		
		//exit button
		Button exitB = (Button)findViewById(R.id.buttonExit);
		OnClickListener Button_Exit = new OnClickListener(){
			@Override
			public void onClick(View v) {
        		Intent intent = new Intent(NineActivity.this, ModeActivity.class);
        		startActivity(intent);
			}
		};
		exitB.setOnClickListener(Button_Exit);

		//Break button
		Button breakButton = (Button)findViewById(R.id.buttonBreak);
		OnClickListener Button_Break = new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(game.candidate!=4){
					nine.setVisibility(View.INVISIBLE);
					Log.d(" pause", " success ");
					game.candidate=4;
					
				}else if (game.candidate==4){
					nine.setVisibility(View.VISIBLE);
					game.candidate=5;
				}
			}
		};
		breakButton.setOnClickListener(Button_Break);
    }

    public void showWinDialog(){

        final AlertDialog.Builder winDialog = 
            new AlertDialog.Builder(NineActivity.this);

        winDialog.setTitle("Game Over");
        winDialog.setMessage("Congratulations!You Win!");
        winDialog.setPositiveButton("OK", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
        winDialog.setNegativeButton("back", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
        		Intent intent = new Intent(NineActivity.this, ModeActivity.class);
        		startActivity(intent);
            }
        });
        winDialog.show();
   }

    public void showFailDialog(){

        final AlertDialog.Builder failDialog = 
            new AlertDialog.Builder(NineActivity.this);

        failDialog.setTitle("Game Over");
        failDialog.setMessage("Sorry,you do not finish! Do you want to continue?");
        failDialog.setPositiveButton("OK", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
        failDialog.setNegativeButton("NO", 
            new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
        		Intent intent = new Intent(NineActivity.this, ModeActivity.class);
        		startActivity(intent);
            }
        });
        failDialog.show();
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

