import java.io.*;
import java.util.*;
public class source {
	public static void main(String args[]) throws IOException
	{
		int ncount=0,pcount=0,neucount=0,p_count=0,n_count=0,neu_count=0;
		int train_pos_tweets=0,sys_pos_tweets=0,sys_neg_tweets=0,sys_neu_tweets=0,train_neg_tweets=0,train_neu_tweets=0;
		HashMap senti_positive = new HashMap();
		HashMap senti_negative = new HashMap();
		HashMap senti_neutral = new HashMap();
		double pos_acc=0,neg_acc=0,nue_acc=0;
		//HashMap<String,Integer> senti_positive=new HashMap<String,Integer>();
		//HashMap<String,Integer> senti_negative=new HashMap<String,Integer>();
		//HashMap<String,Integer> senti_ambiguous=new HashMap<String,Integer>();		
		// Input  files 
		FileInputStream fstream1 = new FileInputStream("/home/syamkumar/Desktop/Telugu_Tweets/TEL_POS.TXT");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
		String strLine1;
		FileInputStream fstream2 = new FileInputStream("/home/syamkumar/Desktop/Telugu_Tweets/TEL_NEG.TXT");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(fstream2));
		String strLine2;
		FileInputStream fstream3 = new FileInputStream("/home/syamkumar/Desktop/Telugu_Tweets/TEL_NEU.TXT");
		BufferedReader br3 = new BufferedReader(new InputStreamReader(fstream3));
		String strLine3;
    	FileInputStream fstream_te_pos = new FileInputStream("/home/syamkumar/Desktop/Telugu_SentiWords/TE_POS.txt");
		BufferedReader brW1 = new BufferedReader(new InputStreamReader(fstream_te_pos));
		String str_pos;
		FileInputStream fstream_te_neg = new FileInputStream("/home/syamkumar/Desktop/Telugu_SentiWords/TE_NEG.txt");
		BufferedReader brW2 = new BufferedReader(new InputStreamReader(fstream_te_neg));
		String str_neg;
		FileInputStream fstream_te_neu = new FileInputStream("/home/syamkumar/Desktop/Telugu_SentiWords/TE_NEU.txt");
		BufferedReader brW3 = new BufferedReader(new InputStreamReader(fstream_te_neu));
		String str_neu;
        
