import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.swing.*;

public class JoinGameUpdater implements ActionListener {
	private String currentValue = "Nothing";
	private JFrame activeFrame;
	
	private HttpURLConnectionTest http;
	private String userName;
	private Timer timer;
	
	public JoinGameUpdater(JFrame f, String name)
	{
		http = new HttpURLConnectionTest();
		activeFrame = f;
		userName = name;
	}

	public void attachTimer(Timer t)
	{
		timer = t;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		HttpURLConnectionTest http = new HttpURLConnectionTest();
		String response = "";
		try {
			response = http.checkForOpponent(userName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!response.equals("Waiting..."))
		{
			System.out.println(response);
			activeFrame.setEnabled(false);
			activeFrame.setVisible(false);
			timer.stop();
			http.buildGameFrame(response.toString());
		}
	}
}
