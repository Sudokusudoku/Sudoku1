package com.example.ssudoku;

import java.util.Arrays;

public class Game4 {

	static int blank4;
	static int[][] now4;
	static int[][] answer4;
	public static int j;
	
	private String str="0020000423000400";
	private String ansString="4123321423411432";
	
	public Game4(){
		now4=getProblemString(str);
		answer4=getProblemString(ansString);
	}
	
	public int getNumber(String s){
		
		j=s.charAt(0)-'0';
		return j;
	}
	
	//return the content of each space when giving the initial game
	public String getString(int i,int j){
		blank4=0;
		if(now4[i][j]==0){
			blank4++;
			return "";
		}
		else return String.valueOf(now4[i][j]%10);
	}
	
	//check the duplicates 
	public boolean isDuplicate4(int i,int j,int x){
		int equal=0;
		for(int m=0;m<4;m++){
			//if(now4[i][m]%10==x||now4[m][j]%10==x||now4[i/2*2+m/2][j/2*2+m%2]%10==x) equal++;
			if(now4[i][m]%10==x) equal++;
			if(now4[m][j]%10==x) equal++;
			if(now4[i/2*2+m/2][j/2*2+m%2]%10==x) equal++;
		}
		if(equal!=3) return true;
		else return false;
		
	}
	
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank4==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		return Arrays.equals(now4,answer4);
	}
	
	public boolean fill_in_blank(int x,int i,int j){
		//if(now[i][j]==0) blank--;
		if(now4[i][j]==0||now4[i][j]>10){
			now4[i][j]=x+10;
			return true;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now4[i][j]>0&&now4[i][j]<9) return true;
		else return false;
	}
	
	public int[][] getProblemString(String s) {
		int[][] sudo = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				 sudo[i][j]=s.charAt(4*j+i)- '0';
			}			 
		}

		return sudo;

	}
	
}
