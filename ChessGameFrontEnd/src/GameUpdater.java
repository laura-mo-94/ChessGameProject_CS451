import java.awt.event.*;
import java.net.HttpURLConnection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameUpdater extends HttpService implements ActionListener  
{
	private JLabel label;
	private JLabel messageLabel;
	private JFrame frame;
	private String gameName;
	private String userName;
	private boolean isActive = false;
	
	private boolean attemptingDraw = false;
	float timeElapsed = 0;
	float timeLimit = 300;
	private boolean countingDown;
	private boolean disconnected;
	
	private ChessGUI gui;
	private boolean isWhite;
	
	Date startTime;
	
	public GameUpdater(JLabel state, JLabel game, String name, String gname)
	{
		label = state;
		messageLabel = game;
		gameName = gname;
		String[] names = gameName.split(" ");
		userName = name;
		
		game.setText(gameName);
		
		if(names[0].equals(name))
		{
			label.setText("Your turn");
			startTime = new Date();
			isActive = true;
			isWhite = true;
		}
		else
		{
			label.setText("Opponent's turn");
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.print(".");
		String response = checkState(SERVER_SITE + "/getGameMessage", gameName, userName);
		Date currentTime = new Date();
		
		if(countingDown && disconnected)
		{
			countingDown = false;
			disconnected = false;
		}
		if(response != null && !response.equals(""))
		{
			messageLabel.setText(response);
		}
		else
		{
			messageLabel.setText(gameName);
		}
		
		response = checkBoardState(SERVER_SITE + "/getState", gameName);
		
		if(response != null && !response.equals(""))
		{
			
			if(Board.isCheckMate() && Board.isChecked())
				showCheckMate();
			if(!Board.kingExists())
				showCheckMate();
			
			isActive = !isActive;
			gui.makeMove(response);
			
			if(isActive)
			{
				label.setText("Your turn");
				startTime = new Date();
			}
			else
			{
				label.setText("Opponent's turn!");
			}
			
			countingDown = false;
			disconnected = false;
		}
		
		if(isActive && (currentTime.getTime() - startTime.getTime())/1000 > timeLimit && !countingDown)
		{
			countingDown = true;
			timeElapsed = timeLimit;
			JOptionPane.showConfirmDialog(null, "Are you still there?");
			
		}
		
		if(countingDown)
		{
			System.out.println(timeElapsed + " seconds left");
			if(timeElapsed > 0)
			{
				timeElapsed = timeElapsed - 1;
				messageLabel.setText(timeElapsed + " seconds left until forfeit");
			}
			else
			{
				int input = JOptionPane.showConfirmDialog(null, "You have forfeited the game.");
				if(input == JOptionPane.OK_OPTION)
				{
					timer.stop();
					
					if(!disconnected)
					{
						leaveGame();
						sendGameMessage("Player has forfeit");
						getAnotherGame();
					}
					else
					{
						startScreen();
					}
				}
			}
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
	public String checkState(String url, String gameName, String userName)
	{
		HttpURLConnection con;
		String response = "";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", userName);
		params.put("gameName", gameName);
		
		try {
			con = setUpConnection(url);
			String encodedParam = encodeStringForPost(params);
			response = getResultsWithParams(con, encodedParam);
			
			if(response != null)
			{
				disconnected = false;
				if(response.equals("Requesting a draw") && !attemptingDraw)
				{
					int input = JOptionPane.showConfirmDialog(null, "User requested a draw. Do you accept?", "Draw Request", JOptionPane.YES_NO_OPTION);
					if(input == JOptionPane.OK_OPTION)
					{
						timer.stop();
						leaveGame();
						sendGameMessage("Draw accepted");
						getAnotherGame();
					}
					else
					{
						sendGameMessage("Draw rejected");
					}
				}
				else if(response.equals("Draw accepted") && attemptingDraw)
				{
					attemptingDraw = false;
					int input = JOptionPane.showConfirmDialog(null, "Draw accepted!");
					if(input >= 0)
					{
						timer.stop();
						leaveGame();
						endGame();
						getAnotherGame();
					}
				}
				else if(response.equals("Draw rejected") && attemptingDraw)
				{
					int input = JOptionPane.showConfirmDialog(null, "Draw was rejected.");
					if(input >= 0)
					{
						attemptingDraw = false;
					}
				}
				else if(response.equals("Player has forfeit"))
				{
					int input = JOptionPane.showConfirmDialog(null, "Opponent has forfeited");
					System.out.println("what are you??? " + input);
					isActive = false;
					System.out.println(userName + " is also leaving now");
					timer.stop();
					leaveGame();
					endGame();
					getAnotherGame();
				}
				
			}
			else if(response == null && !countingDown)
			{
				JOptionPane.showConfirmDialog(null, "Network connection broken! Check connection then continue.");
				countingDown = true;  
				disconnected = true;
				timeElapsed = timeLimit;
			}
			
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
		String url = SERVER_SITE + "/submitMove";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("update", update);
		params.put("user", gameName);
		
		String encodedParam = encodeStringForPost(params);
		
		return getResultsWithParams(con, encodedParam);
	}
	
	public String sendGameMessage(String message) 
	{
		String url = SERVER_SITE + "/postMessage";
		HttpURLConnection con = null;
		String encodedParam = "";
		
		try{
			con = setUpConnection(url);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("update", message);
			params.put("user", gameName);
			
			encodedParam = encodeStringForPost(params);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return getResultsWithParams(con, encodedParam);
	}
	
	public boolean getIsActive()
	{
		return isActive;
	}
	
	public void setActiveFrame(JFrame f)
	{
		frame = f;
	}
	
	public void setChessGui(ChessGUI g)
	{
		gui = g;
	}
	
	public void endGame()
	{
		System.out.println(userName + " is ending the game");
		String url = SERVER_SITE + "/removeGame";
		
		try{
			HttpURLConnection con = setUpConnection(url);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("game", gameName);
			String encodedParam = encodeStringForPost(params);

			getResultsWithParams(con, encodedParam);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void leaveGame()
	{
		System.out.println(userName + " is leaving the game");
		String url = SERVER_SITE + "/leaveGame";
		
		try{
			HttpURLConnection con = setUpConnection(url);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("userName", userName);
			String encodedParam = encodeStringForPost(params);

			getResultsWithParams(con, encodedParam);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void getAnotherGame()
	{
		System.out.println(userName + " is getting another game");
		
		frame.dispose();
		
		String url = SERVER_SITE + "/join";
		try{
			HttpURLConnection con = setUpConnection(url);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("user", userName);
			String encodedParam = encodeStringForPost(params);

			String response = getResultsWithParams(con, encodedParam);
			
			Runnable r = new Runnable() {
	            public void run() {
	            	System.out.println("for " + userName);
	            	JFrame waitFrame = new JFrame("Waiting");
	        		waitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        		
	        		waitFrame.setSize(400, 500);
	        		waitFrame.setLayout(null);
	        		waitFrame.setVisible(true);
	        		
	            	WaitMenuFunction waitScreen = new WaitMenuFunction(userName, waitFrame);
	            	waitScreen.buildFrame("Waiting for an opponent...");
	            }
	        };
	       
	        SwingUtilities.invokeLater(r);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void draw() throws Exception
	{
		sendGameMessage("Requesting a draw");
		attemptingDraw = true;
	}
	
	public void forfeit() throws Exception
	{
		timer.stop();
		sendGameMessage("Player has forfeit");
		leaveGame();
		getAnotherGame();
	}
	
	public void startScreen()
	{
		timer.stop();
		frame.dispose();

		Runnable r = new Runnable() {
            public void run() {
            	System.out.println("for " + userName);

        		SignInMenu signIn = new SignInMenu();
        		signIn.buildStartFrame(null);
            }
        };
       
        SwingUtilities.invokeLater(r);
	}
	
	public boolean getIsWhite()
	{
		return isWhite;
	}
	
	public void showCheckMate()
	{
		int input = JOptionPane.showConfirmDialog(null, "Check mate!");
		if(input >= 0)
		{
			timer.stop();
			leaveGame();
			endGame();
			getAnotherGame();
		}
	}
}
