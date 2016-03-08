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
		System.out.println("Get it dude " + userName);
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
	
	/*private void buildGameFrame(String gameName, JFrame frame)
	{
		frame.getContentPane().removeAll();
		JLabel label = new JLabel(gameName);
		label.setBounds(100, 40, 300, 20);
		frame.add(label);
		
		JLabel state = new JLabel("0");
		state.setBounds(100, 100, 200, 30);
		
		JLabel message = new JLabel("");
		message.setBounds(100, 200, 300, 160);
		frame.add(state);
		
		JButton b = new JButton("ADD");
		b.setBounds(130, 150, 100, 40);
		
		JButton draw = new JButton("DRAW");
		draw.setBounds(130, 200, 100, 40);
		frame.add(draw);
		
		JButton forfeit = new JButton("FORFEIT");
		forfeit.setBounds(130, 250, 100, 40);
		frame.add(forfeit);
		
		GameUpdater updater = new GameUpdater(state, message, userName, gameName);
		updater.setActiveFrame(frame);
		
		try{
			Timer timer = new Timer(1000, updater);
			timer.start();
			
			updater.attachTimer(timer);
			
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
					if(updater.getIsActive())
					{
						updater.sendAction(String.valueOf((Integer.parseInt(state.getText()) + 1)));
					}
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
				
			}
		});
		
		draw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					updater.draw();
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		
		forfeit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{
					updater.forfeit();
				}catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		});
		
		frame.add(b);
		frame.revalidate();
		frame.repaint();
	}*/
	
}
