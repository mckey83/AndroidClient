package ua.com.ex.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class Transport {

	public String getContent(String url){
		String result = "";
		Log.d("MyTAG", "Transport getContent() "+ url);
		try {
			result = new ExecuteTask().execute(url).get();
		} catch (InterruptedException e) {			
			e.printStackTrace();
			result = "";
		} catch (ExecutionException e) {
			e.printStackTrace();
			result = "";
		}
		return result;
	}	

	private class ExecuteTask extends AsyncTask<String, Integer, String> {  	
		@Override  
		protected String doInBackground(String... params) {  
			String res="";
			HttpClient httpClient = new DefaultHttpClient(); 
			try{				 
				HttpGet httpGet  = new HttpGet(params[0]);  
				HttpResponse httpResponse=  httpClient.execute(httpGet);				
				res = readResponse(httpResponse);				
			}  
			catch(Exception exception)  {	
				Log.e("MyTAG", exception.toString());
				
			} finally {
			     httpClient.getConnectionManager().shutdown();
			}	
			return res;  
		}

		private String readResponse(HttpResponse res) {  
			InputStream is=null;   
			String return_text="";  
			try {  
				is=res.getEntity().getContent();  
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));  
				String line="";  
				StringBuffer sb=new StringBuffer();  
				while ((line=bufferedReader.readLine())!=null)  
				{  
					sb.append(line);  
				}  
				return_text=sb.toString();  
			} catch (Exception e)  
			{  
				Log.e("MyTAG", "readResponse"+e.toString());
			}  
			return return_text;  
		} 	
	}
}
