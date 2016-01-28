import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class HttpURLConnectionTest {
	private final String USER_AGENT = "CHROME";
	
	public static void main(String[] args) throws Exception {
		
		
		JFrame f = new JFrame();
		JLabel label = new JLabel("0");
		label.setBounds(180, 40, 100, 10);
		f.add(label);
		
		JButton b = new JButton("ADD ONE");
		b.setBounds(130, 100, 100, 40);
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
		f.setSize(400, 500);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	private void sendGet() throws Exception {
		String url = "http://localhost:3000/add";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL: " + url);
		System.out.println("Response Code: " + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		
		in.close();
		
		System.out.println(response.toString());
	}
	
	private void sendAction() throws Exception {
		String url = "http://localhost:3000/add";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("Requesting to add a number");
		System.out.println("Response Code: " + responseCode);
	
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		
		in.close();
		System.out.println(response.toString());
	}
	
	private void checkAction() throws Exception {
		String url = "http://www.google.com";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("Requesting to add a number");
		System.out.println("Response Code: " + responseCode);
	}
	
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}
}
