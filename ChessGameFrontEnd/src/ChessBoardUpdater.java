import java.awt.event.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ChessBoardUpdater extends HttpService implements ActionListener 
{
	private JLabel label;
	private String gameName;
	
	public ChessBoardUpdater(JLabel label, String name)
	{
		this.label = label;
		gameName = name;
	}
	
	/*----------------------------------------------------------
	 * Calls for an update to the game state.
	 * 
	 * Given a newly created session (name is the name of the
	 * session), this will return 2, since it is +1 of the
	 * current state.
	 *----------------------------------------------------------*/
	
	public String sendAction(String name, String update) throws Exception
	{	
		System.out.println("Pressed the add button...");
		String url = SERVER_SITE + "/submitMove";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("update", update);
		params.put("user", name);
		
		String encodedParam = encodeStringForPost(params);
		
		return getResultsWithParams(con, encodedParam);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String response = checkState(SERVER_SITE + "/getState", gameName);
		
		if(!response.equals(""))
		{
			label.setText(response);
		}
	}
	
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