		//output files
		File fout1 = new File("/home/syamkumar/Desktop/telugu_model_out_pos_tweets.txt");
    	FileOutputStream fos1 = new FileOutputStream(fout1);
    	BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
        File fout2 = new File("/home/syamkumar/Desktop/telugu_model_out_neg_tweets.txt");
    	FileOutputStream fos2 = new FileOutputStream(fout2);
    	BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));
        File fout3 = new File("/home/syamkumar/Desktop/telugu_model_out_neu_tweets.txt");
    	FileOutputStream fos3 = new FileOutputStream(fout3);
    	BufferedWriter bw3 = new BufferedWriter(new OutputStreamWriter(fos3));
    	

        //Reading sentiword and putting in  hashmap
		while ((str_pos = brW1.readLine()) != null)   {
		int len_pos_word=str_pos.length();
			pcount++;
			str_pos = str_pos.substring(2,len_pos_word).trim();
			senti_positive.put(str_pos,1);
			//System.out.println ("positive word"+str_pos);
		}//while close
		brW1.close();
		
		while ((str_neg = brW2.readLine()) != null)   {
			int len_neg_word=str_neg.length();
			ncount++;
			str_neg = str_neg.substring(2,len_neg_word).trim();
        	 senti_negative.put(str_neg,-1);
		}
	    brW2.close();  
	    
	    while ((str_neu = brW3.readLine()) != null)   {
			int len_neu_word=str_neu.length();
			neucount++;
			str_neu = str_neu.substring(2,len_neu_word).trim();
			senti_neutral.put(str_neu,0);
		}
	    brW3.close(); 
	    String temp;
	    
	    //Input files for testing 
	   
	    //  Positive data//
		while ((strLine1 = br1.readLine()) != null)   
		{   temp=strLine1;
		    //System.out.println (strLine);
			train_pos_tweets++;
			int len=strLine1.length();
			strLine1 = strLine1.substring(19,len);
			//System.out.println (strLine);
			// String[] arr = strLine1.split(" ");
			String[] arr = strLine1.split("\\s+");
			 for ( String ss : arr) {
				 ss=ss.trim();
                    //System.out.println(ss);
				    //System.out.println(senti_positive.containsKey(ss));
				    //System.out.println(senti_positive.get(ss));
	        		if(senti_positive.containsKey(ss))
	        		{
	        			p_count++;
	        		}
	        		else if(senti_negative.containsKey(ss))
	        		{
	        			n_count++;
	        		}
	        		else if(senti_neutral.containsKey(ss))
	        		{
	        			neu_count++; 
	        		}
	        		//else{
	        		//	}
			        }//for close
		   // 	for (int i = 0; i < 10; i++) {
		    //		bw1.write("ssyamkkuamar");
		    	//	bw1.newLine();
		    ///	}
		   // 	bw1.close();
			// System.out.println(p_count+" "+ n_count+" "+neu_count);
			    if((p_count>n_count) && (p_count>neu_count))
			    {
			    	pos_acc++;
			    	sys_pos_tweets++;
			    	bw1.write(temp);
			    	bw1.newLine();
			    }
			    if ((n_count>p_count) && (n_count>neu_count))
			    {
			    	sys_neg_tweets++;
			    	bw2.write(temp);
			    	bw2.newLine();
			    }
			    if((neu_count>p_count)&& (neu_count>n_count))
			    {
			    	sys_neu_tweets++;
			    	bw3.write(temp);
			    	bw3.newLine();
			    }
			    if(p_count==n_count)
			    {
			    	sys_neu_tweets++;
			    	bw3.write(temp);
			    	bw3.newLine();
			    }
			    p_count=0;n_count=0;neu_count=0;
		}
	        	br1.close();
	        	//negative data//
	    		while ((strLine2 = br2.readLine()) != null)   
	    		{   temp=strLine2;
	    		    //System.out.println (strLine);
	    			train_neg_tweets++;
	    			int len=strLine2.length();
	    			strLine2 = strLine2.substring(19,len);
	    			//System.out.println (strLine);
	    			 String[] arr = strLine2.split(" ");    
	    			 for ( String ss : arr) {
	    				 ss=ss.trim();
	    	        		if(senti_positive.containsKey(ss))
	    	        		{
	    	        			p_count++;
	    	        		}
	    	        		else if(senti_negative.containsKey(ss))
	    	        		{
	    	        			n_count++;
	    	        		}
	    	        		else if(senti_neutral.containsKey(ss))
	    	        		{
	    	        			neu_count++; 
	    	        		}
	    	        		else{}
	    			        }//for close
	 			    if((p_count>n_count) && (p_count>neu_count))
				    {
				    	sys_pos_tweets++;
				    	bw1.write(temp);
				    	bw1.newLine();
				    }
				   if ((n_count>p_count) && (n_count>neu_count))
				    {
					    neg_acc++;
				    	sys_neg_tweets++;
				    	bw2.write(temp);
				    	bw2.newLine();
				    }
				   if((neu_count>p_count)&& (neu_count>n_count))
				    {
				    	sys_neu_tweets++;
				    	bw3.write(temp);
				    	bw3.newLine();
				    }
				    if(p_count==n_count)
				    {
				    	sys_neu_tweets++;
				    	bw3.write(temp);
				    	bw3.newLine();
				    }
	    			    p_count=0;n_count=0;neu_count=0;
	    		}
	    	        	br2.close();
	                 //neutral data//
	    	    		while ((strLine3 = br3.readLine()) != null)   
	    	    		{   temp=strLine3;
							//System.out.println (strLine);
	    	    			train_neu_tweets++;
	    	    			int len=strLine3.length();
	    	    			strLine3 = strLine3.substring(19,len);
	    	    			//System.out.println (strLine);
	    	    			 String[] arr = strLine3.split(" ");    
	    	    			 for ( String ss : arr) {
	    	    				 ss=ss.trim();
	    	    	        		if(senti_positive.containsKey(ss))
	    	    	        		{
	    	    	        			p_count++;
	    	    	        		}
	    	    	        		if(senti_negative.containsKey(ss))
	    	    	        		{
	    	    	        			n_count++;
	    	    	        		}
	    	    	        		if(senti_neutral.containsKey(ss))
	    	    	        		{
	    	    	        			neu_count++; 
	    	    	        		}
	    	    			        }
	    	    			  //for close
	    	    		      // 	for (int i = 0; i < 10; i++) {
	    	    		      //		bw1.write("ssyamkkuamar");
	    	    		   	  //	bw1.newLine();
	    	    		      ///	}
	    	    		      // 	bw1.close();
	    	 			    if((p_count>n_count) && (p_count>neu_count))
	    				    {
	    				    	sys_pos_tweets++;
	    				    	bw1.write(temp);
	    				    	bw1.newLine();
	    				    }
	    				    if ((n_count>p_count) && (n_count>neu_count))
	    				    {
	    				    	sys_neg_tweets++;
	    				    	bw2.write(temp);
	    				    	bw2.newLine();
	    				    }
	    				    if((neu_count>p_count)&& (neu_count>n_count))
	    				    {
	    				    	nue_acc++;
	    				    	sys_neu_tweets++;
	    				    	bw3.write(temp);
	    				    	bw3.newLine();
	    				    }
	    				    if(p_count==n_count)
	    				    {
	    				    	sys_neu_tweets++;
	    				    	bw3.write(temp);
	    				    	bw3.newLine();
	    				    }
	    				   
	    				    
	    	    			    p_count=0;n_count=0;neu_count=0;
	    	    		}
	    	    	        	br3.close();
	        	bw1.close();
	        	bw2.close();
	        	bw3.close();
	        	
	       // 	float a;
            	//	 a=(sys_tel_tweets/totcal_tel_tweets)*100;
	           //System.out.println(total_tel_tweets);
	           //	System.out.println("programe over!"+a );
	        	//System.out.println(senti_positive.keySet());
	        	System.out.println("corpus +ve words:"+pcount+"		corpus -ve words:"+ncount+"		corpus neu words:"+neucount);
	        	System.out.println("Tarain + ve tweets:"+train_pos_tweets+"	Train -ve tweets:"+train_neg_tweets+"	Train neu tweets:"+train_neu_tweets);
	        	System.out.println("Sys + ve tweets:"+sys_pos_tweets+"	Sys -ve tweets:"+sys_neg_tweets+"	Sys neu tweets:"+sys_neu_tweets);
	        	
	        	double result = pos_acc / train_pos_tweets;
	        	System.out.println(result*100);
	        //	result=neg_acc / train_neg_tweets;
	        	//System.out.println(result*100);
	        //	result=nue_acc/train_neu_tweets;
	        	//System.out.println(result*100);
	        	//System.out.println(senti_neutral.size());
	        	//System.out.println(senti_positive.size());
	        	//System.out.println(senti_negative.size());
 }
}