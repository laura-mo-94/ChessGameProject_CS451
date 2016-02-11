import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class HttpURLConnectionTest {
	private final static String USER_AGENT = "CHROME";
	private String id;
	
	public static void main(String[] args) throws Exception {
		buildStartFrame();
		//buildGameFrame();
	}
	
	private static void buildStartFrame()
	{
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		JFrame startFrame = new JFrame();
		JLabel label = new JLabel("Please enter your name");
		label.setBounds(100, 40, 300, 20);
		startFrame.add(label);
		
		JTextField textField = new JTextField(20);
		textField.setBounds(100, 100, 200, 30);
		
		startFrame.add(textField);
		
		JButton b = new JButton("JOIN GAME");
		b.setBounds(130, 150, 100, 40);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try{
					String name = textField.getText();
					http.joinGame(name, label);
					
					JoinGameUpdater updater = new JoinGameUpdater(startFrame, name);
					Timer timer = new Timer(1000, updater);
					timer.start();
					
					updater.attachTimer(timer);
				}catch(Exception exp)
				{
					System.out.println("Error! Failed to send button action. " + exp);
					exp.printStackTrace();
				}
			}
		});
		
		startFrame.add(b);
		startFrame.setSize(400, 500);
		startFrame.setLayout(null);
		startFrame.setVisible(true);
	}
	
	public static void buildGameFrame(String id)
	{	
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		JFrame f = new JFrame();
		JLabel matchName = new JLabel(id);
		matchName.setBounds(180, 5, 100, 10);
		f.add(matchName);
		
		JLabel label = new JLabel("Not connected");
		label.setBounds(180, 40, 100, 10);
		f.add(label);
		
		JButton b = new JButton("ADD ONE");
		b.setBounds(130, 100, 100, 40);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try{
					http.sendAction(matchName.getText());
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		
		
		f.add(b);
		
		GameUpdater updater = new GameUpdater(label, matchName.getText());
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
	
	public String getResults(HttpURLConnection con)
	{
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
		
		return response.toString();
	}
	
	public String getResultsWithParams(HttpURLConnection con, String params)
	{
		String type = "application/x-www-form-urlencoded";
		con.setRequestProperty("Content-Type", type);
		con.setRequestProperty("Content-Length", String.valueOf(params.length()));
		 
		byte[] postDataBytes;
		try {
			postDataBytes = params.toString().getBytes("UTF-8");
			con.getOutputStream().write(postDataBytes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return getResults(con);
	}
	
	private String encodeStringForPost(Map<String, String> params) throws Exception
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
	
	private void joinGame(String name, JLabel label) throws Exception
	{
		String url = "http://localhost:3000/join";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		String encodedParam = encodeStringForPost(params);

		String response = getResultsWithParams(con, encodedParam);
		label.setText(response.toString());
	}
	
	private String sendAction(String name) throws Exception
	{	
		String url = "http://localhost:3000/add";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("number", "1");
		params.put("user", name);
		
		String encodedParam = encodeStringForPost(params);
		
		String response = getResultsWithParams(con, encodedParam);
		
		return response.toString();
	}
	
	public String checkForOpponent(String userName) throws Exception
	{
		String url = "http://localhost:3000/getOpponent";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", userName);
		
		String encodedParam = encodeStringForPost(params);
		String response = getResultsWithParams(con, encodedParam);
		
		return response.toString();
	}
	
	public String checkState(String url, String name)
	{
		HttpURLConnection con;
		BufferedReader in = null;
		String response = "";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		
		try {
			con = HttpURLConnectionTest.setUpConnection(url);
			String encodedParam = encodeStringForPost(params);
			response = getResultsWithParams(con, encodedParam);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return response.toString();
	}
}
