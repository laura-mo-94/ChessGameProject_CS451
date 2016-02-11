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
	private String gameName;
	
	public GameUpdater(JLabel label, String name)
	{
		this.label = label;
		http = new HttpURLConnectionTest();
		gameName = name;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		String response = http.checkState("http://localhost:3000/getState", gameName);
		
		if(!response.toString().equals(currentValue))
		{
			label.setText(response.toString());
			currentValue = response.toString();
		}
	}
}
