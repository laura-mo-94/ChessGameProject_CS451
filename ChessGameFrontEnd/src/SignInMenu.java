/*----------------------------------------------------------
 * HttpURLConnectionTest.java
 * Laura Mo
 * v1.2
 * Tests the concepts for the nodejs server. Creates frames
 * and sets up framework to sign in to a server then start
 * seperate sessions of adding numbers.
 *----------------------------------------------------------*/
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import java.awt.event.*;
import java.net.HttpURLConnection;


public class SignInMenu extends HttpService
{
	
	public SignInMenu()
	{
		
	}
	
	/*----------------------------------------------------------
	 * Builds the sign in frame.
	 *----------------------------------------------------------*/
	
	public void buildStartFrame(JFrame frame)
	{
		SignInMenu menu = new SignInMenu();
		
		JFrame startFrame;
		
		if(frame == null)
		{
			startFrame = new JFrame();
		}
		else
		{
			startFrame = frame;
			startFrame.getContentPane().removeAll();
		}
		
		JLabel label = new JLabel("Please enter your name");
		label.setBounds(100, 40, 300, 20);
		startFrame.add(label);
		
		JTextField textField = new JTextField(20);
		textField.setBounds(100, 100, 200, 30);
		
		startFrame.add(textField);
		
		JButton b = new JButton("JOIN GAME");
		b.setBounds(130, 150, 100, 40);
		
		// makes it so that on click, this button will run the joinGame function and
		// start up a timer that constantly checks fo an opponent
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try{
					String name = textField.getText();
					menu.joinGame(name, label, startFrame);
					
				}catch(Exception exp)
				{
					System.out.println("Error! Failed to send button action. " + exp);
					exp.printStackTrace();
				}
			}
		});
		
		startFrame.add(b);
		
		if(frame == null)
		{
			startFrame.setSize(400, 500);
			startFrame.setLayout(null);
			startFrame.setVisible(true);
			startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			startFrame.revalidate();
			startFrame.repaint();
		}
	}
	
	
	/*----------------------------------------------------------
	 * Joins the game.
	 * Should return "Waiting for opponent..." when called
	 *----------------------------------------------------------*/
	public void joinGame(String name, JLabel label, JFrame frame) throws Exception
	{
		String url = SERVER_SITE + "/join";
		HttpURLConnection con = setUpConnection(url);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("user", name);
		String encodedParam = encodeStringForPost(params);

		String response = getResultsWithParams(con, encodedParam);
		
		if(response == null)
		{
			label.setText("Error! Could not connect to network!");
		}
		else if(!response.contains("Waiting"))
		{
			label.setText("Name is in use! Please choose another.");
		}
		else
		{
			System.out.println("here i am");
			WaitMenuFunction waitMenu = new WaitMenuFunction(name, frame);
			waitMenu.buildFrame(response);
		}
	}
}
