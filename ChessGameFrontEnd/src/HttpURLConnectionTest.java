import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class HttpURLConnectionTest {
	private final static String USER_AGENT = "CHROME";
	
	public static void main(String[] args) throws Exception {
		buildFrame();
	}
	
	private static void buildFrame()
	{	
		JFrame f = new JFrame();
		JLabel label = new JLabel("Not connected");
		label.setBounds(180, 40, 100, 10);
		f.add(label);
		
		JButton b = new JButton("ADD ONE");
		b.setBounds(130, 100, 100, 40);
		
		HttpURLConnection stateCheckConnection;
		HttpURLConnection sendUpdateConnection;
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				HttpURLConnectionTest http = new HttpURLConnectionTest();
				try{
					http.sendAction();
				}catch(Exception exp)
				{
					System.out.println("Error! Failed to send button action. " + exp);
				}
			}
		});
		
		
		f.add(b);
		
		GameUpdater updater = new GameUpdater(label);
		Timer timer = new Timer(1000, updater);
		timer.start();
		
		f.setSize(400, 500);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	public static HttpURLConnection setUpConnection(String url) throws Exception
	{	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setDoOutput(true);
		return con;
	}
	
	private void sendAction() throws Exception
	{	
		String url = "http://localhost:3000/add";
		HttpURLConnection con = setUpConnection(url);
		String type = "application/x-www-form-urlencoded";
		String encodedParam = URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
		
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", type);
		con.setRequestProperty("Content-Length", String.valueOf(encodedParam.length()));
		 
		byte[] postDataBytes = encodedParam.toString().getBytes("UTF-8");
		con.getOutputStream().write(postDataBytes);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		
		System.out.println("finished");
		System.out.println(response.toString());
	}
	
	private void checkAction() throws Exception {
		String url = "http://www.google.com";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("Requesting to add a number");
		System.out.println("Response Code: " + responseCode);
	}
}
