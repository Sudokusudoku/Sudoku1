package com.example.ssudoku;

import java.util.Arrays;

import android.R.integer;

public class Game {

	private String str="360000000004230800000004200"
			+"070460003820000014500013020"
			+"001900000007048300000000045";

//	public int sudoku[] = new int[9*9];
	public static int i;
	static int blank;
	static int[][] now;
	static int[][] answer;
	
	public Game(){
		now=getProblemString(str);
	}

	public int getNumber(String s){
		
		i=s.charAt(0)-'0';
		return i;
	}


	
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
			//if(now[i][m]%10==x||now[m][j]%10==x||now[i/3*3+m/3][j/3*3+m%3]%10==x) equal++;
			if(now[i][m]%10==x) equal++;
			if(now[m][j]%10==x) equal++;
			if(now[i/3*3+m/3][j/3*3+m%3]%10==x) equal++;
		}
		if(equal!=3) return true;
		else return false;
		
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
	public boolean fill_in_blank(int x,int i,int j){
		//if(now[i][j]==0) blank--;
		if(now[i][j]==0||now[i][j]>10){
			now[i][j]=x+10;
			return true;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now[i][j]>0&&now[i][j]<9) return true;
		else return false;
	}
	
	public int[][] getProblemString(String s) {
		int[][] sudo = new int[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				 sudo[i][j]=s.charAt(9*j+i)- '0';
			}			 
		}

		return sudo;

	}
//
//	public int getTile(int x,int y){
//		return sudoku[y*9+x];
//	}
//
////	public String getTileString(int x,int y){
////		int num=getTile(x, y);
////		if( num == 0)
////			return "";
////		else {
////			return String.valueOf(num);
////
////		}
////	}
//	
////	public void setNum(int x, int y){
////		sudoku[y*9 + x] = i;
////	}
	

	
}
