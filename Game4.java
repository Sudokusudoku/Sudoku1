package com.example.ssudoku;

import java.util.Arrays;

public class Game4 {

	int blank4;
	int[][] now4;
	public static int j;
	int[][] wrongPlace=new int[4][4];
	
	private String str="0020000423000400";
	private String ansString="4123321423411432";
	
	public Game4(){
		now4=getProblemString(str);
		//answer4=getProblemString(ansString);
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
		int[] num=new int[4];
		num=getUsedNum(i,j);
		if(num[x-1]==x) return true;
		else return false;
		
	}
	
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank4==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		/*int wrong=0;
		//return Arrays.equals(now,answer);
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if((now4[i][j]%10)!=answer4[i][j]) wrong++;
			}
		}
		if(wrong==0) return true;
		else return false;*/
		int sum=0;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				sum=sum+wrongPlace[i][j];
			}
		}
		if(sum==0) return true;
		else return false;
	}
	
	
	public boolean fill_in_blank(int x,int i,int j){
		//if(now[i][j]==0) blank--;
		if(x>0&&x<10){
			if(now4[i][j]==0||now4[i][j]>10){
				now4[i][j]=x+10;
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now4[i][j]>0&&now4[i][j]<10) return true;
		else return false;
	}
	public boolean isBlank(int i,int j){
		if(now4[i][j]==0) return true;
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
	public int[] getUsedNum(int x,int y){
		int num[] =new int[4];
		//get the used number of horizontal direction
		for(int i=0;i<4;i++){
			if(i!=y){
				int number=now4[x][i];
				if(number!=0) num[number%10-1]=number%10;			
			}
		}
		//get the used number of vertical direction
		for(int i=0;i<4;i++){
			if(i!=x){
				int number=now4[i][y];
				if(number!=0) num[number%10-1]=number%10;		
			}
		}
		//get the used number of its square
		for(int i=0;i<4;i++){
			if(x/2*2+i/2==x&&y/2*2+i%2==y){
				continue;
			}
			int number=now4[x/2*2+i/2][y/2*2+i%2];
			if(number!=0) num[number%10-1]=number%10;
		}
        return num;
	}
	
	//return the candidate number of each blank
	public String candidate(int x,int y){
		int num[] =new int[4];
		num=getUsedNum(x,y);
		String candidate="";
		for(int i=0;i<4;i++){
			if(num[i]==0) candidate=candidate+(i+1);
		}
		return candidate;
	}
	
	public int[] hintPlace(){
		int[] place=new int[2];
		int length=10;
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(now4[i][j]>0){
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
		now4[Square4View.fillX][Square4View.fillY]=0;
	}
	
	public void wrongPlace(int i,int j,int x){
		if(isDuplicate4(i,j,x)){
			wrongPlace[i][j]=1;
		}
		else wrongPlace[i][j]=0;
	}
	public boolean isWrong(int i,int j){
		if(now4[i][j]>10){
			if(isDuplicate4(i,j,now4[i][j]-10)){
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
