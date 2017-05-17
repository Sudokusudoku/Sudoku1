package com.example.ssudoku;

import java.util.Arrays;

public class Game6 {

	int blank6;
	int[][] now6;
	
	public static int k;
	int[][] wrongPlace=new int[6][6];
	
	private String str="050206246003124065560421400632602010";
	private String ansString="351246246153124365563421415632632514";
	
	public Game6(){
		now6=getProblemString(str);
		//answer6=getProblemString(ansString);
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
		/*int equal=0;
		for(int m=0;m<6;m++){
			//if(now6[i][m]%10==x||now6[m][j]%10==x||now6[i/2*2+m/3][j/3*3+m%3]%10==x) equal++;
			if(now6[i][m]%10==x) equal++;
			if(now6[m][j]%10==x) equal++;
			if(now6[i/2*2+m/3][j/3*3+m%3]%10==x) equal++;
		}
		if(equal!=3) return true;
		else return false;*/
		int[] num=new int[6];
		num=getUsedNum(i,j);
		if(num[x-1]==x) return true;
		else return false;
		
	}
	
	//check if all the blanks are filled
	public boolean isFinished(){
		return blank6==0;
	}
	
	//check if the final answer is right after finished
	public boolean isRight(){
		/*int wrong=0;
		//return Arrays.equals(now,answer);
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if((now6[i][j]%10)!=answer6[i][j]) wrong++;
			}
		}
		if(wrong==0) return true;
		else return false;*/
		int sum=0;
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				sum=sum+wrongPlace[i][j];
			}
		}
		if(sum==0) return true;
		else return false;
	}
	
	
	//decide what to do with the array after the onTouch event
	public boolean fill_in_blank(int x,int i,int j){
		if(x>0&&x<10){
			if(now6[i][j]==0||now6[i][j]>10){
				now6[i][j]=x+10;
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean isOriginal(int i,int j){
		if(now6[i][j]>0&&now6[i][j]<9) return true;
		else return false;
	}
	public boolean isBlank(int i,int j){
		if(now6[i][j]==0) return true;
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
	
	public int[] getUsedNum(int x,int y){
		int num[] =new int[6];
		//get the used number of horizontal direction
		for(int i=0;i<6;i++){
			if(i!=y){
				int number=now6[x][i];
				if(number!=0) num[number%10-1]=number%10;			
			}
		}
		//get the used number of vertical direction
		for(int i=0;i<6;i++){
			if(i!=x){
				int number=now6[i][y];
				if(number!=0) num[number%10-1]=number%10;		
			}
		}
		//get the used number of its square
		for(int i=0;i<6;i++){
			if(x/2*2+i/3==x&&y/2*2+i%3==y){
				continue;
			}
			int number=now6[x/2*2+i/3][y/2*2+i%3];
			if(number!=0) num[number%10-1]=number%10;
		}
        return num;
	}
	
	//return the candidate number of each blank
	public String candidate(int x,int y){
		int num[] =new int[6];
		num=getUsedNum(x,y);
		String candidate="";
		for(int i=0;i<6;i++){
			if(num[i]==0) candidate=candidate+(i+1);
		}
		return candidate;
	}
	
	public int[] hintPlace(){
		int[] place=new int[2];
		int length=10;
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(now6[i][j]>0){
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
		now6[Square6View.fillX][Square6View.fillY]=0;
	}
	
	public void wrongPlace(int i,int j,int x){
		if(isDuplicate6(i,j,x)){
			wrongPlace[i][j]=1;
		}
		else wrongPlace[i][j]=0;
	}
	public boolean isWrong(int i,int j){
		if(now6[i][j]>10){
			if(isDuplicate6(i,j,now6[i][j]-10)){
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
