import java.awt.event.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class JoinGameUpdater extends HttpService implements ActionListener {
	private JFrame activeFrame;
	
	private String userName;
	
	public JoinGameUpdater(JFrame f, String name)
	{
		activeFrame = f;
		userName = name;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String response = "";
		response = checkForOpponent(userName);
		System.out.println(response + " resp");
		if(response == null)
		{
			int input = JOptionPane.showConfirmDialog(null, "Connection broken!");
			if(input >= 0)
			{
				timer.stop();
				SignInMenu menu = new SignInMenu();
				menu.buildStartFrame(activeFrame);
			}
		}
		else if(!response.equals("Waiting..."))
		{
			timer.stop();
			final String resp = response;
			Runnable r = new Runnable() {
	            public void run() {
	                ChessGUI cg = new ChessGUI();
	                cg.buildFrame(activeFrame, userName, resp);
	               
	            }
	        };
	       
	        SwingUtilities.invokeLater(r);
		}
	}
	
	/*----------------------------------------------------------
	 * Sees if they have been placed into a game yet.
	 * 
	 * Given a user name, this either returns "Waiting..." when
	 * no opponent is available, and the name of the game 
	 * session when the player has been placed into a game. If
	 * the first player to sign in is "Alex" and the second is 
	 * "Bill", then the session will be called "Alex vs Bill".
	 * Regardless of whether checkForOpponents is passed "Alex"
	 * or "Bill", once a session has been created, this will
	 * return "Alex vs Bill".
	 *----------------------------------------------------------*/
	public String checkForOpponent(String userName)
	{
		String url = SERVER_SITE + "/getOpponent";
		System.out.println("Checking for opponent for " + userName);
		try
		{
			HttpURLConnection con = setUpConnection(url);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("user", userName);
			
			String encodedParam = encodeStringForPost(params);
			return getResultsWithParams(con, encodedParam);
		} catch(Exception e)
		{
			return null;
		}
	}
	
}
