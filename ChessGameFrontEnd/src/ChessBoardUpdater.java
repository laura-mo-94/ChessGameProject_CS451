import java.awt.event.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ChessBoardUpdater extends HttpService 
{
	private JLabel label;
	private String gameName;
	
	public ChessBoardUpdater(JLabel label, String name)
	{
		this.label = label;
		gameName = name;
	}
	
}
