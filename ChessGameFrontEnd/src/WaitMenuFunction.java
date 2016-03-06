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
		System.out.println("eeeeeh");
		frame.getContentPane().removeAll();
		JLabel state = new JLabel(initialMessage);
		state.setBounds(100, 40, 300, 20);
		frame.add(state);
		
		JoinGameUpdater updater = new JoinGameUpdater(frame, playerName);
		Timer timer = new Timer(1000, updater);
		timer.start();
		
		state.setText(initialMessage);
		updater.attachTimer(timer);
		
		frame.revalidate();
		frame.repaint();
	}
	
}
