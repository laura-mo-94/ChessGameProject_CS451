import javax.swing.*;

public class WaitMenuFunction extends HttpService{
	
	String playerName;
	JFrame frame;
	
	public WaitMenuFunction(String name, JFrame frame)
	{
		playerName = name;
		this.frame = frame;
	}
	
	public void buildFrame(String initialMessage)
	{
		frame.getContentPane().removeAll();
		JLabel state = new JLabel(initialMessage);
		state.setBounds(100, 40, 300, 20);
		frame.add(state);
		
		state.setText(initialMessage);
		
		frame.revalidate();
		frame.repaint();
		frame.setVisible(true);
		

		JoinGameUpdater updater = new JoinGameUpdater(frame, playerName);
		Timer timer = new Timer(1000, updater);
		timer.start();
		updater.attachTimer(timer);
	}
	
}
