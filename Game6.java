package com.example.ssudoku;

import java.util.Arrays;

public class Game6 {

	static int blank6;
	static int[][] now6;
	static int[][] answer6;
	
	public static int k;
	
	private String str="0502062460031240655604214006320602010";
	private String ansString="351246246153124365563421415632632514";
	
	public Game6(){
		now6=getProblemString(str);
		answer6=getProblemString(ansString);
	}
	
	
	public int getNumber(String s){
		
		k=s.charAt(0)-'0';
		return k;
	}
	
	//return the content of each space when giving the initial game
	public String getString(int i,int j){
		blank6=0;
		if(now6[i][j]==0){
			blank6++;
			return "";
		}
		else return String.valueOf(now6[i][j]%10);
	}
	
	//check the duplicates 
	public boolean isDuplicate6(int i,int j,int x){
		int equal=0;
		for(int m=0;m<6;m++){
			//if(now6[i][m]%10==x||now6[m][j]%10==x||now6[i/2*2+m/3][j/3*3+m%3]%10==x) equal++;
			if(now6[i][m]%10==x) equal++;
			if(now6[m][j]%10==x) equal++;
			if(now6[i/2*2+m/3][j/3*3+m%3]%10==x) equal++;
		}
		if(equal!=3) return true;
		else return false;
		
	}
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank6==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		return Arrays.equals(now6,answer6);
	}
	
	//decide what to do with the array after the onTouch event
	public boolean fill_in_blank(int x,int i,int j){
		//if(now[i][j]==0) blank--;
		if(now6[i][j]==0||now6[i][j]>10){
			now6[i][j]=x+10;
			return true;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now6[i][j]>0&&now6[i][j]<9) return true;
		else return false;
	}
	
	public int[][] getProblemString(String s) {
		int[][] sudo = new int[6][6];
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				 sudo[i][j]=s.charAt(6*j+i)- '0';
			}			 
		}
		return sudo;

	}
}
