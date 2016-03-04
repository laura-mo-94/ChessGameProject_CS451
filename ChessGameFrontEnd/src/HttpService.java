import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.swing.*;

public class HttpService 
{
	protected final static String USER_AGENT = "CHROME";
	protected final static String SERVER_SITE =  "http://localhost:3000";
	protected Timer timer;
	/*----------------------------------------------------------
	 * Set up an httpURLConnection based on an url
	 *----------------------------------------------------------*/
	public static HttpURLConnection setUpConnection(String url) throws Exception
	{	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setDoOutput(true);
		return con;
	}
	
	public void attachTimer(Timer t)
	{
		timer = t;
	}
	
	/*----------------------------------------------------------
	 * Gets results of a post call in a string and returns it
	 *----------------------------------------------------------*/
	public String getResults(HttpURLConnection con)
	{
		System.out.println("howdy");
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
			while((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(response.toString());
		return response.toString();
	}
	
	/*----------------------------------------------------------
	 * Gets results of a post call with parameters as a string
	 * and returns it.
	 *----------------------------------------------------------*/
	public String getResultsWithParams(HttpURLConnection con, String params)
	{
		String type = "application/x-www-form-urlencoded";
		con.setRequestProperty("Content-Type", type);
		con.setRequestProperty("Content-Length", String.valueOf(params.length()));
		 
		byte[] postDataBytes;
		try {
			postDataBytes = params.getBytes("UTF-8");
			con.getOutputStream().write(postDataBytes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return getResults(con);
	}
	
	/*----------------------------------------------------------
	 * Encode parameters for post.
	 *----------------------------------------------------------*/
	protected String encodeStringForPost(Map<String, String> params) throws Exception
	{
		String encodedParam = "";
		boolean isFirst = true;
		for(String key : params.keySet())
		{
			if(!isFirst)
			{
				encodedParam += "&";
			}
			else
			{
				isFirst = false;
			}
			encodedParam += URLEncoder.encode(key, "UTF-8");
			encodedParam += "=";
			encodedParam += URLEncoder.encode(params.get(key), "UTF-8");
		}
		return encodedParam;
	}
}
