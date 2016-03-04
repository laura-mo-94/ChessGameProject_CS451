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
		try {
			response = checkForOpponent(userName);
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
			buildGameFrame(response);
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
	public String checkForOpponent(String userName) throws Exception
	{
		String url = SERVER_SITE + "/getOpponent";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", userName);
		
		String encodedParam = encodeStringForPost(params);
		return getResultsWithParams(con, encodedParam);
	}
	
	private static void buildGameFrame(String gameName)
	{
		JFrame startFrame = new JFrame();
		JLabel label = new JLabel(gameName);
		label.setBounds(100, 40, 300, 20);
		startFrame.add(label);
		
		JLabel state = new JLabel("0");
		state.setBounds(100, 100, 200, 30);
		
		JLabel message = new JLabel("");
		message.setBounds(100, 200, 300, 160);
		startFrame.add(state);
		
		JButton b = new JButton("ADD");
		b.setBounds(130, 150, 100, 40);
		
		GameUpdater updater = new GameUpdater(state, gameName);
		ChessBoardUpdater boardUpdater = new ChessBoardUpdater(message, gameName);
		try{
			Timer timer = new Timer(1000, updater);
			timer.start();
			
			updater.attachTimer(timer);
			
			Timer boardTimer = new Timer(1000, boardUpdater);
			boardUpdater.attachTimer(boardTimer);
			
		}catch(Exception exp)
		{
			System.out.println("Error! Failed to send button action. " + exp);
			exp.printStackTrace();
		}
	
		// makes it so that on click, this button will run the joinGame function and
		// start up a timer that constantly checks fo an opponent
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					boardUpdater.sendAction(gameName, String.valueOf((Integer.parseInt(state.getText()) + 1)));
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
				
			}
		});
		
		startFrame.add(b);
		startFrame.setSize(400, 500);
		startFrame.setLayout(null);
		startFrame.setVisible(true);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
