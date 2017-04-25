package com.example.sudokuapp;

import java.util.Arrays;

public class game {
	static int blank;
	static int[][] now;
	static int[][] answer;
	
	//return the content of each space when giving the initial game
	public String getString(int i,int j){
		blank=0;
		if(now[i][j]==0){
			blank++;
			return "";
		}
		else return String.valueOf(now[i][j]%10);
	}
	
	//check the duplicates 
	public boolean isDuplicate9(int i,int j,int x){
		int equal=0;
		for(int m=0;m<9;m++){
			if(now[i][m]%10==x||now[m][j]%10==x||now[i/3*3+m/3][j/3*3+m%3]%10==x) equal++;
		}
		if(equal!=0) return false;
		else return true;
		
	}
	
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		return Arrays.equals(now,answer);
	}
	
	//decide what to do with the array after the onTouch event
	public void fill_in_blank(int x,int i,int j){
		//if(now[i][j]==0) blank--;
		if(now[i][j]==0||now[i][j]>10){
			now[i][j]=x+10;
		}
	}
	
	
	

}
