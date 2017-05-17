import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;


@TargetApi(Build.VERSION_CODES.KITKAT) @SuppressLint("NewApi") public class Read {

	/**
	 * @param args
	 */
	static String[]  s1=new String[50];
	static String[]  s2=new String[50];
	static String[]  s3=new String[50];
	static String[]  s4=new String[49];
	static String[]  s5=new String[50];
	@TargetApi(Build.VERSION_CODES.KITKAT) @SuppressLint("NewApi") public static void readToString() {
		
		try(BufferedReader br=new BufferedReader(new FileReader("assets/four.txt"))){
			for(int i=0;i<s1.length;i++){
				s1[i]=br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0;i<s1.length;i++){
			System.out.println(s1[i]);
		}
		
		/*try(BufferedReader br=new BufferedReader(new FileReader("assets/six.txt"))){
			for(int i=0;i<s2.length;i++){
				s2[i]=br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0;i<s2.length;i++){
			System.out.println(s2[i]);
		}
		
		try(BufferedReader br=new BufferedReader(new FileReader("assets/primary nine.txt"))){
			for(int i=0;i<s3.length;i++){
				s3[i]=br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0;i<s3.length;i++){
			System.out.println(s3[i]);
		}
		
		try(BufferedReader br=new BufferedReader(new FileReader("assets/middle nine.txt"))){
			for(int i=0;i<s4.length;i++){
				s4[i]=br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0;i<s4.length;i++){
			System.out.println(s4[i]);
		}
		
		try(BufferedReader br=new BufferedReader(new FileReader("assets/advanced nine.txt"))){
			for(int i=0;i<s5.length;i++){
				s5[i]=br.readLine();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i=0;i<s5.length;i++){
			System.out.println(s5[i]);
		}*/

	}
	
	public static String pro(String a) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader("assets/"+a+".txt"));
	    LineNumberReader lbr=new LineNumberReader(new FileReader("assets/"+a+".txt"));
	    lbr.skip(Long.MAX_VALUE); 
	    int i=lbr.getLineNumber();
	    Random random=new Random();
	    i=random.nextInt(i)+1;
	    String s="";
	    int m=0;
	    while(null!=(s=br.readLine()))
	    {
	        if((m+1)==i)
	            break;
	        m++;
	    }

	    System.out.println("     "+s);
	    return s;
	}
	
	public static void main(String[] args) throws IOException {
		//readToString();
		/* BufferedReader br=new BufferedReader(new FileReader("assets/six.txt"));
		    LineNumberReader lbr=new LineNumberReader(new FileReader("assets/six.txt"));
		    lbr.skip(Long.MAX_VALUE); 
		    int i=lbr.getLineNumber();
		    Random random=new Random();
		    i=random.nextInt(i)+1;
		    String s="";
		    int m=0;
		    while(null!=(s=br.readLine()))
		    {
		        if((m+1)==i)
		            break;
		        m++;
		    }

		    System.out.println(s);*/
		String a=pro("six");
		System.out.println(a);
	}
	/*static void readToArray(int f[], int n, String name){
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(name));
			int i=0;
			while ((sCurrentLine = br.readLine()) != null) {
				f[i]=Integer.parseInt(sCurrentLine);
				
				//System.out.println(f[i]);
				i++;
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		

	}*/

}
