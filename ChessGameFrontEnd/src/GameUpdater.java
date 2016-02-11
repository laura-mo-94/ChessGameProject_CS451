import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.swing.*;

public class GameUpdater implements ActionListener {
	private JLabel label;
	private String currentValue = "Nothing";
	
	private HttpURLConnectionTest http;
	
	public GameUpdater(JLabel label)
	{
		this.label = label;
		http = new HttpURLConnectionTest();
	}
	
	public void actionPerformed(ActionEvent e){
		String url = "http://localhost:3000/getState";
		HttpURLConnection con;
		BufferedReader in = null;
		StringBuffer response = new StringBuffer();
		
		try {
			con = HttpURLConnectionTest.setUpConnection(url);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			
			
			while((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!response.toString().equals(currentValue))
		{
			label.setText(response.toString());
			currentValue = response.toString();
		}
	}
}
