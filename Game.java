package com.example.ssudoku;

import java.util.Arrays;

import android.R.integer;

public class Game {

	private String str="360000000004230800000004200"
			+"070460003820000014500013020"
			+"001900000007048300000000045";

//	public int sudoku[] = new int[9*9];
	public static int i;
	int blank;
	int[][] now;
	int[][] wrongPlace=new int[9][9];
	
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
		/*int equal=0;
		for(int m=0;m<9;m++){
			//if(now[i][m]%10==x||now[m][j]%10==x||now[i/3*3+m/3][j/3*3+m%3]%10==x) equal++;
			if(now[i][m]%10==x) equal++;
			if(now[m][j]%10==x) equal++;
			if(now[i/3*3+m/3][j/3*3+m%3]%10==x) equal++;
		}
		if(equal!=3) return true;
		else return false;*/
		int[] num=new int[9];
		num=getUsedNum(i,j);
		if(num[x-1]==x) return true;
		else return false;
	}
	
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		/*int wrong=0;
		//return Arrays.equals(now,answer);
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if((now[i][j]%10)!=answer[i][j]) wrong++;
			}
		}
		if(wrong==0) return true;
		else return false;*/
		int sum=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				sum=sum+wrongPlace[i][j];
			}
		}
		if(sum==0) return true;
		else return false;
	}
	
	//decide what to do with the array after the onTouch event
	public boolean fill_in_blank(int x,int i,int j){
		if(x>0&&x<10){
			if(now[i][j]==0||now[i][j]>10){
				now[i][j]=x+10;
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now[i][j]>0&&now[i][j]<10) return true;
		else return false;
	}
	
	public boolean isBlank(int i,int j){
		if(now[i][j]==0) return true;
		else return false;
	}
	
	public int[][] getProblemString(String s) {
		int[][] sudo = new int[9][9];
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				 sudo[i][j]=s.charAt(9*i+j)- '0';
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
	
	public int[] getUsedNum(int x,int y){
		int num[] =new int[9];
		//get the used number of horizontal direction
		for(int i=0;i<9;i++){
			if(i!=y){
				int number=now[x][i];
				if(number!=0) num[number%10-1]=number%10;			
			}
		}
		//get the used number of vertical direction
		for(int i=0;i<9;i++){
			if(i!=x){
				int number=now[i][y];
				if(number!=0) num[number%10-1]=number%10;		
			}
		}
		//get the used number of its square
		for(int i=0;i<9;i++){
			if(x/3*3+i/3==x&&y/3*3+i%3==y){
				continue;
			}
			int number=now[x/3*3+i/3][y/3*3+i%3];
			if(number!=0) num[number%10-1]=number%10;
		}
        return num;
	}
	
	//return the candidate number of each blank
	public String candidate(int x,int y){
		int num[] =new int[9];
		num=getUsedNum(x,y);
		String candidate="";
		for(int i=0;i<9;i++){
			if(num[i]==0) candidate=candidate+(i+1);
		}
		return candidate;
	}
	
	public int[] hintPlace(){
		int[] place=new int[2];
		int length=10;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(now[i][j]>0){
					continue;
				}
				if(candidate(i,j).length()<length){
					length=candidate(i,j).length();
					place[0]=i;
					place[1]=j;
				}
			}
		}
		return place;
	}
	
	public void back(){
		if(!isOriginal(Square9View.fillY,Square9View.fillX)){
			now[Square9View.fillY][Square9View.fillX]=0;
		}
	}
	
	public void wrongPlace(int i,int j,int x){
		if(isDuplicate9(i,j,x)) {
			wrongPlace[i][j]=1;
		}
		else wrongPlace[i][j]=0;
		
	}
	
	public boolean isWrong(int i,int j){
		if(now[i][j]>10){
			if(isDuplicate9(i,j,now[i][j]-10)){
				wrongPlace[i][j]=1;
				return true;
			}
			else{
				wrongPlace[i][j]=0;
				return false;
			}
		}
		else return false;
	}
	
	
	

	
}
