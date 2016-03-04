import java.awt.event.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameUpdater extends HttpService implements ActionListener  
{
	private JLabel label;
	private JLabel messageLabel;
	private String gameName;
	
	public GameUpdater(JLabel label, JLabel message, String name)
	{
		this.label = label;
		messageLabel = message;
		gameName = name;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Here...");
		String response = checkState(SERVER_SITE + "/getGameMessage", gameName);
		
		if(!response.equals(""))
		{
			messageLabel.setText(response);
		}
		
		response = checkBoardState(SERVER_SITE + "/getState", gameName);
		
		if(!response.equals(""))
		{
			label.setText(response);
		}
	}
	
	/*----------------------------------------------------------
	 * Checks the status of the game. Url passed in should
	 * be http://localhost:3000/getState
	 * 
	 * Name is the name of session, ie "Alex vs Bill".
	 * Given this, it will return what number the state is 
	 * currently at. If sendAction has never been called, this
	 * should be 1. After sendAction, it should be 2.
	 *----------------------------------------------------------*/
	public String checkState(String url, String name)
	{
		HttpURLConnection con;
		String response = "";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		
		try {
			con = setUpConnection(url);
			String encodedParam = encodeStringForPost(params);
			response = getResultsWithParams(con, encodedParam);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return response;
	}
	
	public String checkBoardState(String url, String name)
	{
		HttpURLConnection con;
		String response = "";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		
		try {
			con = setUpConnection(url);
			String encodedParam = encodeStringForPost(params);
			response = getResultsWithParams(con, encodedParam);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return response;
	}
	

	/*----------------------------------------------------------
	 * Calls for an update to the game state.
	 * 
	 * Given a newly created session (name is the name of the
	 * session), this will return 2, since it is +1 of the
	 * current state.
	 *----------------------------------------------------------*/
	
	public String sendAction(String update) throws Exception
	{	
		System.out.println("Pressed the add button...");
		String url = SERVER_SITE + "/submitMove";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("update", update);
		params.put("user", gameName);
		
		String encodedParam = encodeStringForPost(params);
		
		return getResultsWithParams(con, encodedParam);
	}
}
