package com.example.ssudoku;

import java.util.Date;

public class Timer {
	
	private long total;
	private long begin;
	private long end;
	private boolean isTiming;
	
	public void start(){
		total=0;
		Date now =new Date();
		begin=now.getTime();
		isTiming=true;
	}
	
	public void pauseOrRestart(){
		if(isTiming==true){
			Date now =new Date();
			end=now.getTime();
			total=total+(end-begin);
			isTiming=false;
		}
		else{
			Date now =new Date();
			begin=now.getTime();
			isTiming=true;
		}
	}
	
	public long totalTime(){
		return total;
	}
	
	public String toString(){
		long time=totalTime();
		int hours=(int)(time/1000/3600);
		int minutes=(int) (time/1000%3600/60);
		int seconds=(int) (time/1000%3600%60);
		String a="The time you used is:  Hours: "+hours+" minutes: "+minutes+" seconds: "+seconds;
		return a;
		
		
	}

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("1");
		Date now = new Date();
		System.out.println(now.get);
	
	}*/
}
