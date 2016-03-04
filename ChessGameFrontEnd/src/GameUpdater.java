import java.awt.event.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameUpdater extends HttpService implements ActionListener  
{
	private JLabel label;
	
	private String gameName;
	
	public GameUpdater(JLabel label, String name)
	{
		this.label = label;
		gameName = name;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String response = checkState(SERVER_SITE + "/getGameMessage", gameName);
		
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
}
