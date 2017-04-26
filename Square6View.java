package com.example.ssudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Square6View extends View{

	public Square6View(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	private Game6 game=new Game6();
	private float width;
	private float hight;
	private static int selectX;
	private static int selectY;
	private Rect selectRect=new Rect();
	private Rect duplicatetRect=new Rect();
	static boolean isFill;
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		
		this.width =( w-26) / 6f;
		this.hight = ( w-26) / 6f;
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	 
	 public void onDraw(Canvas canvas){
		 super.onDraw(canvas);
		 
		 //width=((getWidth()-26)/9);
		 //hight=((getHeight()-240)/9);
		 
		 //draw square
		 Paint background = new Paint();
		 background.setColor(Color.WHITE);
		 
		 Paint broaderline = new Paint();
		 broaderline.setColor(Color.BLACK);
		 broaderline.setStrokeWidth(3);
		 broaderline.setStyle(Paint.Style.STROKE); 
		 canvas.drawRect(13, 10, getWidth()-13, getWidth()-26, broaderline);
		 
		 Paint lightline = new Paint();
		 lightline.setColor(Color.BLACK);
		 Paint darkline = new Paint();
		 darkline.setColor(Color.BLACK);
		 darkline.setStrokeWidth(3);
		 
		
		 for(int i=1;i<6;i++){
			 canvas.drawLine(13, 10+i*hight, getWidth()-13, 10+i*hight, lightline);
			 canvas.drawLine(13+i*width, 10, 13+i*width , getWidth()-26, lightline);
			 
			 if(i%2==0){
				 canvas.drawLine(13, 10+i*hight, getWidth()-13, 10+i*hight, darkline);
			 }
			 if(i%3==0){
				 canvas.drawLine(13+i*width, 10, 13+i*width , getWidth()-26, darkline); 
			 }
		 }
		 
		 //draw initial numbers
		 Paint number=new Paint();
		 number.setColor(Color.BLACK);
		 number.setTextSize(hight/2);
		 number.setTextAlign(Align.CENTER);
		 FontMetrics fm = number.getFontMetrics();
		 float x=13+width/2;
		 float y=10+hight/2 - (fm.ascent+fm.descent)/2;
		 
		 for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				canvas.drawText(game.getString(i,j),i*width+x,j*hight+y,number);
			}
		 }
		 
		 //draw selected area
		 Paint selected = new Paint();
	 	 selected.setColor(Color.CYAN);
	 	 selected.setStrokeWidth(6);
	 	 selected.setStyle(Paint.Style.STROKE); 
	 	 if(!game.isOriginal(selectX, selectY)){
	 		  canvas.drawRect(selectRect, selected);
	 	 }
	 	 
	 	 //showNumber(selectX, selectY);	
	 	 
	 	 //to check if it is duplicate
		 Paint errorline = new Paint();
		 errorline.setColor(Color.RED);
		 errorline.setStrokeWidth(6);
		 errorline.setStyle(Paint.Style.STROKE); 
	 	 if(isFill==true){
	 		if(game.isDuplicate6(selectX, selectY,game.k)){
	 			if(!game.isOriginal(selectX, selectY)){
	 				canvas.drawRect(duplicatetRect, errorline);
					Log.d(" ", "false");
	 			}
		 	 }
		 	 else {
		 		Log.d(" ", "t");
			}
	 	 }
	 	 
	 	 if(game.isFinished()){
	 		 if(game.isRight()){
	 			 
	 		 }
	 	 }
	 }
	 
	public boolean onTouchEvent(MotionEvent event) {
		float clickX=event.getX();
		float clickY=event.getY();
		selectX = (int) ((clickX-13)/width);
		selectY = (int) ((clickY-10)/width);
			 
		if (event.getAction()!= MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);	
			 
		Log.d("hhh", "aa");//to check if click successfully		 
		if(clickY>10&&clickY<getWidth()-16)
			selectedR(selectX, selectY);
		duplicateR(selectX, selectY);
//		initiaNum(selectX,selectY);
//		showNumber(selectX, selectY);
		isFill=game.fill_in_blank(game.k, selectY, selectX); 		 
		return true;
		}
		
//	private void initiaNum(int x, int y) {
//		game.getNumber("0");
//		game.setNum(x, y);
//		invalidate();
//	}

	public void selectedR(int x,int y){
		invalidate(selectRect);
		selectRect.set((int)(13+x*width), (int)(10+y*hight), (int)(13+(x+1)*width),(int)(13+(y+1)*hight));
		invalidate(selectRect);
		}
	public void duplicateR(int x,int y){
		
		duplicatetRect.set((int)(13+x*width), (int)(10+y*hight), (int)(13+(x+1)*width),(int)(13+(y+1)*hight));
		invalidate(duplicatetRect);
	}	
//		public void showNumber(int x,int y){
//			game.setNum(x,y);
//			invalidate();
//						
//		}
}
