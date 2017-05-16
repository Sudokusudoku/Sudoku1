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
import android.view.View.MeasureSpec;



public class Square6View extends View{

	public Square6View(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public Square6View(Context context) {
		super(context);
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
		System.out.print("hhhh");
	}
	
	 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        setMeasuredDimension(   measureWidth(widthMeasureSpec), 
	        		measureWidth(widthMeasureSpec)+10);
	    }
		 
	 private int measureWidth(int measureSpec) {    
	        int w = 0;

	        int specMode = MeasureSpec.getMode(measureSpec);
	        int specSize = MeasureSpec.getSize(measureSpec);

	        if (specMode == MeasureSpec.EXACTLY) {   // match_parent , accurate
	            w = specSize;
	        } else {
	            w =600 ;    
	            if (specMode == MeasureSpec.AT_MOST) {  // wrap_content 
	               w = Math.min(w,specSize); //注意取两者之间小的值 
	           }
	        }
	        return w;
	    }
	    
	 public void onDraw(Canvas canvas){
		 
		 
		 //width=((getWidth()-26)/9);
		 //hight=((getHeight()-240)/9);
		 
		 //draw square
		 Paint background = new Paint();
		 background.setColor(Color.WHITE);
		 
		 Paint broaderline = new Paint();
		 broaderline.setColor(Color.BLACK);
		 broaderline.setStrokeWidth(3);
		 broaderline.setStyle(Paint.Style.STROKE); 
		 canvas.drawRect(13, 10, getWidth()-13, getWidth()-16, broaderline);
		 
		 Paint lightline = new Paint();
		 lightline.setColor(Color.BLACK);
		 Paint darkline = new Paint();
		 darkline.setColor(Color.BLACK);
		 darkline.setStrokeWidth(3);
		 
		 //draw line
		 for(int i=1;i<6;i++){
			 canvas.drawLine(13, 10+i*hight, getWidth()-13, 10+i*hight, lightline);
			 canvas.drawLine(13+i*width, 10, 13+i*width , getWidth()-16, lightline);
			 
			 if(i%2==0){
				 canvas.drawLine(13, 10+i*hight, getWidth()-13, 10+i*hight, darkline);
			 }
			 if(i%3==0){
				 canvas.drawLine(13+i*width, 10, 13+i*width , getWidth()-26, darkline); 
			 }
		 }
		 
//		 for(int j=0;j<6;j++){
//			 if(j%3==0){
//				 canvas.drawLine(13, 10+j*hight, getWidth()-13, 10+j*hight, darkline);
//				 canvas.drawLine(13+j*width, 10, 13+j*width , getWidth()-16, darkline); 
//			 }
//		 }
		
		 //draw initial numbers
		 Paint iniBackPaint=new Paint();
		 iniBackPaint.setColor(Color.argb(85,107,47,0));
		 iniBackPaint.setAlpha(30);
		 Paint number=new Paint();
		 number.setColor(Color.BLACK);
		 number.setTextSize(hight/2);
		 number.setTextAlign(Align.CENTER);
		 FontMetrics fm = number.getFontMetrics();
		 float x=13+width/2;
		 float y=10+hight/2 - (fm.ascent+fm.descent)/2;
		 
		 for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if( game.now6[i][j]>0 && game.now6[i][j]<10)
					canvas.drawRect((int)(14+i*width), (int)(11+j*hight), (int)(12+(i+1)*width),(int)(10+(j+1)*hight),iniBackPaint);				
				canvas.drawText(game.getString(i, j),i*width+x,j*hight+y,number);
			}
		 }
		 
		//draw selected area
		 Paint selected = new Paint();
	 	 selected.setColor(Color.argb(107,142,35,0));
	 	 selected.setStrokeWidth(6);
	 	 selected.setStyle(Paint.Style.STROKE); 
	 	 
		 Paint errorline = new Paint();
		 errorline.setColor(Color.RED);
		 errorline.setStrokeWidth(6);
		 errorline.setStyle(Paint.Style.STROKE); 
		 
	 	 
	 	 //to check if it is duplicate
	 	 if(isFill==true){
	 		if(game.isDuplicate6(selectX, selectY,game.k)){
	 			if(!game.isOriginal(selectX, selectY)){
	 				canvas.drawLine((int)(15+selectX*width), (int)(10+selectY*hight), (int)(12+(selectX+1)*width), (int)(10+(selectY+1)*hight), errorline);
	 				canvas.drawLine((int)(12+(selectX+1)*width), (int)(10+selectY*hight), (int)(15+selectX*width), (int)(10+(selectY+1)*hight), errorline);
	 				Log.d(" ", "false");
	 			}
		 	 }
		 	 else {
			 	 if(!game.isOriginal(selectX, selectY)){
			 		  canvas.drawRect(selectRect, selected);
			 	 }
		 		Log.d(" ", "t");
			}
	 	 }
	 	 
	 	//to check if win
	 	if(game.isFinished()){
	 		Log.d(" ", " finish");
	 		 if(game.isRight()){
	 			Log.d(" ", " wiin");
	 		 }
	 		 else {
	 			 Log.d(" ", " not win");
	 		 }
	 	 }
	 	else 
	 		Log.d("not finish", "yes");
		 
	 	 	 	 
		super.onDraw(canvas); 
		 
	 }
	 
	 @Override
	public boolean onTouchEvent(MotionEvent event) {
		 float clickX=event.getX();
		 float clickY=event.getY();
		 selectX = (int) ((clickX-13)/width);
		 selectY = (int) ((clickY-10)/width);
		 
		 if (event.getAction()!= MotionEvent.ACTION_DOWN)
			 return super.onTouchEvent(event);	
		 Log.d(String.valueOf(selectX) , String.valueOf(selectY) );
		 Log.d("hhh", "aa");//to check if click successfully		 
		 if(clickY>10&&clickY<getWidth()-16)
			 selectedR(selectX, selectY);
		 	 duplicateR(selectX, selectY);

		 isFill=game.fill_in_blank(game.k, selectX, selectY);
		 Log.d("array", game.getString(selectX, selectY));
		 		 
		return true;
	}
	 

	 

		public void selectedR(int x,int y){
			invalidate(selectRect);
			selectRect.set((int)(13+x*width), (int)(10+y*hight), (int)(13+(x+1)*width),(int)(13+(y+1)*hight));
			invalidate(selectRect);
		}
		
		public void duplicateR(int x,int y){
			
			duplicatetRect.set((int)(13+x*width), (int)(10+y*hight), (int)(13+(x+1)*width),(int)(13+(y+1)*hight));
			invalidate(duplicatetRect);
		}
		

		public String winString (){
			invalidate();
			return "You Win";
		}


	

}
