import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class wordcollection_from_tweets {

	public static void main(String[] args) throws IOException {
		FileInputStream fstream = new FileInputStream("/home/syamkumar/Desktop/iamDS/IR_material_for_projects/tweets_TE_out.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine,temp;
		int l;
		while ((strLine = br.readLine()) != null)   
		{  
		//	System.out.println (strLine);
			 int len=strLine.length();
			 strLine = strLine.substring(19,len);
			 System.out.println (strLine);
			 // String[] arr = strLine1.split(" ");
			 String[] arr = strLine.split("\\s+");
			 for ( String ss : arr) {
			        	 ss=ss.trim();
			        	 temp=ss;
			        	 l=temp.length();
			        	 for(int j=0;j<l;j++)
			        	 {
			            // System.out.println(temp.charAt(j));
			        	 }
                    //System.out.println(ss);
				    
			    }
			 ///... 
		   // 	for (int i = 0; i < 10; i++) {
		    //		bw1.write("ssyamkkuamar");
		    	//	bw1.newLine();
		    ///	}
		   // 	bw1.close();
			
		}
	        	br.close();

	}

}
